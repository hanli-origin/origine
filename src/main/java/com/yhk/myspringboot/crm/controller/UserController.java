package com.yhk.myspringboot.crm.controller;


import com.yhk.myspringboot.base.BaseController;
import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.exceptions.ParamsException;
import com.yhk.myspringboot.crm.pojo.User;
import com.yhk.myspringboot.crm.pojo.UserModel;
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
    public ResultInfo<UserModel> login(@RequestBody User user) {
        ResultInfo<UserModel> resultInfo;
        try {
            resultInfo = success("登录成功", userService.login(user));
        } catch (ParamsException p) {
            resultInfo = fail(p.getMsg(), new UserModel(user));
        } catch (Exception e) {
            resultInfo = fail(e.getMessage(), new UserModel(user));
        }
        return resultInfo;
    }
}
