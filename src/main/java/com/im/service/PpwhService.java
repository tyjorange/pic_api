package com.im.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.im.domain.RespResult;
import com.im.enums.RespResultEnum;
import com.im.mapper.PpwhMapper;
import com.im.pojo.Ppwh;
import com.im.utils.RespResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 品牌文化
 * Created by vostor on 2018/10/26.
 */
@Service
public class PpwhService {
    @Autowired
    private PpwhMapper mapper;

    /**
     * 添加
     *
     * @param ppwh
     * @return
     */
    @Transactional
    public RespResult savePpwh(Ppwh ppwh) {
        ppwh.setGenTime(new Date());
        int insert = mapper.insert(ppwh);
        if (insert == 0) {
            return RespResultUtil.success(RespResultEnum.ADD_UPDATE_FAILED);
        }
        return RespResultUtil.success(RespResultEnum.ADD_UPDATE_SUCCESS, ppwh);
    }

    /**
     * 修改
     *
     * @param ppwh
     * @return
     */
    @Transactional
    public RespResult updatePpwh(Ppwh ppwh) {
        int update = mapper.updateByPrimaryKeySelective(ppwh);
        if (update == 0) {
            return RespResultUtil.success(RespResultEnum.ADD_UPDATE_FAILED);
        }
        return RespResultUtil.success(RespResultEnum.ADD_UPDATE_SUCCESS, ppwh);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Transactional
    public RespResult deletePpwh(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        if (i == 0) {
            return RespResultUtil.success(RespResultEnum.DEL_FAILED);
        }
        return RespResultUtil.success(RespResultEnum.DEL_SUCCESS);
    }

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    public RespResult queryPpwhById(Integer id) {
        Ppwh ppwh = mapper.selectByPrimaryKey(id);
        if (ppwh == null) {
            return RespResultUtil.success(RespResultEnum.EMPTY_RESULT);
        }
        return RespResultUtil.success(RespResultEnum.SUCCESS, ppwh);
    }

    /**
     * 列表查询 无分页
     *
     * @param ppwh 条件值
     * @return HTTP返回值封装对象
     */
    public RespResult queryPpwhList(Ppwh ppwh) {
        Example example = new Example(Ppwh.class);
        Example.Criteria criteria = example.createCriteria();
        if (ppwh.getBrandculture() != null) {
            criteria.andEqualTo("brandculture", ppwh.getBrandculture());
        }
        List<Ppwh> ppwhList = mapper.selectByExample(example);
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
     * @param ppwh  条件值
     * @param page  当前页
     * @param limit 每页条数
     * @return HTTP返回值封装对象
     */
    public RespResult queryPpwhList(Ppwh ppwh, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        Example example = new Example(Ppwh.class);
        Example.Criteria criteria = example.createCriteria();
        if (ppwh.getBrandculture() != null) {
            criteria.andEqualTo("brandculture", ppwh.getBrandculture());
        }
        PageInfo<Ppwh> pageInfo = new PageInfo<>(mapper.selectByExample(example));
        long total = pageInfo.getTotal();
        if (total == 0) {
            return RespResultUtil.success(RespResultEnum.EMPTY_RESULT);
        }
        return RespResultUtil.success(RespResultEnum.FIND_SUCCESS, pageInfo.getList(), total);
    }

}
