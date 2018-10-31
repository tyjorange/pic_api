package com.im.handle;

import com.im.domain.RespResult;
import com.im.enums.RespResultEnum;
import com.im.exception.PicException;
import com.im.utils.RespResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * Created by vostor on 2018/10/26.
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 捕获Exception异常
     *
     * @param e
     * @return HTTP返回对象
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespResult handle(Exception e) {
        if (e instanceof PicException) {
            PicException de = (PicException) e;
            return RespResultUtil.customError(de);
        }
        logger.error("[系统异常]", e);
        return RespResultUtil.sysError(RespResultEnum.SYS_EXCEPTION);
    }
}
