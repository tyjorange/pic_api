package com.im.controller;

import com.im.domain.RespResult;
import com.im.enums.RespResultEnum;
import com.im.utils.RespResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * 图片上传controller
 * Created by vostor on 2018/11/2.
 */
@RestController
public class ImageController {
    private final static Logger logger = LoggerFactory.getLogger(ImageController.class);
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.filePrefix}")
    private String filePrefix;

    @PostMapping(value = "/up_img")
    public RespResult upImg(@RequestParam(value = "file") MultipartFile file) {
        if (!file.isEmpty()) {
            String image_file_name;
            try {
                image_file_name = UUID.randomUUID().toString().replace("-", "").toUpperCase() + ".jpg";
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(uploadFolder + image_file_name)));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage());
                return RespResultUtil.sysError(RespResultEnum.SYS_EXCEPTION);
            } catch (IOException e) {
                logger.error(e.getMessage());
                return RespResultUtil.sysError(RespResultEnum.SYS_EXCEPTION);
            }
            return RespResultUtil.success(RespResultEnum.SUCCESS, filePrefix + image_file_name);
        } else {
            return RespResultUtil.sysError(RespResultEnum.SYS_EXCEPTION);
        }
    }
}
