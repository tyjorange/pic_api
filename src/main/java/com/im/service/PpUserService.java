package com.im.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.im.domain.RespResult;
import com.im.enums.RespResultEnum;
import com.im.mapper.PpUserMapper;
import com.im.pojo.PpUser;
import com.im.utils.RespResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 用户
 * Created by vostor on 2018/11/2.
 */
@Service
public class PpUserService {
    @Autowired
    private PpUserMapper mapper;

    /**
     * 用户登陆验证
     *
     * @param ppUser
     * @return
     */
    public RespResult login(PpUser ppUser) {
        Example example = new Example(PpUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (ppUser.getUsername() != null && !ppUser.getUsername().isEmpty()) {
            criteria.andEqualTo("username", ppUser.getUsername());
        } else {
            return RespResultUtil.success(RespResultEnum.LOGIN_FAILED);
        }
        if (ppUser.getPassword() != null && !ppUser.getPassword().isEmpty()) {
            criteria.andEqualTo("password", ppUser.getPassword());
        } else {
            return RespResultUtil.success(RespResultEnum.LOGIN_FAILED);
        }
        List<PpUser> ppUserList = mapper.selectByExample(example);
        if (ppUserList.size() == 0) {
            return RespResultUtil.success(RespResultEnum.LOGIN_FAILED);
        }
        return RespResultUtil.success(RespResultEnum.LOGIN_SUCCESS);
    }

    /**
     * 添加
     *
     * @param ppUser
     * @return
     */
    @Transactional
    public RespResult savePpUser(PpUser ppUser) {
        ppUser.setGenTime(new Date());
        int insert = mapper.insert(ppUser);
        if (insert == 0) {
            return RespResultUtil.success(RespResultEnum.ADD_UPDATE_FAILED);
        }
        return RespResultUtil.success(RespResultEnum.ADD_UPDATE_SUCCESS, ppUser);
    }

    /**
     * 删除
     *
     * @param id
     */
    public RespResult deletePpUser(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        if (i == 0) {
            return RespResultUtil.success(RespResultEnum.DEL_FAILED);
        }
        return RespResultUtil.success(RespResultEnum.DEL_SUCCESS);
    }

    /**
     * 修改
     *
     * @param ppwh
     * @return
     */
    @Transactional
    public RespResult updatePpUser(PpUser ppwh) {
        int update = mapper.updateByPrimaryKeySelective(ppwh);
        if (update == 0) {
            return RespResultUtil.success(RespResultEnum.ADD_UPDATE_FAILED);
        }
        return RespResultUtil.success(RespResultEnum.ADD_UPDATE_SUCCESS, ppwh);
    }

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    public RespResult queryPpUserById(Integer id) {
        PpUser ppUser = mapper.selectByPrimaryKey(id);
        if (ppUser == null) {
            return RespResultUtil.success(RespResultEnum.EMPTY_RESULT);
        }
        return RespResultUtil.success(RespResultEnum.SUCCESS, ppUser);
    }


    /**
     * 列表查询 无分页
     *
     * @param ppUser 条件值
     * @return HTTP返回值封装对象
     */
    public RespResult queryPpUserList(PpUser ppUser) {
        Example example = new Example(PpUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (ppUser.getUsername() != null) {
            criteria.andLike("username", "%" + ppUser.getUsername() + "%");
        }
        List<PpUser> ppwhList = mapper.selectByExample(example);
        if (ppwhList.size() == 0) {
            return RespResultUtil.success(RespResultEnum.EMPTY_RESULT);
        }
//        ArrayList<String> arrayList = new ArrayList<>();
//        for (Ppwh phwh : ppwhList) {
//            arrayList.add(phwh.getPicPath());
//        }
//        return RespResultUtil.success(RespResultEnum.FIND_SUCCESS, arrayList, new Integer(arrayList.size()).longValue());
        return RespResultUtil.success(RespResultEnum.FIND_SUCCESS, ppwhList, new Integer(ppwhList.size()).longValue());
    }

    /**
     * 列表查询 分页
     *
     * @param ppUser 条件值
     * @param page   当前页
     * @param limit  每页条数
     * @return HTTP返回值封装对象
     */
    public RespResult queryPpUserList(PpUser ppUser, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        Example example = new Example(PpUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (ppUser.getUsername() != null) {
            criteria.andLike("username", "%" + ppUser.getUsername() + "%");
        }
        PageInfo<PpUser> pageInfo = new PageInfo<>(mapper.selectByExample(example));
        long total = pageInfo.getTotal();
        if (total == 0) {
            return RespResultUtil.success(RespResultEnum.EMPTY_RESULT);
        }
        return RespResultUtil.success(RespResultEnum.FIND_SUCCESS, pageInfo.getList(), total);
    }
}
