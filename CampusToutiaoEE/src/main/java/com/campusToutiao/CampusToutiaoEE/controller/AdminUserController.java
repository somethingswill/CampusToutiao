package com.campusToutiao.CampusToutiaoEE.controller;

import com.alibaba.fastjson.JSONObject;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUserController {

    @Autowired
    private IAdminUserService adminUserService;

    /**
     * 管理员登录
     * @param adminUsername
     * @param adminPasswd
     * @return
     */
    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam("adminUsername") String adminUsername,
                             @RequestParam("adminPasswd") String adminPasswd) {
        ToutiaoResult toutiaoResult = adminUserService.login(adminUsername, adminPasswd);
        return JSONObject.toJSONString(toutiaoResult);
    }

}
