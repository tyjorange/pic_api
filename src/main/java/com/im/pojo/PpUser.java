package com.im.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pp_user")
public class PpUser {
    @Id
    @Column(name = "u_id")
    private Integer uId;

    private String username;

    private String password;

    @Column(name = "gen_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date genTime;

    /**
     * @return u_id
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * @param uId
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return gen_time
     */
    public Date getGenTime() {
        return genTime;
    }

    /**
     * @param genTime
     */
    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }
}