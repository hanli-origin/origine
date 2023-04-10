package com.yhk.myspringboot.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhk.myspringboot.crm.entity.User;
import com.yhk.myspringboot.crm.entity.UserModel;
import com.yhk.myspringboot.crm.mapper.UserMapper;
import com.yhk.myspringboot.crm.query.UserQuery;
import com.yhk.myspringboot.crm.service.IUserService;
import com.yhk.myspringboot.crm.utils.AssertUtil;
import com.yhk.myspringboot.crm.utils.Md5Util;
import com.yhk.myspringboot.crm.utils.PhoneUtil;
import com.yhk.myspringboot.crm.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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


    @Override
    public UserModel login(User user) {
        // 输入用户信息判空
        checkLogin(user);
        // 查询结果判空
        User userInfo = getUserByName(user);
        AssertUtil.isTrue(!Optional.ofNullable(userInfo).isPresent(), "用户不存在");
        assert userInfo != null;
        // 密码校验
        boolean result = checkPwd(user.getUserPassword(), userInfo.getUserPassword());
        AssertUtil.isTrue(!result, "密码错误，登录失败");
        return buildUserModel(userInfo);
    }

    private UserModel buildUserModel(User user) {
        UserModel userModel = new UserModel();
        String userID = UserIDBase64.encoderUserID(user.getId());
        userModel.setUserID(userID);
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        userModel.setUserPassword("******");
        return userModel;
    }

    private void checkLogin(User user) {
        boolean isPresent = Optional.ofNullable(user).isPresent();
        AssertUtil.isTrue(!isPresent, "用户信息为空");
        assert user != null;
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserPassword()), "密码不能为空");
    }

    private boolean checkPwd(String inputPassword, String realPassword) {

        // 输入密码加密后和数据库密码比较
        inputPassword = Md5Util.encode(inputPassword);

        return Objects.equals(inputPassword, realPassword);
    }

    @Override
    public User getUserByName(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", user.getUserName());
        return getOne(wrapper);
    }

    @Override
    public void updatePassword(int userId, String originPwd, String newPwd, String repeatPwd) {
        User origin = getById(userId);
        AssertUtil.isTrue(!Optional.ofNullable(origin).isPresent(), "用户不存在");
        assert origin != null;
        checkPwdParam(origin.getUserPassword(), originPwd, newPwd, repeatPwd);
        User user = new User();
        user.setId(userId);
        user.setUserPassword(Md5Util.encode(newPwd));
        baseMapper.updateById(user);
    }

    private void checkPwdParam(String realPwd, String originPwd, String newPwd, String repeatPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(originPwd) || StringUtils.isBlank(newPwd) || StringUtils.isBlank(repeatPwd)
                , "旧/新/确认密码不能为空");
        AssertUtil.isTrue(!StringUtils.equals(realPwd, Md5Util.encode(originPwd)), "密码错误，修改失败");
        AssertUtil.isTrue(StringUtils.equals(originPwd, newPwd), "新密码不能与旧密码相同");
        AssertUtil.isTrue(!StringUtils.equals(newPwd, repeatPwd), "新密码要与确认密码相同");
    }

    @Override
    public List<Map<String, Object>> getAllSales() {
        return baseMapper.queryAllSales();
    }

    public Page<User> getUsers(UserQuery query) {
        // 在QueryWrapper中设置条件
        LambdaQueryWrapper<User> lambdaWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(query.getUserName())) {
            lambdaWrapper.like(User::getUserName, query.getUserName());
        }
        if (StringUtils.isNotBlank(query.getEmail())) {
            lambdaWrapper.like(User::getEmail, query.getEmail());
        }
        if (StringUtils.isNotBlank(query.getPhone())) {
            lambdaWrapper.like(User::getPhone, query.getPhone());
        }

        // 构建分页对象
        Page<User> page = new Page<>(query.getPage(), query.getLimit());
        baseMapper.selectPage(page, lambdaWrapper);
        return page;
    }


    public void addUser(User user) {
        // 参数校验
        checkUser(user);
        // 设置默认值
        user.setIsValid(1);
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        // 设置默认密码
        user.setUserPassword(Md5Util.encode("123456"));
        this.save(user);
    }

    private void checkUser(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户名不能为空");
        AssertUtil.isTrue(getUserByName(user) != null, "用户名已被占用");
        AssertUtil.isTrue(StringUtils.isBlank(user.getEmail()), "email不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPhone()), "手机号不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(user.getPhone()), "手机号格式错误");
    }
}
