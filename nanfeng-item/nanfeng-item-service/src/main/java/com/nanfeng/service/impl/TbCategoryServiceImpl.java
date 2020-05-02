package com.nanfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nanfeng.TbCategoryDtO;
import com.nanfeng.pojo.TbCategory;
import com.nanfeng.mapper.TbCategoryMapper;
import com.nanfeng.service.TbCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.enums.ExceptionResult;
import com.share.exception.NanfengException;
import com.share.utils.BeanHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Wrapper;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 服务实现类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Service
public class TbCategoryServiceImpl extends ServiceImpl<TbCategoryMapper, TbCategory> implements TbCategoryService {

    @Override
    public List<TbCategoryDtO> findByParentId(Long pid) {

        QueryWrapper<TbCategory> tbCategoryQueryWrapper = new QueryWrapper<>();
        tbCategoryQueryWrapper.eq("parent_id", pid);
        List<TbCategory> list = this.list(tbCategoryQueryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            throw new NanfengException(ExceptionResult.CATEGORY_NOT_FOUND);
        }

        List<TbCategoryDtO> tbCategoryDtOS = BeanHelper.copyWithCollection(list, TbCategoryDtO.class);


        return tbCategoryDtOS;
    }
}
