package com.campusToutiao.CampusToutiaoEE.service;

import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;

public interface IAdminUserService {

    ToutiaoResult login(String adminUsername, String adminPasswd);

}
