package com.nanfeng.service.impl;

import com.nanfeng.pojo.TbApplication;
import com.nanfeng.mapper.TbApplicationMapper;
import com.nanfeng.service.TbApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务信息表，记录微服务的id，名称，密文，用来做服务认证 服务实现类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Service
public class TbApplicationServiceImpl extends ServiceImpl<TbApplicationMapper, TbApplication> implements TbApplicationService {

}
