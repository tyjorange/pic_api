package com.im.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class Ppwh {
    @Id
    @Column(name = "w_id")
    @JsonIgnore
    private Integer wId;

    /**
     * 品牌文化
     */
    private Integer brandculture;

    /**
     * 图片路径
     */
    @Column(name = "pic_path")
    private String picPath;

    /**
     * @return w_id
     */
    public Integer getwId() {
        return wId;
    }

    /**
     * @param wId
     */
    public void setwId(Integer wId) {
        this.wId = wId;
    }

    /**
     * 获取品牌文化
     *
     * @return brandculture - 品牌文化
     */
    public Integer getBrandculture() {
        return brandculture;
    }

    /**
     * 设置品牌文化
     *
     * @param brandculture 品牌文化
     */
    public void setBrandculture(Integer brandculture) {
        this.brandculture = brandculture;
    }

    /**
     * 获取图片路径
     *
     * @return pic_path - 图片路径
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * 设置图片路径
     *
     * @param picPath 图片路径
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}