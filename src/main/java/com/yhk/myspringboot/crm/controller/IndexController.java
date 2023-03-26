package com.yhk.myspringboot.crm.controller;

import com.yhk.myspringboot.base.BaseController;
import com.yhk.myspringboot.crm.entity.User;
import com.yhk.myspringboot.crm.service.IUserService;
import com.yhk.myspringboot.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yhk
 * @since 2023/3/4 18:54
 */
@Controller
public class IndexController extends BaseController {

    @Resource
    private IUserService userService;

    // 系统登录页
    @GetMapping("index")
    public String index(HttpServletRequest request) {
        return "index";
    }

    // 系统欢迎页
    @GetMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    // 后台管理页
    @GetMapping(value = {"/", "main"})
    public String admin(HttpServletRequest request) {
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.getById(userId);
        request.getSession().setAttribute("user", user);
        return "main";
    }
}
