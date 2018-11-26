package com.im.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by vostor on 2018/11/21.
 */
@Controller
public class PdfController {
    private final static Logger logger = LoggerFactory.getLogger(PdfController.class);
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.filePrefix}")
    private String filePrefix;

    /**
     * @param name
     * @param model
     * @return
     */
    @GetMapping(value = "up/{name}.pdf")
    public String index(@PathVariable("name") String name, Model model) {
        logger.warn("fileName={}", name);
        model.addAttribute("filePath", "img/" + name + ".pdf");
        return "pdf";
    }

    /**
     * @param name
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/{name}.pdf")
    public String downloadFile(@PathVariable("name") String name,
                               HttpServletResponse response) throws UnsupportedEncodingException {
        //设置文件路径
        String realPath = uploadFolder;
        String fileName = name + ".pdf"; //下载的文件名
        File file = new File(realPath, fileName);
        // 如果文件名存在，则进行下载
        if (file.exists()) {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
//                System.out.println("Download the file successfully!");
            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("Download the file failed!");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("file not exited!");
        }
        return null;
    }
}
