package com.im.controller;

import com.im.domain.RespResult;
import com.im.pojo.PpUser;
import com.im.service.PpUserService;
import com.im.utils.RespResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户
 * Created by vostor on 2018/11/2.
 */
@RestController
public class PpUserController {
    @Autowired
    private PpUserService ppUserService;

    /**
     * 用户登陆验证
     *
     * @param ppUser
     * @return
     */
    @GetMapping(value = "/user_login")
    public RespResult login(PpUser ppUser) {
        return ppUserService.login(ppUser);
    }

    /**
     * 条件查询 无分页
     *
     * @param ppUser 条件值
     * @return HTTP返回值封装对象
     */
    @GetMapping(value = "/query_user")
    public RespResult queryPpUser(PpUser ppUser) {
        if (ppUser == null) {
            ppUser = new PpUser();
        }
        return ppUserService.queryPpUserList(ppUser);
    }

    /**
     * 条件查询 分页
     *
     * @param ppwh  条件值
     * @param page  当前页
     * @param limit 每页条数
     * @return HTTP返回值封装对象
     */
    @GetMapping(value = "/query_user_page")
    public RespResult queryPpUser(PpUser ppwh,
                                  @RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "limit", required = false) Integer limit) {
        if (ppwh == null) {
            ppwh = new PpUser();
        }
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 5;
        }
        return ppUserService.queryPpUserList(ppwh, page, limit);
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/query_user/{id}")
    public RespResult queryPpwhID(@PathVariable("id") Integer id) {
        return ppUserService.queryPpUserById(id);
    }

    /**
     * 添加一条数据(使用对象代替参数列表)
     *
     * @param ppUser        参数
     * @param bindingResult 表单验证
     * @return
     */
    @PostMapping(value = "/add_user")
    public RespResult addPpUser(@Valid PpUser ppUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return RespResultUtil.customError(1, bindingResult.getFieldError().getDefaultMessage());//TODO
        }
        return ppUserService.savePpUser(ppUser);
    }

    /**
     * 通过ID更新
     *
     * @param id
     * @param ppUser
     * @return
     */
    @PutMapping(value = "/user/{id}")
    public RespResult updateOne(@PathVariable("id") Integer id, PpUser ppUser) {
        ppUser.setuId(id);
        return ppUserService.updatePpUser(ppUser);
    }

    /**
     * 通过ID删除
     *
     * @param id
     */
    @DeleteMapping(value = "/user/{id}")
    public RespResult deleteOne(@PathVariable("id") Integer id) {
        return ppUserService.deletePpUser(id);
    }


}
