package com.campusToutiao.CampusToutiaoEE.service;

import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;

import java.util.List;

public interface INewsService {

    ToutiaoResult postNews(Integer categoryId, String newsTitle, String newsContent, Integer userId);

    ToutiaoResult getNewsListByCategoryId(Integer categoryId, Integer page, Integer pageSize);

    ToutiaoResult getNewsByNewsId(Integer newsId);

    ToutiaoResult getHotNewsList();

    ToutiaoResult delNewsByNewsId(Integer newsId);

    ToutiaoResult batchDelNews(List<Integer> delNewsIdList);

    ToutiaoResult getAllNewsCount();

}
