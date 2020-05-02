package com.nanfeng.service.impl;

import com.nanfeng.pojo.TbApplicationPrivilege;
import com.nanfeng.mapper.TbApplicationPrivilegeMapper;
import com.nanfeng.service.TbApplicationPrivilegeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务中间表，记录服务id以及服务能访问的目标服务的id 服务实现类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Service
public class TbApplicationPrivilegeServiceImpl extends ServiceImpl<TbApplicationPrivilegeMapper, TbApplicationPrivilege> implements TbApplicationPrivilegeService {

}
