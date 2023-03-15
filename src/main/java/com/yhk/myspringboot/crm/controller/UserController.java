package com.yhk.myspringboot.crm.controller;


import com.yhk.myspringboot.base.BaseController;
import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.exceptions.ParamsException;
import com.yhk.myspringboot.crm.pojo.User;
import com.yhk.myspringboot.crm.service.IUserService;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yhk
 * @since 2023-03-05
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo<User> login(@RequestBody User user) {
        ResultInfo<User> resultInfo;
        try {
            resultInfo = success("登录成功", userService.login(user));
        } catch (ParamsException p) {
            resultInfo = fail(p.getMsg(), user);
        } catch (Exception e) {
            resultInfo = fail(e.getMessage(), user);
        }
        return resultInfo;
    }
}
