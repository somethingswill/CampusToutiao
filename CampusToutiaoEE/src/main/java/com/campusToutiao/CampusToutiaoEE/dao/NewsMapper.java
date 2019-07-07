package com.campusToutiao.CampusToutiaoEE.dao;

import com.campusToutiao.CampusToutiaoEE.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);

    List<News> getAllNewsListInPage(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    List<News> getNewsListByCategoryIdInPage(@Param("categoryId") Integer categoryId, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    List<News> getHotNewsList();

    int getAllNewsCount();

    int deleteNewsByCategoryId(@Param("categoryId") Integer categoryId);

    List<News> getNewsListByCategoryId(@Param("categoryId") Integer categoryId);
}