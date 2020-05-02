package com.nanfeng.pojo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JobInfo extends Model<JobInfo> {

private static final long serialVersionUID=1L;

    private Integer id;

    @TableField("companyName")
    private String companyName;

    @TableField("companyAddr")
    private String companyAddr;

    @TableField("companyInfo")
    private String companyInfo;

    @TableField("jobName")
    private String jobName;

    @TableField("jobAddr")
    private String jobAddr;

    @TableField("jobInfo")
    private String jobInfo;

    @TableField("salaryMin")
    private String salaryMin;

    @TableField("salaryMax")
    private String salaryMax;

    private String url;

    private Date time;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
