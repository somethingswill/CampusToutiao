package com.campusToutiao.CampusToutiaoEE.service;

import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;

import java.util.List;

public interface ICommentService {

    ToutiaoResult postComment(String commentContent, Integer newsId, Integer userId);

    ToutiaoResult getCommentListByNewsId(Integer newsId);

    ToutiaoResult getCommentList(Integer page, Integer pageSize);

    ToutiaoResult delCommentById(Integer commentId);

    ToutiaoResult batchDelComment(List<Integer> delCommentIdList);

    ToutiaoResult getAllCommentCount();

    ToutiaoResult drawCommentDataChart();
}
