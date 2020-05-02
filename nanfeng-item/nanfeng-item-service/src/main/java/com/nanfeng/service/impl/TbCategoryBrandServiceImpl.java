package com.nanfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nanfeng.TbBrandDTO;
import com.nanfeng.TbCategoryBrandDTO;
import com.nanfeng.pojo.TbBrand;
import com.nanfeng.pojo.TbCategoryBrand;
import com.nanfeng.mapper.TbCategoryBrandMapper;
import com.nanfeng.service.TbCategoryBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.utils.BeanHelper;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * <p>
 * 商品分类和品牌的中间表，两者是多对多关系 服务实现类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Service
public class TbCategoryBrandServiceImpl extends ServiceImpl<TbCategoryBrandMapper, TbCategoryBrand> implements TbCategoryBrandService {

    @Autowired
    TbBrandServiceImpl tbBrandService;

    public List<TbBrandDTO> findByCid(Long id) {
        QueryWrapper<TbCategoryBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbCategoryBrand::getCategoryId, id);
        List<TbCategoryBrand> list = this.list(queryWrapper);
        ArrayList ids = new ArrayList();
        TbBrand tbBrand = new TbBrand();
        System.out.println(ids);
        for (TbCategoryBrand t : list) {
            Long brandId = t.getBrandId();
            ids.add(brandId);
        }
        Collection collection = tbBrandService.listByIds(ids);
        List l = CollectionUtils.arrayToList(collection.toArray());
        return BeanHelper.copyWithCollection(l, TbBrandDTO.class);
    }
}
