package com.nanfeng.service.impl;

import com.nanfeng.pojo.Emp;
import com.nanfeng.mapper.EmpMapper;
import com.nanfeng.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HM
 * @since 2020-04-11
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

}
