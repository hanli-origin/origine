package com.yhk.myspringboot.crm.controller;


import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.pojo.User;
import org.springframework.util.ObjectUtils;
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
public class UserController {

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo<User> login(@RequestBody User user) {

        if (!ObjectUtils.isEmpty(user)) {
            System.out.println("name" + user.getUserName());
        }
        ResultInfo<User> resultInfo = new ResultInfo<>();
        resultInfo.setResult(user);
        return resultInfo;
    }
}
