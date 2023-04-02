package com.yhk.myspringboot.crm.controller;

import com.yhk.myspringboot.base.BaseController;
import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.entity.User;
import com.yhk.myspringboot.crm.entity.UserModel;
import com.yhk.myspringboot.crm.exceptions.ParamsException;
import com.yhk.myspringboot.crm.service.IUserService;
import com.yhk.myspringboot.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yhk
 * @since 2023-03-05
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final IUserService IUserService;

    public UserController(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo<UserModel> login(@RequestBody User user) {
        ResultInfo<UserModel> resultInfo;
        try {
            resultInfo = success("登录成功", IUserService.login(user));
        } catch (ParamsException p) {
            resultInfo = fail(p.getMsg(), new UserModel(user));
        } catch (Exception e) {
            resultInfo = fail(e.getMessage(), new UserModel(user));
        }
        return resultInfo;
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ResultInfo<String> updatePwd(HttpServletRequest request, String originPwd, String newPwd, String repeatPwd) {
        try {
            int userId = LoginUserUtil.releaseUserIdFromCookie(request);
            IUserService.updatePassword(userId, originPwd, newPwd, repeatPwd);
            return success("密码修改成功");

        } catch (ParamsException p) {
            return fail(p.getMsg(), p.getCode());
        } catch (Exception e) {
            return fail(e.getMessage(), 500);
        }
    }

    @RequestMapping("/toPasswordPage")
    public String toPasswordPage() {
        return "/user/password";
    }


    @RequestMapping("/queryAllSales")
    @ResponseBody
    public List<Map<String, Object>> queryAllSales() {
        return IUserService.getAllSales();
    }
}
