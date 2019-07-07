package com.campusToutiao.CampusToutiaoEE.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.campusToutiao.CampusToutiaoEE.dao.CommentMapper;
import com.campusToutiao.CampusToutiaoEE.dao.UserMapper;
import com.campusToutiao.CampusToutiaoEE.entity.User;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.IUserService;
import com.campusToutiao.CampusToutiaoEE.utils.JedisAdapter;
import com.campusToutiao.CampusToutiaoEE.utils.ToutiaoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisAdapter jedisAdapter;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ToutiaoResult regUser(String username, String passwd, String nickname, Integer gender, String tel, String profile) {
        User user = new User();
        // 设置属性
        user.setUsername(username);
        user.setPasswd(passwd);
        user.setNickname(nickname);
        user.setGender(gender);
        user.setTel(tel);
        user.setProfile(StringUtils.isBlank(profile) ? "" : profile);
        // 生成随机的6位盐值
        user.setSalt(ToutiaoUtils.uuidStr().substring(0, 6));
        // 密码加盐后md5加密
        user.setPasswd(ToutiaoUtils.MD5(user.getPasswd() + user.getSalt()));
        // 设置默认的用户头像
        user.setHeadImageUrl("https://www.gravatar.com/avatar/" + ToutiaoUtils.uuidStr() + "?s=230&d=identicon");

        userMapper.insertSelective(user);
        return new ToutiaoResult(0, "注册成功");
    }

    @Override
    public ToutiaoResult checkUsername(String username) {
        if (StringUtils.isNotBlank(username)) {
            User user = userMapper.getUserByUsername(username);
            if (null == user) {
                return new ToutiaoResult(0, "用户不存在");
            } else {
                return new ToutiaoResult(1, "用户存在");
            }
        } else {
            return new ToutiaoResult(1, "用户名为空");
        }
    }

    @Override
    public ToutiaoResult login(String username, String passwd, boolean rememberME) {
        if (StringUtils.isBlank(username)) {
            return new ToutiaoResult(1, "用户名不能为空");
        }
        if (StringUtils.isBlank(passwd)) {
            return new ToutiaoResult(1, "密码不能为空");
        }
        User user = userMapper.getUserByUsername(username);
        if (null == user) {
            return new ToutiaoResult(1, "用户名错误");
        } else {
            if (ToutiaoUtils.MD5(passwd + user.getSalt()).equals(user.getPasswd())) {
                // 登录成功返回的数据
                HashMap<String, Object> mp = new HashMap<>();
                mp.put("user", user);
                if (rememberME) {
                    // 生成ticket
                    String ticket = ToutiaoUtils.USER_TICKET_PREFIX + ToutiaoUtils.uuidStr();
                    // 记住用户，默认7天有效期
                    jedisAdapter.setex(ticket, JSONObject.toJSONString(user), 3600 * 24 * 7);
                    // 将ticket返回
                    mp.put("ticket", ticket);
                }
                return new ToutiaoResult(0, "登录成功", mp);
            } else {
                return new ToutiaoResult(1, "密码错误");
            }
        }
    }

    @Override
    public ToutiaoResult autoLoginByTicket(HttpServletRequest request) {
        // 获取cookie中的登录凭证
        String ticket = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if ("ticket".equals(cookie.getName())) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        // 去redis中取用户信息
        if (null != ticket) {
            String userStr = jedisAdapter.get(ticket);
            if (StringUtils.isNotBlank(userStr)) {
                User user = JSONObject.parseObject(userStr, User.class);
                Map<String, Object> mp = new HashMap<>();
                mp.put("user", user);
                return new ToutiaoResult(0, mp);
            } else {
                return new ToutiaoResult(1, "自动登录失败，ticket失效");
            }
        } else {
            return new ToutiaoResult(1, "自动登录失败，ticket失效");
        }
    }

    @Override
    public ToutiaoResult logout(String ticket) {
        // 去redis中删除ticket
        jedisAdapter.del(ticket);
        return new ToutiaoResult(0, "删除ticket成功");
    }

    @Override
    public ToutiaoResult updateUserInfo(Integer userId, String oldPasswd, String newPasswd, String tel, String profile, String nickname, Integer gender) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (null != user) {
            if (user.getPasswd().equals(ToutiaoUtils.MD5(oldPasswd + user.getSalt()))) {
                // 更新信息
                user.setNickname(nickname);
                user.setGender(gender);
                user.setPasswd(ToutiaoUtils.MD5(newPasswd + user.getSalt()));
                user.setTel(tel);
                user.setProfile(profile);
                userMapper.updateByPrimaryKeySelective(user);

                Map<String, Object> mp = new HashMap<>();
                mp.put("user", user);
                return new ToutiaoResult(0, "修改信息成功", mp);
            } else {
                return new ToutiaoResult(1, "密码错误，修改用户信息失败");
            }
        } else {
            return new ToutiaoResult(1, "用户不存在，修改用户信息失败");
        }
    }

    @Override
    public ToutiaoResult updateUserHeadImage(Integer userId, String imgUrl) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (null != user) {
            if (StringUtils.isNotBlank(imgUrl)) {
                user.setHeadImageUrl(imgUrl);
                userMapper.updateByPrimaryKeySelective(user);

                Map<String, Object> mp = new HashMap<>();
                mp.put("user", user);
                return new ToutiaoResult(0, "修改头像成功", mp);
            } else {
                return new ToutiaoResult(1, "要修改的头像不存在，修改头像失败");
            }
        } else {
            return new ToutiaoResult(1, "用户不存在，修改头像失败");
        }
    }

    @Override
    public ToutiaoResult getAllUserCount() {
        int userTotal = userMapper.getAllUserCount();

        Map<String, Object> mp = new HashMap<>();
        mp.put("userTotal", userTotal);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult getUserList(Integer page, Integer pageSize) {
        int pageIndex = (page - 1) * pageSize;
        List<User> userList = userMapper.getUserList(pageIndex, pageSize);
        Map<String, Object> mp = new HashMap<>();
        mp.put("userList", userList);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult delUserById(Integer userId) {
        userMapper.deleteByPrimaryKey(userId);
        return new ToutiaoResult(0, "删除用户成功");
    }

    @Override
    public ToutiaoResult batchDelUser(List<Integer> delUserIdList) {
        // 循环删除
        for (Integer userId : delUserIdList) {
            userMapper.deleteByPrimaryKey(userId);
        }
        return new ToutiaoResult(0, "批量删除用户成功");
    }

    @Override
    public ToutiaoResult drawActiveUserDataChart() {

        List<String> xAxisData = new ArrayList<>();
        List<Long> seriesData = new ArrayList<>();

        List<HashMap<String, Object>> top10UserList = commentMapper.getTop10ActiveUser();
        for (HashMap<String, Object> top10User : top10UserList) {
            Long commentCount = (Long) top10User.get("commentCount");
            seriesData.add(commentCount);
            Integer userId = (Integer) top10User.get("userId");
            User user = userMapper.selectByPrimaryKey(userId);
            xAxisData.add(user.getUsername());
        }

        // 封装echarts所用的数据
        Map<String, Object> resultMp = new HashMap<>();

        resultMp.put("color", new String[]{"#3398DB"});

        Map<String, Object> titleMp = new HashMap<>();
        titleMp.put("text", "最活跃的前10名用户评论数");
        resultMp.put("title", titleMp);

        Map<String, Object> tooltipMp = new HashMap<>();
        tooltipMp.put("trigger", "axis");
        Map<String, Object> axisPointerMp = new HashMap<>();
        axisPointerMp.put("type", "shadow");
        tooltipMp.put("axisPointer", axisPointerMp);
        resultMp.put("tooltip", tooltipMp);

        Map<String, Object> legendMp = new HashMap<>();
        legendMp.put("data", new String[]{"评论数量"});
        resultMp.put("legend", legendMp);

        Map<String, Object> xAxisMp = new HashMap<>();
        xAxisMp.put("data", xAxisData);
        resultMp.put("xAxis", xAxisMp);

        Map<String, Object> yAxisMp = new HashMap<>();
        resultMp.put("yAxis", yAxisMp);

        Map<String, Object> seriesMp = new HashMap<>();
        seriesMp.put("name", "评论数量");
        seriesMp.put("type", "bar");
        seriesMp.put("data", seriesData);
        resultMp.put("series", new Object[]{seriesMp});

        Map<String, Object> mp = new HashMap<>();
        mp.put("echartsData", resultMp);

        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult drawUserGenderDataChart() {

        int male = 0, female = 0;

        List<User> userList = userMapper.getAllUserList();
        for (User user : userList) {
            if (user.getGender() == 0) {
                male++;
            } else {
                female++;
            }
        }

        List<Map<String, Object>> seriesDataList = new ArrayList<>();

        Map<String, Object> seriesMp1 = new HashMap<>();
        seriesMp1.put("value", male);
        seriesMp1.put("name", "男");
        seriesDataList.add(seriesMp1);

        Map<String, Object> seriesMp2 = new HashMap<>();
        seriesMp2.put("value", female);
        seriesMp2.put("name", "女");
        seriesDataList.add(seriesMp2);

        // 封装echarts所用的数据
        Map<String, Object> resultMp = new HashMap<>();

        Map<String, Object> titleMp = new HashMap<>();
        titleMp.put("text", "用户男女占比");
        titleMp.put("x", "center");
        resultMp.put("title", titleMp);

        Map<String, Object> tooltipMp = new HashMap<>();
        tooltipMp.put("trigger", "item");
        tooltipMp.put("formatter", "{a} <br/>{b} : {c} ({d}%)");
        resultMp.put("tooltip", tooltipMp);

        Map<String, Object> legendMp = new HashMap<>();
        legendMp.put("orient", "vertical");
        legendMp.put("left", "left");
        legendMp.put("data", new String[]{"男", "女"});
        resultMp.put("legend", legendMp);

        Map<String, Object> seriesMp = new HashMap<>();
        seriesMp.put("name", "用户性别");
        seriesMp.put("type", "pie");
        seriesMp.put("radius", "55%");
        seriesMp.put("center", new String[]{"50%", "60%"});
        seriesMp.put("data", seriesDataList);

        Map<String, Object> itemStyleMp = new HashMap<>();
        Map<String, Object> emphasisMp = new HashMap<>();
        emphasisMp.put("shadowBlur", 10);
        emphasisMp.put("shadowOffsetX", 0);
        emphasisMp.put("shadowColor", "rgba(0, 0, 0, 0.5)");
        itemStyleMp.put("emphasis", emphasisMp);
        seriesMp.put("itemStyle", itemStyleMp);

        resultMp.put("series", new Object[]{seriesMp});

        Map<String, Object> mp = new HashMap<>();
        mp.put("echartsData", resultMp);

        return new ToutiaoResult(0, mp);
    }

}
