package com.nanfeng.service.impl;

import com.nanfeng.pojo.TbUser;
import com.nanfeng.mapper.TbUserMapper;
import com.nanfeng.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

}
