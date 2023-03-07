package com.yhk.myspringboot.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.pojo.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yhk
 * @since 2023-03-05
 */
public interface IUserService extends IService<User> {
    ResultInfo<User> getUserByName(User user);
}
