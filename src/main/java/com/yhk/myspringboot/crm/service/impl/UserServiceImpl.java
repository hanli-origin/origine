package com.yhk.myspringboot.crm.service.impl;

import com.yhk.myspringboot.crm.pojo.User;
import com.yhk.myspringboot.crm.mapper.UserMapper;
import com.yhk.myspringboot.crm.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yhk
 * @since 2023-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
