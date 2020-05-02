package com.nanfeng.service;

import com.nanfeng.TbBrandDTO;
import com.nanfeng.pojo.TbBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.share.vo.PageResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
public interface TbBrandService extends IService<TbBrand> {

    PageResult<TbBrandDTO> findBrandPagr(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    Object insert(TbBrandDTO tbBrandDTO, List<Long> cids);

    List<TbBrandDTO> f(Long cid);
}
