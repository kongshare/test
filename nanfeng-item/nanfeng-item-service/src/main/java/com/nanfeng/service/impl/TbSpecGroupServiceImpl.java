package com.nanfeng.service.impl;

import com.nanfeng.pojo.TbSpecGroup;
import com.nanfeng.mapper.TbSpecGroupMapper;
import com.nanfeng.service.TbSpecGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 规格参数的分组表，每个商品分类下有多个规格参数组 服务实现类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Service
public class TbSpecGroupServiceImpl extends ServiceImpl<TbSpecGroupMapper, TbSpecGroup> implements TbSpecGroupService {

}
