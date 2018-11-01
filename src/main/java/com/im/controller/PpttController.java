package com.im.controller;

import com.im.domain.RespResult;
import com.im.enums.RespResultEnum;
import com.im.pojo.Pptt;
import com.im.service.PpttService;
import com.im.utils.RespResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.util.UUID;

/**
 * 品牌推图
 * Created by vostor on 2018/10/25.
 */
@RestController
public class PpttController {
    private final static Logger logger = LoggerFactory.getLogger(PpttController.class);
    @Autowired
    private PpttService ppttService;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.filePrefix}")
    private String filePrefix;

    @PostMapping(value = "/up_pptt")
    public RespResult upPpttImg(@RequestParam(value = "file") MultipartFile file) {
        if (!file.isEmpty()) {
            String image_file_name;
            try {
                image_file_name = UUID.randomUUID().toString().replace("-", "").toUpperCase() + ".jpg";
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(uploadFolder + image_file_name)));
                out.write(file.getBytes());
                out.flush();
                out.close();
//                Pptt pptt = new Pptt();
//                pptt.setTitle(title);
//                pptt.setPicture(filePrefix + image_file_name);
//                pptt.setpType(p_type);
//                ppttService.savePptt(pptt);
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

    /**
     * 条件查询 无分页
     *
     * @param pptt 条件值
     * @return HTTP返回值封装对象
     */
    @GetMapping(value = "/query_tt_pic")
    public RespResult queryPptt(Pptt pptt) {
        if (pptt == null) {
            pptt = new Pptt();
        }
        return ppttService.queryPpttList(pptt);
    }

    /**
     * 条件查询 分页
     *
     * @param pptt     条件值
     * @param page     当前页
     * @param pageSize 每页条数
     * @return HTTP返回值封装对象
     */
    @GetMapping(value = "/query_tt_pic_page")
    public RespResult queryPptt(Pptt pptt,
                                @RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pptt == null) {
            pptt = new Pptt();
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        return ppttService.queryPpttList(pptt, page, pageSize);
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/query_pptt/{id}")
    public RespResult queryppttID(@PathVariable("id") Integer id) {
        return ppttService.queryPpttById(id);
    }


    /**
     * 添加一条数据(使用对象代替参数列表)
     *
     * @param pptt          参数
     * @param bindingResult 表单验证
     * @return
     */
    @PostMapping(value = "/add_pptt")
    public RespResult addpptt(@Valid Pptt pptt, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return RespResultUtil.customError(1, bindingResult.getFieldError().getDefaultMessage());//TODO
        }
        return ppttService.savePptt(pptt);
    }


    /**
     * 通过ID更新
     *
     * @param id
     * @param pptt
     * @return
     */
    @PutMapping(value = "/pptt/{id}")
    public RespResult updateOne(@PathVariable("id") Integer id, Pptt pptt) {
        pptt.setpId(id);
        return ppttService.updatePptt(pptt);
    }

    /**
     * 通过ID删除
     *
     * @param id
     */
    @DeleteMapping(value = "/pptt/{id}")
    public RespResult deleteOne(@PathVariable("id") Integer id) {
        return ppttService.deletePptt(id);
    }

}
