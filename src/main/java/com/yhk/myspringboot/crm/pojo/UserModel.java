package com.yhk.myspringboot.crm.pojo;

import lombok.Data;

/**
 * @ClassName UserModel
 * @Author ADMIN
 * @Date 2023/3/15
 */
@Data
public class UserModel {
    private String userName;
    private String userID;
    private String trueName;

    private String userPassword;

    public UserModel() {
    }

    public UserModel(User user) {
        this.userName = user.getUserName();
        this.userPassword = user.getUserPassword();
    }
}
