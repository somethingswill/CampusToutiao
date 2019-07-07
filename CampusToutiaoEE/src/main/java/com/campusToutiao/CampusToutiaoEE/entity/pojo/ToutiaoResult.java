package com.campusToutiao.CampusToutiaoEE.entity.pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * 返回的结果类
 */
public class ToutiaoResult implements Serializable {

    private static final long serialVersionUID = -6528526472969225517L;

    private Integer toutiaoCode;

    private String toutiaoMsg;

    private Map<String, Object> toutiaoData;

    public ToutiaoResult() {
    }

    public ToutiaoResult(Integer toutiaoCode, String toutiaoMsg) {
        this.toutiaoCode = toutiaoCode;
        this.toutiaoMsg = toutiaoMsg;
    }

    public ToutiaoResult(Integer toutiaoCode, Map<String, Object> toutiaoData) {
        this.toutiaoCode = toutiaoCode;
        this.toutiaoData = toutiaoData;
    }

    public ToutiaoResult(Integer toutiaoCode, String toutiaoMsg, Map<String, Object> toutiaoData) {
        this.toutiaoCode = toutiaoCode;
        this.toutiaoMsg = toutiaoMsg;
        this.toutiaoData = toutiaoData;
    }

    public Integer getToutiaoCode() {
        return toutiaoCode;
    }

    public void setToutiaoCode(Integer toutiaoCode) {
        this.toutiaoCode = toutiaoCode;
    }

    public String getToutiaoMsg() {
        return toutiaoMsg;
    }

    public void setToutiaoMsg(String toutiaoMsg) {
        this.toutiaoMsg = toutiaoMsg;
    }

    public Map<String, Object> getToutiaoData() {
        return toutiaoData;
    }

    public void setToutiaoData(Map<String, Object> toutiaoData) {
        this.toutiaoData = toutiaoData;
    }

}
