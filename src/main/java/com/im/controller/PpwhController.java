package com.im.controller;

import com.im.domain.RespResult;
import com.im.enums.RespResultEnum;
import com.im.pojo.Ppwh;
import com.im.service.PpwhService;
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
 * 品牌文化
 * Created by vostor on 2018/10/25.
 */
@RestController
public class PpwhController {
    //    private final static Logger logger = LoggerFactory.getLogger(PpwhController.class);
    @Autowired
    private PpwhService ppwhService;

    /**
     * 条件查询 无分页
     *
     * @param ppwh 条件值
     * @return HTTP返回值封装对象
     */
    @GetMapping(value = "/query_wh_pic")
    public RespResult queryPpwh(Ppwh ppwh) {
        if (ppwh == null) {
            ppwh = new Ppwh();
        }
        return ppwhService.queryPpwhList(ppwh);
    }

    /**
     * 条件查询
     *
     * @param ppwh     条件值
     * @param page     当前页
     * @param pageSize 每页条数
     * @return HTTP返回值封装对象
     */
    @GetMapping(value = "/query_wh_pic_page")
    public RespResult queryPpwh(Ppwh ppwh,
                                @RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (ppwh == null) {
            ppwh = new Ppwh();
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        return ppwhService.queryPpwhList(ppwh, page, pageSize);
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/query_ppwh/{id}")
    public RespResult queryPpwhID(@PathVariable("id") Integer id) {
        return ppwhService.queryPpwhById(id);
    }


    /**
     * 添加一条数据(使用对象代替参数列表)
     *
     * @param ppwh          参数
     * @param bindingResult 表单验证
     * @return
     */
    @PostMapping(value = "/add_ppwh")
    public RespResult addPpwh(@Valid Ppwh ppwh, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return RespResultUtil.customError(1, bindingResult.getFieldError().getDefaultMessage());//TODO
        }
        return ppwhService.savePpwh(ppwh);
    }


    /**
     * 通过ID更新
     *
     * @param id
     * @param ppwh
     * @return
     */
    @PutMapping(value = "/ppwh/{id}")
    public RespResult updateOne(@PathVariable("id") Integer id, Ppwh ppwh) {
        ppwh.setwId(id);
        return ppwhService.updatePpwh(ppwh);
    }

    /**
     * 通过ID删除
     *
     * @param id
     */
    @DeleteMapping(value = "/ppwh/{id}")
    public RespResult deleteOne(@PathVariable("id") Integer id) {
        return ppwhService.deletePpwh(id);
    }

}
