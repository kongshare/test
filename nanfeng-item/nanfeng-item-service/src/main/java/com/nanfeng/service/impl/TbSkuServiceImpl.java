package com.nanfeng.service.impl;

import com.nanfeng.pojo.TbSku;
import com.nanfeng.mapper.TbSkuMapper;
import com.nanfeng.service.TbSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8 服务实现类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Service
public class TbSkuServiceImpl extends ServiceImpl<TbSkuMapper, TbSku> implements TbSkuService {

}
