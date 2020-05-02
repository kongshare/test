package com.nanfeng.service.co;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nanfeng.TbSpecGroupDTO;
import com.nanfeng.TbSpecParamDTO;
import com.nanfeng.pojo.TbSpecGroup;
import com.nanfeng.pojo.TbSpecParam;
import com.nanfeng.service.impl.TbSpecGroupServiceImpl;
import com.nanfeng.service.impl.TbSpecParamServiceImpl;
import com.share.enums.ExceptionResult;
import com.share.exception.NanfengException;
import com.share.utils.BeanHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecService {

    @Autowired
    TbSpecGroupServiceImpl tbSpecGroupService;

    /*
    查询规格组
     */
    public List<TbSpecGroupDTO> findByCidSpecGroup(Long cid) {

       QueryWrapper<TbSpecGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbSpecGroup::getCid, cid);

        List<TbSpecGroup> list = tbSpecGroupService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            throw new NanfengException(ExceptionResult.SPEC_NOT_FOUND);
        }
        return BeanHelper.copyWithCollection(list, TbSpecGroupDTO.class);

    }

    @Autowired
    TbSpecParamServiceImpl tbSpecParamService;
    /*
    查询的规格参数
     */
    public List<TbSpecParamDTO> findSpecPaamByGid(Long gid, Long cid) {
        QueryWrapper<TbSpecParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbSpecParam::getGroupId, gid).or().eq(TbSpecParam::getCid, cid);

        List<TbSpecParam> list = tbSpecParamService.list(queryWrapper);

        return BeanHelper.copyWithCollection(list, TbSpecParamDTO.class);
    }

    /*
    新增规格组
     */
    public Boolean insertSpecGroup(TbSpecGroup group) {


        boolean b = tbSpecGroupService.save(group);
        if (!b) {
            throw new NanfengException(ExceptionResult.INSERT_OPERATION_FAIL);
        }
        return b;
    }
}
