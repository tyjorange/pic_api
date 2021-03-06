package com.im.domain;

/**
 * HTTP 请求返回的最外层对象
 * Created by vostor on 2018/10/26.
 */
public class RespResult {
    //错误码
    private Integer code;
    //提示信息
    private String msg;
    // 具体内容
    private Object data;

    private Long count;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
