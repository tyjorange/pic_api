package com.im.enums;

/**
 * Created by vostor on 2018/10/26.
 */
public enum RespResultEnum {
    FIND_SUCCESS(0, "查询成功"),
    FIND_FAILED(1, "查询失败"),
    ADD_UPDATE_SUCCESS(20, "添加/修改成功"),
    ADD_UPDATE_FAILED(21, "添加/修改失败"),
    ADD_UPDATE_DUPLICATE(22, "添加/修改失败-编码重复"),
    DEL_SUCCESS(30, "删除成功"),
    DEL_FAILED(31, "删除失败"),
    WRONG_PARAMETER_FORMAT(4, "参数格式错误"),
    WRONG_PARAMETER_VALUE(5, "参数值错误"),
    DATABASE_EXCEPTION(6, "数据库操作失败"),
    EMPTY_RESULT(121, "无结果"),
    AUTH_FAILED(122, "权限错误"),
    SUCCESS(100, "操作成功"),
    FAILED(101, "操作失败"),
    LOGIN_SUCCESS(200, "登录成功"),
    LOGIN_FAILED(201, "登录失败"),
    SYS_EXCEPTION(-1, "服务器内部错误");

    private Integer code;
    private String msg;

    RespResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
