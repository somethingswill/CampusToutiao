package com.campusToutiao.CampusToutiaoEE.service;

import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {

    ToutiaoResult regUser(String username, String passwd, String nickname, Integer gender, String tel, String profile);

    ToutiaoResult checkUsername(String username);

    ToutiaoResult login(String username, String passwd, boolean rememberME);

    ToutiaoResult autoLoginByTicket(HttpServletRequest request);

    ToutiaoResult logout(String ticket);

    ToutiaoResult updateUserInfo(Integer userId, String oldPasswd, String newPasswd, String tel, String profile, String nickname, Integer gender);

    ToutiaoResult updateUserHeadImage(Integer userId, String imgUrl);

    ToutiaoResult getAllUserCount();

    ToutiaoResult getUserList(Integer page, Integer pageSize);

    ToutiaoResult delUserById(Integer userId);

    ToutiaoResult batchDelUser(List<Integer> delUserIdList);

    ToutiaoResult drawActiveUserDataChart();

    ToutiaoResult drawUserGenderDataChart();

}
