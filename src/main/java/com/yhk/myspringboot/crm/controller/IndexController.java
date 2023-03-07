package com.yhk.myspringboot.crm.controller;

import com.yhk.myspringboot.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: yhk
 * @since: 2023/3/4 18:54
 * @description:
 */
@Controller
public class IndexController extends BaseController {

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

        return "main";
    }
}
