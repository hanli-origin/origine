package com.yhk.myspringboot.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.mapper.UserMapper;
import com.yhk.myspringboot.crm.pojo.User;
import com.yhk.myspringboot.crm.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yhk
 * @since 2023-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public ResultInfo<User> getUserByName(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.setEntity(user);
        wrapper.eq("user_name", user.getUserName());
        wrapper.and(w -> w.eq("user_password", user.getUserPassword()));
        User result = getOne(wrapper);
        ResultInfo<User> resultInfo = new ResultInfo<>();
        resultInfo.setResult(result);
        return resultInfo;
    }
}
