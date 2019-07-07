package com.campusToutiao.CampusToutiaoEE.dao;

import com.campusToutiao.CampusToutiaoEE.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    User getUserByUsername(@Param("username") String username);

    int getAllUserCount();

    List<User> getUserList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    List<User> getAllUserList();
}