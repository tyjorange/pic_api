package com.im.utils;

import com.im.domain.RespResult;
import com.im.enums.RespResultEnum;
import com.im.exception.PicException;

/**
 * 用于HTTP返回的 封装对象
 * Created by vostor on 2018/10/26.
 */
public class RespResultUtil {
    /**
     * 正常返回结果(带结果集)
     *
     * @param respResultEnum
     * @param object
     * @param total
     * @return
     */
    public static RespResult success(RespResultEnum respResultEnum, Object object, Long total) {
        RespResult result = new RespResult();
        result.setCode(respResultEnum.getCode());
        result.setMsg(respResultEnum.getMsg());
        result.setData(object);
        result.setCount(total);
        return result;
    }

    /**
     * 正常返回结果(单条结果集)
     *
     * @param respResultEnum
     * @return
     */
    public static RespResult success(RespResultEnum respResultEnum, Object object) {
        return success(respResultEnum, object, null);
    }

    /**
     * 正常返回结果(不带结果集)
     *
     * @param respResultEnum
     * @return
     */
    public static RespResult success(RespResultEnum respResultEnum) {
        return success(respResultEnum, null, null);
    }

    /**
     * 系统异常
     *
     * @param respResultEnum
     * @return
     */
    public static RespResult sysError(RespResultEnum respResultEnum) {
        RespResult result = new RespResult();
        result.setCode(respResultEnum.getCode());
        result.setMsg(respResultEnum.getMsg());
        return result;
    }

    /**
     * 自定义异常
     *
     * @param de
     * @return
     */
    public static RespResult customError(PicException de) {
        RespResult result = new RespResult();
        result.setCode(de.getCode());
        result.setMsg(de.getMessage());
        return result;
    }

    /**
     * 自定义异常 （表单验证异常）
     *
     * @param code
     * @param msg
     * @return
     */
    public static RespResult customError(Integer code, String msg) {
        RespResult result = new RespResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
