package com.yhk.myspringboot.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yhk.myspringboot.crm.entity.User;
import com.yhk.myspringboot.crm.entity.UserModel;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yhk
 * @since 2023-03-05
 */
public interface IUserService extends IService<User> {
    User getUserByName(User user);

    UserModel login(User user);

    void updatePassword(int userId, String originPwd, String newPwd, String repeatPwd);
}
