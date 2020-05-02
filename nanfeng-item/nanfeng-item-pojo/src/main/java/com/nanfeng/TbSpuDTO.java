package com.nanfeng;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Arrays;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbSpuDTO  {

private static final long serialVersionUID=1L;

    /**
     * spu id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 副标题，一般是促销信息
     */
    private String subTitle;

    /**
     * 1级类目id
     */
    private Long cid1;

    /**
     * 2级类目id
     */
    private Long cid2;

    /**
     * 3级类目id
     */
    private Long cid3;

    /**
     * 商品所属品牌id
     */
    private Long brandId;

    /**
     * 是否上架，0下架，1上架
     */
    private Boolean saleable;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date updateTime;
    private TbSpuDetailDTO spuDetail;
    private List<TbSkuDTO> skus;
    private String categoryName; // 商品分类名称拼接
    private String brandName;// 品牌名称

    @JsonIgnore
    public List<Long> getCategoryCids() {
        return Arrays.asList(cid1, cid2, cid3);
    }

}
