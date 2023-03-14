package com.yhk.myspringboot;

import com.yhk.myspringboot.crm.utils.Md5Util;
import org.junit.jupiter.api.Test;

/**
 * @ClassName TestEncode
 * @Author ADMIN
 * @Date 2023/3/14
 */
public class TestEncode {

    @Test
    void test() {
        String encode = Md5Util.encode("000000");
        System.out.println(encode);
    }
}
