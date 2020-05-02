package com.nanfeng.service;

import com.nanfeng.TbCategoryDtO;
import com.nanfeng.pojo.TbCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 服务类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
public interface TbCategoryService extends IService<TbCategory> {



    List<TbCategoryDtO> findByParentId(Long cid);
}
