package com.yhk.myspringboot.crm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhk.myspringboot.base.BaseController;
import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.entity.User;
import com.yhk.myspringboot.crm.entity.UserModel;
import com.yhk.myspringboot.crm.exceptions.ParamsException;
import com.yhk.myspringboot.crm.query.UserQuery;
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
import java.util.Optional;

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

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo<UserModel> login(@RequestBody User user) {
        ResultInfo<UserModel> resultInfo;
        try {
            resultInfo = success("登录成功", iUserService.login(user));
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
            iUserService.updatePassword(userId, originPwd, newPwd, repeatPwd);
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
        return iUserService.getAllSales();
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResultInfo<List<User>> queryUser(UserQuery query) {
        Page<User> users = iUserService.getUsers(query);
        ResultInfo<List<User>> success = success("查询成功", users.getRecords());
        success.setCode(0);
        success.setCount(users.getTotal());
        return success;
    }

    @RequestMapping("/index")
    public String index() {
        return "/user/user";
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo<User> addUser(User user) {
        if (!Optional.ofNullable(user).isPresent()) {
            return fail("参数不能为空", 500);
        }
        iUserService.addUser(user);
        return success();
    }

    @RequestMapping("/addOrUpdateUserPage")
    public String addOrUpdateUserPage(Integer id, HttpServletRequest request) {
        if (Optional.ofNullable(id).isPresent()) {
            User user = iUserService.getById(id);
            request.setAttribute("user", user);
        } else {
            request.setAttribute("user", new User());
        }
        return "/user/add_update";
    }
}
