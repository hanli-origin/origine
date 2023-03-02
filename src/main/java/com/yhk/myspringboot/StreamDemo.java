package com.yhk.myspringboot;

import java.util.stream.Stream;

/**
 * @author: yhk
 * @since: 2023/3/1 16:29
 * @description:
 */
public class StreamDemo {
    public static void main(String[] args) {
        Stream.iterate(0, t -> t + 2).limit(10).forEach(r -> System.out.println(r));
    }
}
