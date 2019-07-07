package com.campusToutiao.CampusToutiaoEE.service;

import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;

import java.util.List;

public interface INewsCategoryService {

    ToutiaoResult getAllNewsCategory();

    ToutiaoResult getAllCategoryCount();

    ToutiaoResult getCategoryList(Integer page, Integer pageSize);

    ToutiaoResult delCategoryById(Integer categoryId);

    ToutiaoResult batchDelCategory(List<Integer> delCategoryIdList);

    ToutiaoResult drawCategoryDataChart();

}
