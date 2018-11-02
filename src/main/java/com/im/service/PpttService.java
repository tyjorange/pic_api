package com.im.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.im.domain.RespResult;
import com.im.enums.RespResultEnum;
import com.im.mapper.PpttMapper;
import com.im.pojo.Pptt;
import com.im.utils.RespResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 品牌推图
 * Created by vostor on 2018/10/26.
 */
@Service
public class PpttService {
    @Autowired
    private PpttMapper mapper;

    /**
     * 添加
     *
     * @param pptt
     * @return
     */
    @Transactional
    public RespResult savePptt(Pptt pptt) {
        pptt.setGenTime(new Date());
        int insert = mapper.insert(pptt);
        if (insert == 0) {
            return RespResultUtil.success(RespResultEnum.ADD_UPDATE_FAILED);
        }
        return RespResultUtil.success(RespResultEnum.ADD_UPDATE_SUCCESS, pptt);
    }

    /**
     * 修改
     *
     * @param pptt
     * @return
     */
    @Transactional
    public RespResult updatePptt(Pptt pptt) {
        int update = mapper.updateByPrimaryKeySelective(pptt);
        if (update == 0) {
            return RespResultUtil.success(RespResultEnum.ADD_UPDATE_FAILED);
        }
        return RespResultUtil.success(RespResultEnum.ADD_UPDATE_SUCCESS, pptt);
    }

    /**
     * 删除
     *
     * @param id
     */
    public RespResult deletePptt(Integer id) {
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
    public RespResult queryPpttById(Integer id) {
        Pptt pptt = mapper.selectByPrimaryKey(id);
        if (pptt == null) {
            return RespResultUtil.success(RespResultEnum.EMPTY_RESULT);
        }
        return RespResultUtil.success(RespResultEnum.SUCCESS, pptt);
    }

    /**
     * 列表查询
     *
     * @param pptt 条件值
     * @return HTTP返回值封装对象
     */
    public RespResult queryPpttList(Pptt pptt) {
        Example example = new Example(Pptt.class);
        Example.Criteria criteria = example.createCriteria();
        if (pptt.getpType() != null) {
            criteria.andEqualTo("pType", pptt.getpType());//TODO
        }
        List<Pptt> ppttList = mapper.selectByExample(example);
        if (ppttList.size() == 0) {
            return RespResultUtil.success(RespResultEnum.EMPTY_RESULT);
        }
        Map<String, Object> map = new HashMap<>();

        map.put("account", ppttList.size());
        map.put("resultList", ppttList);

        return RespResultUtil.success(RespResultEnum.FIND_SUCCESS, map, null);
    }

    /**
     * 列表查询
     *
     * @param pptt     条件值
     * @param page     当前页
     * @param pageSize 每页条数
     * @return HTTP返回值封装对象
     */
    public RespResult queryPpttList(Pptt pptt, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Example example = new Example(Pptt.class);
        Example.Criteria criteria = example.createCriteria();
        if (pptt.getPicture() != null) {
            criteria.andEqualTo("picture", pptt.getPicture());
        }
        PageInfo<Pptt> pageInfo = new PageInfo<>(mapper.selectByExample(example));
        long total = pageInfo.getTotal();
        if (total == 0) {
            return RespResultUtil.success(RespResultEnum.EMPTY_RESULT);
        }
        return RespResultUtil.success(RespResultEnum.FIND_SUCCESS, pageInfo.getList(), null);
    }

}
