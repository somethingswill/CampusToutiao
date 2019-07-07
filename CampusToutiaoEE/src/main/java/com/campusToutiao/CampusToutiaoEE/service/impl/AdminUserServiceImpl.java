package com.campusToutiao.CampusToutiaoEE.service.impl;

import com.campusToutiao.CampusToutiaoEE.dao.AdminMapper;
import com.campusToutiao.CampusToutiaoEE.entity.Admin;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.IAdminUserService;
import com.campusToutiao.CampusToutiaoEE.utils.ToutiaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminUserServiceImpl implements IAdminUserService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ToutiaoResult login(String adminUsername, String adminPasswd) {
        Admin admin = adminMapper.getAdminUserByUsername(adminUsername);
        if (null == admin) {
            return new ToutiaoResult(1, "用户名错误");
        } else {
            if (admin.getPasswd().equals(ToutiaoUtils.MD5(adminPasswd + admin.getSalt()))) {
                Map<String, Object> mp = new HashMap<>();
                mp.put("adminUser", admin);
                return new ToutiaoResult(0,mp);
            } else {
                return new ToutiaoResult(1, "密码错误");
            }
        }
    }

}
