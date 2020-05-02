package com.nanfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nanfeng.TbBrandDTO;
import com.nanfeng.pojo.TbBrand;
import com.nanfeng.mapper.TbBrandMapper;
import com.nanfeng.pojo.TbCategoryBrand;
import com.nanfeng.service.TbBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.enums.ExceptionResult;
import com.share.exception.NanfengException;
import com.share.utils.BeanHelper;
import com.share.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务实现类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Slf4j
@Service
public class TbBrandServiceImpl extends ServiceImpl<TbBrandMapper, TbBrand> implements TbBrandService {

    @Autowired
    TbCategoryBrandServiceImpl tbCategoryBrandService;

    @Override
    public PageResult<TbBrandDTO> findBrandPagr(String key, Integer page, Integer rows, String sortBy, Boolean desc) {

        Page<TbBrand> page1 = new Page<>(page,rows);
        QueryWrapper<TbBrand> wrapper = new QueryWrapper<>();
        //添加关键字搜索条件
        if (!StringUtils.isEmpty(key)){
        wrapper.lambda().like(TbBrand::getName,key).or().like(TbBrand::getLetter,key);
        }
        //根据升序降序 默认是降序
        if (!StringUtils.isEmpty(sortBy)) {
            if (desc){
                wrapper.orderByDesc(sortBy);
            }else {
                wrapper.orderByAsc(sortBy);
            }
        }
        IPage<TbBrand> pageBrand = this.page(page1, wrapper);
        //判断查询结果是否为空
        if (pageBrand==null||CollectionUtils.isEmpty(pageBrand.getRecords())) {

            throw new NanfengException(ExceptionResult.BRAND_NOT_FOUND);
        }
        List<TbBrand> tbBrands = pageBrand.getRecords();
        System.out.println("哈哈"+String.valueOf(pageBrand.getPages()));
        List<TbBrandDTO> brandDTOS = BeanHelper.copyWithCollection(tbBrands, TbBrandDTO.class);
        return new PageResult<TbBrandDTO>(pageBrand.getTotal(),Integer.valueOf(String.valueOf(pageBrand.getPages())),brandDTOS);
    }

    @Override
    public Object insert(TbBrandDTO tbBrandDTO, List<Long> cids) {

        TbBrand tbBrand = BeanHelper.copyProperties(tbBrandDTO, TbBrand.class);
        boolean save = this.save(tbBrand);
        if (!save) {
            throw new NanfengException(ExceptionResult.INSERT_OPERATION_FAIL);
        }
        Long bid = tbBrand.getId();
        ArrayList<TbCategoryBrand> arr = new ArrayList<>();
        //关系表分类品牌表

        for (Long cid: cids) {
            TbCategoryBrand tbCategoryBrand = new TbCategoryBrand();
            tbCategoryBrand.setBrandId(bid);
            tbCategoryBrand.setCategoryId(cid);
            arr.add(tbCategoryBrand);
        }
        boolean b = tbCategoryBrandService.saveBatch(arr);
        if (!b) {
            throw new NanfengException(ExceptionResult.INSERT_OPERATION_FAIL);
        }
        return b;
    }

    @Override
    public List<TbBrandDTO> f(Long cid) {
        List<TbBrand> f = this.getBaseMapper().f(cid);
        return BeanHelper.copyWithCollection(f, TbBrandDTO.class);
    }
}
