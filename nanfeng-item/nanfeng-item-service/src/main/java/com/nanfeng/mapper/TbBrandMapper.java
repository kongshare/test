package com.nanfeng.mapper;

import com.nanfeng.pojo.TbBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 Mapper 接口
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
public interface TbBrandMapper extends BaseMapper<TbBrand> {

    @Select("SELECT b.`id`,b.`name`,b.`image`,b.`letter` FROM tb_brand b ,tb_category_brand  tc WHERE  b.`id`=tc.`brand_id` AND tc.`category_id`=#{cid} ")
    List<TbBrand> f(@Param("cid") Long cid);

}
