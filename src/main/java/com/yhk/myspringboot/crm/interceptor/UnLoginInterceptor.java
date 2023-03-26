package com.yhk.myspringboot.crm.interceptor;

import com.yhk.myspringboot.crm.exceptions.UnLoginException;
import com.yhk.myspringboot.crm.service.IUserService;
import com.yhk.myspringboot.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    IUserService iUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从cookie 读取userId 根据userId是否存在判断用户是否登录
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        if (userId == 0 || iUserService.getById(userId) == null) {
            throw new UnLoginException();
        }
        return super.preHandle(request, response, handler);

    }
}
