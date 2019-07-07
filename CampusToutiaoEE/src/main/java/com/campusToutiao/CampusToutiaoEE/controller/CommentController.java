package com.campusToutiao.CampusToutiaoEE.controller;

import com.alibaba.fastjson.JSONObject;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private ICommentService commentService;

    /**
     * 发表评论
     *
     * @param commentContent
     * @param newsId
     * @param userId
     * @return
     */
    @PostMapping("/postComment")
    public String postComment(@RequestParam("commentContent") String commentContent,
                              @RequestParam("newsId") Integer newsId,
                              @RequestParam("userId") Integer userId) {
        ToutiaoResult toutiaoResult = commentService.postComment(commentContent, newsId, userId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 根据资讯id查询资讯的所有评论
     *
     * @param newsId
     * @return
     */
    @GetMapping("/getCommentListByNewsId")
    public String getCommentListByNewsId(@RequestParam("newsId") Integer newsId) {
        ToutiaoResult toutiaoResult = commentService.getCommentListByNewsId(newsId);
        return JSONObject.toJSONString(toutiaoResult);
    }


    /**
     * 获取所有的评论
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/admin/getCommentList")
    public String getCommentList(@RequestParam("page") Integer page,
                                 @RequestParam("pageSize") Integer pageSize) {
        ToutiaoResult toutiaoResult = commentService.getCommentList(page, pageSize);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 根据评论id删除评论
     *
     * @param commentId
     * @return
     */
    @GetMapping("/admin/delComment")
    public String delCommentById(@RequestParam("commentId") Integer commentId) {
        ToutiaoResult toutiaoResult = commentService.delCommentById(commentId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 批量删除评论
     *
     * @param delCommentIdListStr
     * @return
     */
    @PostMapping("/admin/batchDelComment")
    public String batchDelComment(@RequestParam("delCommentIdListStr") String delCommentIdListStr) {
        // 将json数组字符串转化为字符串
        List<Integer> delCommentIdList = JSONObject.parseArray(delCommentIdListStr, Integer.class);
        ToutiaoResult toutiaoResult = commentService.batchDelComment(delCommentIdList);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 查询评论总数
     *
     * @return
     */
    @GetMapping("/admin/getAllCommentCount")
    public String getAllCommentCount() {
        ToutiaoResult toutiaoResult = commentService.getAllCommentCount();
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 绘制一周内全站资讯评论数的折线图，返回echarts所用的数据格式
     *
     * @return
     */
    @GetMapping("/admin/drawCommentDataChart")
    public String drawCommentDataChart() {
        ToutiaoResult toutiaoResult = commentService.drawCommentDataChart();
        return JSONObject.toJSONString(toutiaoResult);
    }

}
