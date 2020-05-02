package com.nanfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nanfeng.TbSkuDTO;
import com.nanfeng.TbSpuDTO;
import com.nanfeng.TbSpuDetailDTO;
import com.nanfeng.pojo.*;
import com.nanfeng.mapper.TbSpuMapper;
import com.nanfeng.service.TbSpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.enums.ExceptionResult;
import com.share.exception.NanfengException;
import com.share.utils.BeanHelper;
import com.share.vo.PageResult;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8 服务实现类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Service
public class TbSpuServiceImpl extends ServiceImpl<TbSpuMapper, TbSpu> implements TbSpuService {

    /*
    spu的分页查询
     */
    public PageResult<TbSpuDTO> findPage(Integer page, Integer rows, String key, Boolean saleable) {
        Page<TbSpu> page1 = new Page<>(page, rows);

        QueryWrapper<TbSpu> queryWrapper = new QueryWrapper<>();
        if (saleable != null) {
            queryWrapper.lambda().eq(TbSpu::getSaleable, saleable);
        }
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.lambda().like(TbSpu::getName, key).or().like(TbSpu::getSubTitle, key);
        }

        IPage<TbSpu> spu = this.page(page1, queryWrapper);
        List<TbSpu> records = spu.getRecords();

        if (CollectionUtils.isEmpty(records)) {
            throw new NanfengException(ExceptionResult.GOODS_NOT_FOUND);
        }
        List<TbSpuDTO> spuDTOs = BeanHelper.copyWithCollection(records, TbSpuDTO.class);
        this.getCateAndBrand(spuDTOs);
        for (TbSpuDTO spuDTO : spuDTOs) {
          //  this.getCatetegoryName(spuDTO);
          //  this.getBrandName(spuDTO);

        }
        this.findDetailAndSku(spuDTOs);

        return new PageResult<TbSpuDTO>(spu.getTotal(), Integer.valueOf(String.valueOf(spu.getPages())), spuDTOs);
    }


    /*
    查询sku spudetai
     */
    public List<TbSpuDTO> findDetailAndSku(List<TbSpuDTO> tbSpuDTOS) {
        for (TbSpuDTO tbSpuDTO : tbSpuDTOS) {
            Long id = tbSpuDTO.getId();
            QueryWrapper<TbSpuDetail> tbSpuDetailQueryWrapper = new QueryWrapper<>();
            tbSpuDetailQueryWrapper.lambda().eq(TbSpuDetail::getSpuId, id);
            List<TbSpuDetail> details = spuDetailService.list(tbSpuDetailQueryWrapper);
            for (TbSpuDetail detail : details) {
                tbSpuDTO.setSpuDetail(BeanHelper.copyProperties(detail, TbSpuDetailDTO.class));
            }
            QueryWrapper<TbSku> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(TbSku::getSpuId, id);
            List<TbSku> list = tbSkuService.list(queryWrapper);
            tbSpuDTO.setSkus(BeanHelper.copyWithCollection(list,TbSkuDTO.class));

        }

        return tbSpuDTOS;
    }

    @Autowired
    TbCategoryServiceImpl tbCategoryService;
    /*
    商品分类下的名称
     */
    public TbSpuDTO getCatetegoryName(TbSpuDTO spuDTO) {
        List<Long> cids = spuDTO.getCategoryCids();
        Collection<TbCategory> categories = tbCategoryService.listByIds(cids);
        String categoryNames = "";
        for (TbCategory c : categories) {
            if (!StringUtils.isEmpty(categoryNames)) {
                categoryNames += "/";
            }
            String name = c.getName();
            categoryNames += name;
        }


        return spuDTO.setCategoryName(categoryNames);
    }

    @Autowired
    TbBrandServiceImpl tbBrandService;
    /*
    商品分类下的品牌name
     */
    public TbSpuDTO getBrandName(TbSpuDTO spuDTO) {

        TbBrand tbBrand = tbBrandService.getById(spuDTO.getBrandId());

      return   spuDTO.setBrandName(tbBrand.getName());
    }

    /*
    获取分类名称和品牌名称
     */
    public void getCateAndBrand(List<TbSpuDTO> tbSpus) {
        for (TbSpuDTO spus : tbSpus) {
            List<Long> cids = spus.getCategoryCids();
            Collection<TbCategory> tbBrands = tbCategoryService.listByIds(cids);
            String s = tbBrands.stream().map(TbCategory::getName).collect(Collectors.joining("/"));
            spus.setCategoryName(s);

            TbBrand name = tbBrandService.getById(spus.getBrandId());
            spus.setBrandName(name.getName());

        }

    }

    /*
    新增商品保存三张表
     */
    @Autowired
    TbSpuDetailServiceImpl spuDetailService;
    @Autowired
    private TbSkuServiceImpl tbSkuService;

    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(TbSpuDTO s) {

        /*
        保存spu表
         */
        TbSpu spu = BeanHelper.copyProperties(s, TbSpu.class);
        boolean b = this.save(spu);
        if (!b) {
            throw new NanfengException(ExceptionResult.INSERT_OPERATION_FAIL);
        }
        /*
        保存spudetail
         */
        TbSpuDetailDTO sTbSpuDetailDTO = s.getSpuDetail();
        TbSpuDetail detail = BeanHelper.copyProperties(sTbSpuDetailDTO, TbSpuDetail.class);
        Long id = s.getId();
        detail.setSpuId(id);
        boolean b1 = spuDetailService.save(detail);
        if (!b1) {
            throw new NanfengException(ExceptionResult.INSERT_OPERATION_FAIL);

        }
        /*
        保存sku
         */
        List<TbSkuDTO> dtos = s.getSkus();
        List<TbSku> skus = BeanHelper.copyWithCollection(dtos, TbSku.class);
        boolean b2 = tbSkuService.saveBatch(skus);
        if (!b2) {
            throw new NanfengException(ExceptionResult.INSERT_OPERATION_FAIL);
        }

        return b2;
    }



    @Transactional(rollbackFor = Exception.class)
    public Boolean saleable(Long id, Boolean saleable) {
        UpdateWrapper<TbSpu> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(TbSpu::getId, id);
        wrapper.lambda().set(TbSpu::getSaleable, saleable);
        boolean b = this.update(wrapper);

        if (!b) {
            throw new NanfengException(ExceptionResult.UPDATE_OPERATION_FAIL);
        }
        UpdateWrapper<TbSku> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(TbSku::getSpuId, id);
        updateWrapper.lambda().set(TbSku::getEnable, saleable);
        boolean b1 = tbSkuService.update(updateWrapper);


        return b1;
    }



    public TbSpuDetailDTO findSpuDetail(Long id) {
        TbSpuDetail tbSpuDetail = spuDetailService.getById(id);
        return BeanHelper.copyProperties(tbSpuDetail, TbSpuDetailDTO.class);
    }


    public List<TbSkuDTO> findByIdSku(Long id) {

        QueryWrapper<TbSku> q = new QueryWrapper<>();
        q.lambda().eq(TbSku::getSpuId, id);
        List<TbSku> list = tbSkuService.list(q);
        return BeanHelper.copyWithCollection(list, TbSkuDTO.class);

    }

    /*
   修改操作spu
     */
    @Transactional(rollbackFor = Exception.class)
    public void nanfenpdate(TbSpuDTO tbSpuDTO) {
        TbSpu tbSpu = BeanHelper.copyProperties(tbSpuDTO, TbSpu.class);
        Long id = tbSpu.getId();
        //修改spu表
        boolean b2 = this.updateById(tbSpu);
        if (!b2) {
            throw new NanfengException(ExceptionResult.UPDATE_OPERATION_FAIL);
        }
        //修改spudetail
        TbSpuDetailDTO detailDTO = tbSpuDTO.getSpuDetail();
        TbSpuDetail detail = BeanHelper.copyProperties(detailDTO, TbSpuDetail.class);
        boolean b = spuDetailService.updateById(detail);
        if (!b) {
            throw new NanfengException(ExceptionResult.UPDATE_OPERATION_FAIL);
        }
        //修改sku集合  sku是多个
        List<TbSkuDTO> tbSkuDTOS = tbSpuDTO.getSkus();

        UpdateWrapper<TbSku> wrapper1 = new UpdateWrapper<>();
        wrapper1.lambda().eq(TbSku::getSpuId, id);
        //做修改多个    可以选择把删除之后再批量保存
        boolean remove = tbSkuService.remove(wrapper1);
        if (!remove) {
            throw new NanfengException(ExceptionResult.UPDATE_OPERATION_FAIL);
        }

        List<TbSku> tbSkus = tbSkuDTOS.stream().map(tbSkuDTO -> {
                    tbSkuDTO.setSpuId(id);
            return BeanHelper.copyProperties(tbSkuDTO, TbSku.class);
                }
        ).collect(Collectors.toList());
        boolean b1 = tbSkuService.saveBatch(tbSkus);
        if (!b1) {
            throw new NanfengException(ExceptionResult.UPDATE_OPERATION_FAIL);

        }
        }

    }
