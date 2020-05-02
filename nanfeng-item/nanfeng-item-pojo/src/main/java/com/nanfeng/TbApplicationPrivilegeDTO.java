package com.nanfeng;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 服务中间表，记录服务id以及服务能访问的目标服务的id
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbApplicationPrivilegeDTO {

private static final long serialVersionUID=1L;

    /**
     * 服务id
     */
    private Integer serviceId;

    /**
     * 目标服务id
     */
    private Integer targetId;


}
