package com.campusToutiao.CampusToutiaoEE.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.IUploadService;
import com.campusToutiao.CampusToutiaoEE.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUploadService uploadService;


    @PostMapping("/reg")
    public String regUser(@RequestParam("username") String username, @RequestParam("passwd") String passwd,
                          @RequestParam("nickname") String nickname, @RequestParam("gender") Integer gender,
                          @RequestParam("tel") String tel, @RequestParam("profile") String profile) {
        ToutiaoResult toutiaoResult = userService.regUser(username, passwd, nickname, gender, tel, profile);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 验证用户名是否被注册过
     *
     * @param username
     * @return
     */
    @GetMapping("/checkUsername")
    public String checkUsername(@RequestParam("username") String username) {
        ToutiaoResult toutiaoResult = userService.checkUsername(username);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 登录
     *
     * @param username
     * @param passwd
     * @param rememberME
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("passwd") String passwd,
                        @RequestParam("rememberME") boolean rememberME, HttpServletResponse response) {
        ToutiaoResult toutiaoResult = userService.login(username, passwd, rememberME);
        // 判断是否存在ticket
        if (0 == toutiaoResult.getToutiaoCode() && null != toutiaoResult.getToutiaoData().get("ticket")) {
            // 创建cookie
            Cookie cookie = new Cookie("ticket", toutiaoResult.getToutiaoData().get("ticket").toString());
            // 设置携带路径
            cookie.setPath("/");
            // 设置过期时间
            cookie.setMaxAge(3600 * 24 * 7);
            response.addCookie(cookie);
        }
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 前端带着ticket自动登录
     *
     * @return
     */
    @GetMapping("/autoLogin")
    public String autoLogin(HttpServletRequest request) {
        ToutiaoResult toutiaoResult = userService.autoLoginByTicket(request);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 注销
     *
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
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

        if (null != ticket) {
            // 删除浏览器cookie
            Cookie cookie = new Cookie("ticket", "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            // 去redis中删除ticket
            return JSONObject.toJSONString(userService.logout(ticket));
        } else {
            return JSONObject.toJSONString(new ToutiaoResult(0, "删除ticket成功"));
        }
    }

    /**
     * 修改用户信息
     *
     * @param userId
     * @param oldPasswd
     * @param newPasswd
     * @param tel
     * @param profile
     * @param nickname
     * @param gender
     * @return
     */
    @PostMapping("/editUserInfo")
    public String editUserInfo(@RequestParam("userId") Integer userId, @RequestParam("oldPasswd") String oldPasswd, @RequestParam("newPasswd") String newPasswd,
                               @RequestParam("tel") String tel, @RequestParam("profile") String profile,
                               @RequestParam("nickname") String nickname, @RequestParam("gender") Integer gender,
                               HttpServletRequest request, HttpServletResponse response) {
        ToutiaoResult toutiaoResult = userService.updateUserInfo(userId, oldPasswd, newPasswd, tel, profile, nickname, gender);
        // 修改信息成功，删除cookie
        if (0 == toutiaoResult.getToutiaoCode()) {
            // 获取cookie
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

            if (null != ticket) {
                // 删除浏览器cookie
                Cookie cookie = new Cookie("ticket", "");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                // 去redis中删除ticket
                userService.logout(ticket);
            }
        }
        return JSON.toJSONString(toutiaoResult);
    }

    /**
     * 上传用户头像
     *
     * @param headImage
     * @return
     */
    @PostMapping("/uploadHeaderPic")
    public String uploadHeaderPic(@RequestParam("headImage") MultipartFile headImage) {
        try {
            String headImageUrl = uploadService.uploadImage(headImage, "USER");
            if (null == headImageUrl) {
                return JSONObject.toJSONString(new ToutiaoResult(1, "图片上传失败"));
            }
            Map<String, Object> mp = new HashMap<>();
            mp.put("headImageUrl", headImageUrl);
            return JSONObject.toJSONString(new ToutiaoResult(0, "图片上传成功", mp));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return JSONObject.toJSONString(new ToutiaoResult(1, "图片上传失败"));
        }
    }


    /**
     * 修改用户头像
     *
     * @param userId
     * @param imgUrl
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/updateUserHeadImage")
    public String updateUserHeadImage(@RequestParam("userId") Integer userId,
                                      @RequestParam("imgUrl") String imgUrl,
                                      HttpServletRequest request, HttpServletResponse response) {
        ToutiaoResult toutiaoResult = userService.updateUserHeadImage(userId, imgUrl);
        // 修改信息成功，删除cookie
        if (0 == toutiaoResult.getToutiaoCode()) {
            // 获取cookie
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

            if (null != ticket) {
                // 删除浏览器cookie
                Cookie cookie = new Cookie("ticket", "");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                // 去redis中删除ticket
                userService.logout(ticket);
            }
        }
        return JSON.toJSONString(toutiaoResult);
    }

    /**
     * 查询用户总数
     *
     * @return
     */
    @GetMapping("/admin/getAllUserCount")
    public String getAllUserCount() {
        ToutiaoResult toutiaoResult = userService.getAllUserCount();
        return JSON.toJSONString(toutiaoResult);
    }

    /**
     * 查询用户信息
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/admin/getUserList")
    public String getUserList(@RequestParam("page") Integer page,
                              @RequestParam("pageSize") Integer pageSize) {
        ToutiaoResult toutiaoResult = userService.getUserList(page, pageSize);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @GetMapping("/admin/delUser")
    public String delUser(@RequestParam("userId") Integer userId) {
        ToutiaoResult toutiaoResult = userService.delUserById(userId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 批量删除用户
     *
     * @param delUserIdListStr
     * @return
     */
    @PostMapping("/admin/batchDelUser")
    public String batchDelUser(@RequestParam("delUserIdListStr") String delUserIdListStr) {
        // 将json数组字符串转化为数组
        List<Integer> delUserIdList = JSONObject.parseArray(delUserIdListStr, Integer.class);
        ToutiaoResult toutiaoResult = userService.batchDelUser(delUserIdList);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 绘制活跃用户信息柱状图，返回echarts所用的数据格式
     *
     * @return
     */
    @GetMapping("/admin/drawActiveUserDataChart")
    public String drawActiveUserDataChart() {
        ToutiaoResult toutiaoResult = userService.drawActiveUserDataChart();
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 绘制注册用户的性别分布的饼状图，返回echarts所用的数据格式
     *
     * @return
     */
    @GetMapping("/admin/drawUserGenderDataChart")
    public String drawUserGenderDataChart() {
        ToutiaoResult toutiaoResult = userService.drawUserGenderDataChart();
        return JSONObject.toJSONString(toutiaoResult);
    }

}
