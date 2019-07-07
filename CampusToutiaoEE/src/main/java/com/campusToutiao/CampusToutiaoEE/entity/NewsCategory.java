package com.campusToutiao.CampusToutiaoEE.entity;

import java.io.Serializable;

public class NewsCategory implements Serializable {

    private static final long serialVersionUID = 8994738447440996357L;

    private Integer id;

    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }
}