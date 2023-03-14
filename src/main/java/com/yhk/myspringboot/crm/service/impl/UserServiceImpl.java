package com.yhk.myspringboot.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.mapper.UserMapper;
import com.yhk.myspringboot.crm.pojo.User;
import com.yhk.myspringboot.crm.service.IUserService;
import com.yhk.myspringboot.crm.utils.AssertUtil;
import com.yhk.myspringboot.crm.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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

    public boolean login(User user) {
        return checkLogin(user);
    }

    private boolean checkLogin(User user) {
        //输入用户信息判空
        boolean isPresent = Optional.ofNullable(user).isPresent();
        AssertUtil.isTrue(!isPresent, "用户信息为空");
        assert user != null;
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserPassword()), "密码不能为空");
        //查询结果判空
        User userInfo = getUserByName(user).getResult();
        AssertUtil.isTrue(!Optional.ofNullable(userInfo).isPresent(), "用户不存在");
        assert userInfo != null;
        // 密码校验
        boolean result = checkPwd(user.getUserPassword(), userInfo.getUserPassword());
        AssertUtil.isTrue(!result, "密码错误，登录失败");
        return true;
    }

    private boolean checkPwd(String inputPassword, String realPassword) {

        // 输入密码加密后和数据库密码比较
        inputPassword = Md5Util.encode(inputPassword);

        return Objects.equals(inputPassword, realPassword);
    }

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
