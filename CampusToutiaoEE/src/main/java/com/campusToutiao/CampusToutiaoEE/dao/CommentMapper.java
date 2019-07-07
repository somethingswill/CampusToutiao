package com.campusToutiao.CampusToutiaoEE.dao;

import com.campusToutiao.CampusToutiaoEE.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> getCommentListByNewsId(@Param("newsId") Integer newsId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    int delCommentByNewsId(@Param("newsId") Integer newsId);

    List<Comment> getCommentList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    int getAllCommentCount();

    List<HashMap<String, Object>> getTop10ActiveUser();

    List<Comment> getCommentListInDay(@Param("week") Integer week);
}