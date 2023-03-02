package com.yhk.myspringboot;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: yhk
 * @since: 2023/2/28 21:33
 * @description:
 */
public class ThreadDemo {
    public static void main(String[] args) {

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(100, 100, 60L, TimeUnit.DAYS, new SynchronousQueue<Runnable>());

        tpe.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("---");
            }
        });
    }
}
