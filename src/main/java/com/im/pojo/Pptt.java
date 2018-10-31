package com.im.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class Pptt {
    @Id
    @Column(name = "p_id")
    @JsonIgnore
    private Integer pId;

    @Column(name = "p_type")
    @JsonIgnore
    private Integer pType;

    /**
     * 图片路径
     */
    private String picture;

    /**
     * 图片推文
     */
    private String title;

    /**
     * @return p_id
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * @param pId
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }

    /**
     * @return p_type
     */
    public Integer getpType() {
        return pType;
    }

    /**
     * @param pType
     */
    public void setpType(Integer pType) {
        this.pType = pType;
    }

    /**
     * 获取图片路径
     *
     * @return picture - 图片路径
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置图片路径
     *
     * @param picture 图片路径
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取图片推文
     *
     * @return title - 图片推文
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置图片推文
     *
     * @param title 图片推文
     */
    public void setTitle(String title) {
        this.title = title;
    }
}