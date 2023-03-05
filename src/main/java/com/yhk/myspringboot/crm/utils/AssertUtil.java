package com.yhk.myspringboot.crm.utils;

import com.yhk.myspringboot.crm.exceptions.ParamsException;

public class AssertUtil {

    public static void isTrue(Boolean flag, String msg) {
        if (flag) {
            throw new ParamsException(msg);
        }
    }

}
