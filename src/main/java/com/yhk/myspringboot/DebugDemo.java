package com.yhk.myspringboot;

/**
 * @author: yhk
 * @since: 2023/3/2 16:13
 * @description:
 */
public class DebugDemo {
    public static void main(String[] args) {

        Person p = new Person("field", 10);
        p.setAge(20);
        System.out.println(p);
    }
}
