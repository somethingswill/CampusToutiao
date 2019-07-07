package com.campusToutiao.CampusToutiaoEE.service.impl;

import com.campusToutiao.CampusToutiaoEE.dao.CommentMapper;
import com.campusToutiao.CampusToutiaoEE.dao.NewsMapper;
import com.campusToutiao.CampusToutiaoEE.entity.Comment;
import com.campusToutiao.CampusToutiaoEE.entity.News;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.IAttitudeService;
import com.campusToutiao.CampusToutiaoEE.utils.JedisAdapter;
import com.campusToutiao.CampusToutiaoEE.utils.ToutiaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AttitudeServiceImpl implements IAttitudeService {

    @Autowired
    private JedisAdapter jedisAdapter;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ToutiaoResult getNewsAttitude(Integer userId, Integer newsId) {

        String redisNewsLikeKey = ToutiaoUtils.NEWS_LIKE_PREFIX + newsId;
        String redisNewsDislikeKey = ToutiaoUtils.NEWS_DISLIKE_PREFIX + newsId;

        boolean likeFlag = jedisAdapter.sismember(redisNewsLikeKey, String.valueOf(userId));
        boolean dislikeFlag = jedisAdapter.sismember(redisNewsDislikeKey, String.valueOf(userId));

        // 持中性态度
        int attitude = 0;
        if (likeFlag) {
            // 喜欢
            attitude = 1;
        } else if (dislikeFlag) {
            // 不喜欢
            attitude = -1;
        }

        Map<String, Object> mp = new HashMap<>();
        mp.put("attitude", attitude);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult likeNews(Integer userId, Integer newsId) {
        String redisNewsLikeKey = ToutiaoUtils.NEWS_LIKE_PREFIX + newsId;
        String redisNewsDislikeKey = ToutiaoUtils.NEWS_DISLIKE_PREFIX + newsId;
        // 在redis中喜欢的集合中添加，同时在不喜欢的集合中删除
        jedisAdapter.sadd(redisNewsLikeKey, String.valueOf(userId));
        jedisAdapter.srem(redisNewsDislikeKey, String.valueOf(userId));

        // 更新news中的获赞数和获踩数
        News news = newsMapper.selectByPrimaryKey(newsId);
        news.setLikeCount((int) jedisAdapter.scard(redisNewsLikeKey));
        news.setDislikeCount((int) jedisAdapter.scard(redisNewsDislikeKey));
        newsMapper.updateByPrimaryKeySelective(news);

        Map<String, Object> mp = new HashMap<>();
        mp.put("news", news);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult dislikeNews(Integer userId, Integer newsId) {
        String redisNewsLikeKey = ToutiaoUtils.NEWS_LIKE_PREFIX + newsId;
        String redisNewsDislikeKey = ToutiaoUtils.NEWS_DISLIKE_PREFIX + newsId;
        // 在redis中不喜欢的集合中添加，同时在喜欢的集合中删除
        jedisAdapter.sadd(redisNewsDislikeKey, String.valueOf(userId));
        jedisAdapter.srem(redisNewsLikeKey, String.valueOf(userId));

        // 更新news中的获赞数和获踩数
        News news = newsMapper.selectByPrimaryKey(newsId);
        news.setLikeCount((int) jedisAdapter.scard(redisNewsLikeKey));
        news.setDislikeCount((int) jedisAdapter.scard(redisNewsDislikeKey));
        newsMapper.updateByPrimaryKeySelective(news);

        Map<String, Object> mp = new HashMap<>();
        mp.put("news", news);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult getCommentAttitude(Integer userId, Integer commentId) {

        String redisCommentLikeKey = ToutiaoUtils.COMMENT_LIKE_PREFIX + commentId;
        String redisCommentDislikeKey = ToutiaoUtils.COMMENT_DISLIKE_PREFIX + commentId;

        boolean likeFlag = jedisAdapter.sismember(redisCommentLikeKey, String.valueOf(userId));
        boolean dislikeFlag = jedisAdapter.sismember(redisCommentDislikeKey, String.valueOf(userId));

        // 持中性态度
        int attitude = 0;
        if (likeFlag) {
            // 喜欢
            attitude = 1;
        } else if (dislikeFlag) {
            // 不喜欢
            attitude = -1;
        }

        Map<String, Object> mp = new HashMap<>();
        mp.put("attitude", attitude);
        return new ToutiaoResult(0, mp);

    }

    @Override
    public ToutiaoResult likeComment(Integer userId, Integer commentId) {
        String redisCommentLikeKey = ToutiaoUtils.COMMENT_LIKE_PREFIX + commentId;
        String redisCommentDislikeKey = ToutiaoUtils.COMMENT_DISLIKE_PREFIX + commentId;
        // 在redis中喜欢的集合中添加，同时在不喜欢的集合中删除
        jedisAdapter.sadd(redisCommentLikeKey, String.valueOf(userId));
        jedisAdapter.srem(redisCommentDislikeKey, String.valueOf(userId));

        // 更新comment中的获赞数和获踩数
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        comment.setLikeCount((int) jedisAdapter.scard(redisCommentLikeKey));
        comment.setDislikeCount((int) jedisAdapter.scard(redisCommentDislikeKey));
        commentMapper.updateByPrimaryKeySelective(comment);

        Map<String, Object> mp = new HashMap<>();
        mp.put("comment", comment);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult dislikeComment(Integer userId, Integer commentId) {
        String redisCommentLikeKey = ToutiaoUtils.COMMENT_LIKE_PREFIX + commentId;
        String redisCommentDislikeKey = ToutiaoUtils.COMMENT_DISLIKE_PREFIX + commentId;
        // 在redis中喜欢的集合中添加，同时在不喜欢的集合中删除
        jedisAdapter.sadd(redisCommentDislikeKey, String.valueOf(userId));
        jedisAdapter.srem(redisCommentLikeKey, String.valueOf(userId));

        // 更新comment中的获赞数和获踩数
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        comment.setLikeCount((int) jedisAdapter.scard(redisCommentLikeKey));
        comment.setDislikeCount((int) jedisAdapter.scard(redisCommentDislikeKey));
        commentMapper.updateByPrimaryKeySelective(comment);

        Map<String, Object> mp = new HashMap<>();
        mp.put("comment", comment);
        return new ToutiaoResult(0, mp);
    }

}
