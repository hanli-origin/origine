package com.yhk.myspringboot;

import com.yhk.myspringboot.base.ResultInfo;
import com.yhk.myspringboot.crm.entity.User;

/**
 * @ClassName TestTemplate
 * @Author ADMIN
 * @Date 2023/3/7
 */
public class TestTemplate {

    /**
     * @param * @param args
     * @return void
     * @throws
     * @Description <description your method purpose>
     * @Date 2023/3/7 15:15
     * @user ADMIN
     */
    public static void main(String[] args) {
    }

    /**
     * @param * @param
     * @return com.yhk.myspringboot.base.ResultInfo<com.yhk.myspringboot.crm.pojo.User>
     * @throws
     * @Description <description your method purpose>
     * @Date 2023/3/7 15:20
     * @user ADMIN
     */
    public ResultInfo<User> test01() {
        ResultInfo<User> resultInfo = new ResultInfo<>();
        resultInfo.setData(new User());
        return resultInfo;
    }
}
