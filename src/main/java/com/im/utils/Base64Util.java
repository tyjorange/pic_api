package com.im.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.nio.ByteBuffer;

/**
 * Created by vostor on 2018/11/2.
 */
public class Base64Util {
    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String key) throws Exception {
        byte[] bytes = (new BASE64Decoder()).decodeBuffer(key);
        return new String(bytes);

    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key.getBytes());
    }
}
