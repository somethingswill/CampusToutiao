package com.campusToutiao.CampusToutiaoEE.controller;

import com.alibaba.fastjson.JSONObject;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.IAttitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttitudeController {

    @Autowired
    private IAttitudeService attitudeService;

    /**
     * 查询用户对某条资讯的态度
     *
     * @param userId
     * @param newsId
     * @return
     */
    @GetMapping("/getNewsAttitude")
    public String getNewsAttitude(@RequestParam("userId") Integer userId, @RequestParam("newsId") Integer newsId) {
        ToutiaoResult toutiaoResult = attitudeService.getNewsAttitude(userId, newsId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 用户喜欢资讯
     *
     * @param userId
     * @param newsId
     * @return
     */
    @GetMapping("/likeNews")
    public String likeNews(@RequestParam("userId") Integer userId, @RequestParam("newsId") Integer newsId) {
        ToutiaoResult toutiaoResult = attitudeService.likeNews(userId, newsId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 用户不喜欢资讯
     *
     * @param userId
     * @param newsId
     * @return
     */
    @GetMapping("/dislikeNews")
    public String dislikeNews(@RequestParam("userId") Integer userId, @RequestParam("newsId") Integer newsId) {
        ToutiaoResult toutiaoResult = attitudeService.dislikeNews(userId, newsId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 查询用户对某条评论的态度
     *
     * @param userId
     * @param commentId
     * @return
     */
    @GetMapping("/getCommentAttitude")
    public String getCommentAttitude(@RequestParam("userId") Integer userId, @RequestParam("commentId") Integer commentId) {
        ToutiaoResult toutiaoResult = attitudeService.getCommentAttitude(userId, commentId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 用户喜欢评论
     *
     * @param userId
     * @param commentId
     * @return
     */
    @GetMapping("/likeComment")
    public String likeComment(@RequestParam("userId") Integer userId, @RequestParam("commentId") Integer commentId) {
        ToutiaoResult toutiaoResult = attitudeService.likeComment(userId, commentId);
        return JSONObject.toJSONString(toutiaoResult);
    }

    /**
     * 用户不喜欢评论
     *
     * @param userId
     * @param commentId
     * @return
     */
    @GetMapping("/dislikeComment")
    public String dislikeComment(@RequestParam("userId") Integer userId, @RequestParam("commentId") Integer commentId) {
        ToutiaoResult toutiaoResult = attitudeService.dislikeComment(userId, commentId);
        return JSONObject.toJSONString(toutiaoResult);
    }

}
