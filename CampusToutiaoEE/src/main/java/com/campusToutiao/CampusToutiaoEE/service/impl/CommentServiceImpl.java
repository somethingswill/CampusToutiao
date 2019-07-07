package com.campusToutiao.CampusToutiaoEE.service.impl;

import com.campusToutiao.CampusToutiaoEE.dao.CommentMapper;
import com.campusToutiao.CampusToutiaoEE.dao.NewsMapper;
import com.campusToutiao.CampusToutiaoEE.dao.UserMapper;
import com.campusToutiao.CampusToutiaoEE.entity.Comment;
import com.campusToutiao.CampusToutiaoEE.entity.News;
import com.campusToutiao.CampusToutiaoEE.entity.User;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.ICommentService;
import com.campusToutiao.CampusToutiaoEE.utils.ToutiaoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public ToutiaoResult postComment(String commentContent, Integer newsId, Integer userId) {
        if (StringUtils.isNotBlank(commentContent)) {
            // 设置属性
            Comment comment = new Comment();
            comment.setArticleId(newsId);
            comment.setUserId(userId);
            comment.setCommentContent(commentContent);
            comment.setLikeCount(0);
            comment.setDislikeCount(0);
            comment.setCreateDate(new Date());

            commentMapper.insertSelective(comment);
            // 评论入库，此时需要更新资讯的评论数
            List<Comment> commentList = commentMapper.getCommentListByNewsId(newsId);
            News news = newsMapper.selectByPrimaryKey(newsId);
            // 此处不应该是加1这种操作，有线程安全问题，所以应再去数据库查询
            // news.setCommentCount(news.getCommentCount() + 1);
            news.setCommentCount(commentList.size());
            newsMapper.updateByPrimaryKeySelective(news);

            // 额外将发表评论的用户名和用户头像返回
            User user = userMapper.selectByPrimaryKey(userId);
            Map<String, Object> mp = ToutiaoUtils.object2Map(comment);
            mp.put("userNickname", user.getNickname());
            mp.put("userHeadImageUrl", user.getHeadImageUrl());

            Map<String, Object> resultMp = new HashMap<>();
            resultMp.put("commentInfo", mp);
            return new ToutiaoResult(0, "发表评论成功", resultMp);
        } else {
            return new ToutiaoResult(1, "评论内容不能为空哦");
        }
    }

    @Override
    public ToutiaoResult getCommentListByNewsId(Integer newsId) {
        List<Comment> commentList = commentMapper.getCommentListByNewsId(newsId);
        // 要返回的评论的详细信息
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Comment comment : commentList) {
            Map<String, Object> mp = ToutiaoUtils.object2Map(comment);
            User user = userMapper.selectByPrimaryKey(comment.getUserId());
            // 额外将用户的昵称和头像返回
            mp.put("userNickname", user.getNickname());
            mp.put("userHeadImageUrl", user.getHeadImageUrl());
            resultList.add(mp);
        }

        Map<String, Object> resultMp = new HashMap<>();
        resultMp.put("commentInfoList", resultList);
        return new ToutiaoResult(0, resultMp);
    }

    @Override
    public ToutiaoResult getCommentList(Integer page, Integer pageSize) {
        // sql中limit的索引值
        int pageIndex = (page - 1) * pageSize;
        List<Comment> commentList = commentMapper.getCommentList(pageIndex, pageSize);
        // 要返回的评论的详细信息
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Comment comment : commentList) {
            Map<String, Object> mp = ToutiaoUtils.object2Map(comment);
            User user = userMapper.selectByPrimaryKey(comment.getUserId());
            // 额外将用户的用户名返回
            mp.put("username", user.getUsername());
            // 将评论的资讯信息返回
            News news = newsMapper.selectByPrimaryKey(comment.getArticleId());
            mp.put("newsTitle", news.getNewsTitle());
            resultList.add(mp);
        }

        Map<String, Object> resultMp = new HashMap<>();
        resultMp.put("commentInfoList", resultList);
        return new ToutiaoResult(0, resultMp);
    }

    @Override
    public ToutiaoResult delCommentById(Integer commentId) {
        commentMapper.deleteByPrimaryKey(commentId);
        return new ToutiaoResult(0, "删除评论成功");
    }

    @Override
    public ToutiaoResult batchDelComment(List<Integer> delCommentIdList) {
        // 循环删除评论
        for (Integer commentId : delCommentIdList) {
            commentMapper.deleteByPrimaryKey(commentId);
        }
        return new ToutiaoResult(0, "批量删除评论成功");
    }

    @Override
    public ToutiaoResult getAllCommentCount() {
        int commentTotal = commentMapper.getAllCommentCount();

        Map<String, Object> mp = new HashMap<>();
        mp.put("commentTotal", commentTotal);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult drawCommentDataChart() {

        List<Integer> commentCountListInDay = new ArrayList<>();
        // 上周周一到上周周日的每天的评论
        for (int i = 6; i >= 0; i--) {
            List<Comment> commentListInDay = commentMapper.getCommentListInDay(i);
            commentCountListInDay.add(commentListInDay.size());
        }

        // 封装echarts所用的数据
        Map<String, Object> resultMp = new HashMap<>();

        Map<String, Object> titleMp = new HashMap<>();
        titleMp.put("text", "上周评论活跃详情");
        resultMp.put("title", titleMp);

        Map<String, Object> tooltipMp = new HashMap<>();
        tooltipMp.put("trigger", "axis");
        resultMp.put("tooltip", tooltipMp);

        Map<String, Object> legendMp = new HashMap<>();
        legendMp.put("data", new String[]{"评论数量"});
        resultMp.put("legend", legendMp);

        Map<String, Object> xAxisMp = new HashMap<>();
        xAxisMp.put("type", "category");
        xAxisMp.put("data", new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"});
        resultMp.put("xAxis", xAxisMp);

        Map<String, Object> yAxisMp = new HashMap<>();
        yAxisMp.put("type", "value");
        resultMp.put("yAxis", yAxisMp);

        Map<String, Object> seriesMp = new HashMap<>();
        seriesMp.put("name", "评论数量");
        seriesMp.put("type", "line");
        seriesMp.put("data", commentCountListInDay);
        resultMp.put("series", new Object[]{seriesMp});

        Map<String, Object> mp = new HashMap<>();
        mp.put("echartsData", resultMp);

        return new ToutiaoResult(0, mp);

    }

}
