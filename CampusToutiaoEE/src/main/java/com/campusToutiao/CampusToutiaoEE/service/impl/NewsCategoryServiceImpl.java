package com.campusToutiao.CampusToutiaoEE.service.impl;

import com.campusToutiao.CampusToutiaoEE.dao.NewsCategoryMapper;
import com.campusToutiao.CampusToutiaoEE.dao.NewsMapper;
import com.campusToutiao.CampusToutiaoEE.entity.News;
import com.campusToutiao.CampusToutiaoEE.entity.NewsCategory;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.INewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsCategoryServiceImpl implements INewsCategoryService {

    @Autowired
    private NewsCategoryMapper newsCategoryMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public ToutiaoResult getAllNewsCategory() {
        List<NewsCategory> newsCategoryList = newsCategoryMapper.getAllNewsCategory();
        Map<String, Object> mp = new HashMap<>();
        mp.put("newsCategoryList", newsCategoryList);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult getAllCategoryCount() {
        int categoryTotal = newsCategoryMapper.getAllCategoryCount();

        Map<String, Object> mp = new HashMap<>();
        mp.put("categoryTotal", categoryTotal);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult getCategoryList(Integer page, Integer pageSize) {
        int pageIndex = (page - 1) * pageSize;
        List<NewsCategory> categoryList = newsCategoryMapper.getCategoryList(pageIndex, pageSize);
        Map<String, Object> mp = new HashMap<>();
        mp.put("categoryList", categoryList);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult delCategoryById(Integer categoryId) {
        newsCategoryMapper.deleteByPrimaryKey(categoryId);
        // 把该分类下的所有资讯删除
        newsMapper.deleteNewsByCategoryId(categoryId);
        return new ToutiaoResult(0, "删除分类成功");
    }

    @Override
    public ToutiaoResult batchDelCategory(List<Integer> delCategoryIdList) {
        // 循环删除
        for (Integer categoryId : delCategoryIdList) {
            newsCategoryMapper.deleteByPrimaryKey(categoryId);
            // 把该分类下的所有资讯删除
            newsMapper.deleteNewsByCategoryId(categoryId);
        }
        return new ToutiaoResult(0, "批量删除分类成功");
    }

    @Override
    public ToutiaoResult drawCategoryDataChart() {

        List<String> xAxisData = new ArrayList<>();
        List<Integer> seriesData = new ArrayList<>();

        List<NewsCategory> newsCategoryList = newsCategoryMapper.getAllNewsCategory();
        for (NewsCategory category : newsCategoryList) {
            xAxisData.add(category.getCategoryName());
            // 查询某种分类下的所有资讯
            List<News> newsList = newsMapper.getNewsListByCategoryId(category.getId());
            seriesData.add(newsList.size());
        }

        // 封装echarts所用的数据
        Map<String, Object> resultMp = new HashMap<>();

        Map<String, Object> titleMp = new HashMap<>();
        titleMp.put("text", "资讯种类分布");
        resultMp.put("title", titleMp);

        Map<String, Object> tooltipMp = new HashMap<>();
        tooltipMp.put("trigger", "axis");
        Map<String, Object> axisPointerMp = new HashMap<>();
        axisPointerMp.put("type", "shadow");
        tooltipMp.put("axisPointer", axisPointerMp);
        resultMp.put("tooltip", tooltipMp);

        Map<String, Object> legendMp = new HashMap<>();
        legendMp.put("data", new String[]{"资讯数量"});
        resultMp.put("legend", legendMp);

        Map<String, Object> xAxisMp = new HashMap<>();
        xAxisMp.put("data", xAxisData);
        resultMp.put("xAxis", xAxisMp);

        Map<String, Object> yAxisMp = new HashMap<>();
        resultMp.put("yAxis", yAxisMp);

        Map<String, Object> seriesMp = new HashMap<>();
        seriesMp.put("name", "资讯数量");
        seriesMp.put("type", "bar");
        seriesMp.put("data", seriesData);
        resultMp.put("series", new Object[]{seriesMp});

        Map<String, Object> mp = new HashMap<>();
        mp.put("echartsData", resultMp);

        return new ToutiaoResult(0, mp);
    }

}
