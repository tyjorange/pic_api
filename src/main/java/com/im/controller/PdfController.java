package com.im.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping(value = "/{name}.pdf")
    public String index(@PathVariable("name") String name, Model model) {
        logger.warn("fileName={}", name);
        model.addAttribute("filePath", "img/" + name + ".pdf");
        return "pdf";
    }
}
