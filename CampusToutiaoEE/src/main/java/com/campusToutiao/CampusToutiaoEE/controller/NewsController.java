package com.campusToutiao.CampusToutiaoEE.controller;

import com.alibaba.fastjson.JSONObject;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.INewsService;
import com.campusToutiao.CampusToutiaoEE.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NewsController {

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private INewsService newsService;

    /**
     * 上传资讯内容中的图片
     *
     * @param newsImage
     * @return
     */
    @PostMapping("/uploadNewsImage")
    public String uploadHeaderPic(@RequestParam("newsImage") MultipartFile newsImage) {
        try {
            String newsImageUrl = uploadService.uploadImage(newsImage, "NEWS");
            if (null == newsImageUrl) {
                return JSONObject.toJSONString(new ToutiaoResult(1, "图片上传失败"));
            }
            Map<String, Object> mp = new HashMap<>();
            mp.put("newsImageUrl", newsImageUrl);
            return JSONObject.toJSONString(new ToutiaoResult(0, "图片上传成功", mp));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return JSONObject.toJSONString(new ToutiaoResult(1, "图片上传失败"));
        }
    }

    /**
     * 发布资讯
     *
     * @param categoryId
     * @param newsTitle
     * @param newsContent
     * @param userId
     * @return
     */
    @PostMapping("/postNews")
    public String postNews(@RequestParam("categoryId") Integer categoryId,
                           @RequestParam("newsTitle") String newsTitle,
                           @RequestParam("newsContent") String newsContent,
                           @RequestParam("userId") Integer userId) {
        ToutiaoResult toutiaoResult = newsService.postNews(categoryId, newsTitle, newsContent, userId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 根据某种资讯类型查询资讯列表
     *
     * @param categoryId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/getNewsListByCategoryId")
    public String getNewsListByCategoryId(@RequestParam("categoryId") Integer categoryId,
                                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ToutiaoResult toutiaoResult = newsService.getNewsListByCategoryId(categoryId, page, pageSize);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 根据资讯id查询资讯信息
     *
     * @param newsId
     * @return
     */
    @GetMapping("/getNewsByNewsId")
    public String getNewsByNewsId(@RequestParam("newsId") Integer newsId) {
        ToutiaoResult toutiaoResult = newsService.getNewsByNewsId(newsId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 查询热门文章，默认返回评论数前十的文章
     *
     * @return
     */
    @GetMapping("/getHotNewsList")
    public String getHotNewsList() {
        ToutiaoResult toutiaoResult = newsService.getHotNewsList();
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 管理员删除单条资讯
     *
     * @param newsId
     * @return
     */
    @GetMapping("/admin/delNews")
    public String delNewsByNewsId(@RequestParam("newsId") Integer newsId) {
        ToutiaoResult toutiaoResult = newsService.delNewsByNewsId(newsId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 管理员批量删除资讯
     *
     * @param delNewsIdListStr
     * @return
     */
    @PostMapping("/admin/batchDelNews")
    public String batchDelNews(@RequestParam("delNewsIdListStr") String delNewsIdListStr) {
        // 将json数组字符串转化为数组
        List<Integer> delNewsIdList = JSONObject.parseArray(delNewsIdListStr, Integer.class);
        ToutiaoResult toutiaoResult = newsService.batchDelNews(delNewsIdList);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 查询资讯总数
     *
     * @return
     */
    @GetMapping("/admin/getAllNewsCount")
    public String getAllNewsCount() {
        ToutiaoResult toutiaoResult = newsService.getAllNewsCount();
        return JSONObject.toJSONString(toutiaoResult);
    }

}
