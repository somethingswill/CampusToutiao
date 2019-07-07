package com.campusToutiao.CampusToutiaoEE.dao;

import com.campusToutiao.CampusToutiaoEE.entity.NewsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NewsCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsCategory record);

    int insertSelective(NewsCategory record);

    NewsCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsCategory record);

    int updateByPrimaryKey(NewsCategory record);

    List<NewsCategory> getAllNewsCategory();

    int getAllCategoryCount();

    List<NewsCategory> getCategoryList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);
}