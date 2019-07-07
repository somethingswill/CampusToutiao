package com.campusToutiao.CampusToutiaoEE.service;

import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;

public interface IAttitudeService {
    ToutiaoResult getNewsAttitude(Integer userId, Integer newsId);

    ToutiaoResult likeNews(Integer userId, Integer newsId);

    ToutiaoResult dislikeNews(Integer userId, Integer newsId);

    ToutiaoResult getCommentAttitude(Integer userId, Integer commentId);

    ToutiaoResult likeComment(Integer userId, Integer commentId);

    ToutiaoResult dislikeComment(Integer userId, Integer commentId);
}
