package com.im.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

public class Pptt {
    public interface SimpView {
    }

    public interface DetailView extends SimpView {
    }

    @Id
    @Column(name = "p_id")
    private Integer pId;

    @Column(name = "p_type")
    private Integer pType;

    @Column(name = "gen_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date genTime;
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

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }
}