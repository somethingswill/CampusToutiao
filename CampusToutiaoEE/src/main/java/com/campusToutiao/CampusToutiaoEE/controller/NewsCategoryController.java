package com.campusToutiao.CampusToutiaoEE.controller;

import com.alibaba.fastjson.JSONObject;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.INewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsCategoryController {

    @Autowired
    private INewsCategoryService newsCategoryService;

    /**
     * 查询全部分类信息
     *
     * @return
     */
    @GetMapping("/getAllNewsCategory")
    public String getAllNewsCategory() {
        ToutiaoResult toutiaoResult = newsCategoryService.getAllNewsCategory();
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 查询全部分类的数量
     *
     * @return
     */
    @GetMapping("/admin/getAllCategoryCount")
    public String getAllCategoryCount() {
        ToutiaoResult toutiaoResult = newsCategoryService.getAllCategoryCount();
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 查询分类信息
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/admin/getCategoryList")
    public String getCategoryList(@RequestParam("page") Integer page,
                                  @RequestParam("pageSize") Integer pageSize) {
        ToutiaoResult toutiaoResult = newsCategoryService.getCategoryList(page, pageSize);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 根据资讯类型id删除资讯分类
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/admin/delCategory")
    public String delCategory(@RequestParam("categoryId") Integer categoryId) {
        ToutiaoResult toutiaoResult = newsCategoryService.delCategoryById(categoryId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 批量删除资讯分类
     *
     * @param delCategoryIdListStr
     * @return
     */
    @PostMapping("/admin/batchDelCategory")
    public String batchDelCategory(@RequestParam("delCategoryIdListStr") String delCategoryIdListStr) {
        // 将json数组字符串转化为数组
        List<Integer> delCategoryIdList = JSONObject.parseArray(delCategoryIdListStr, Integer.class);
        ToutiaoResult toutiaoResult = newsCategoryService.batchDelCategory(delCategoryIdList);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 绘制资讯分类信息柱状图，返回echarts所用的数据格式
     *
     * @return
     */
    @GetMapping("/admin/drawCategoryDataChart")
    public String drawCategoryDataChart() {
        ToutiaoResult toutiaoResult = newsCategoryService.drawCategoryDataChart();
        return JSONObject.toJSONString(toutiaoResult);
    }

}
