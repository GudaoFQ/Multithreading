package com.gudao.m013_threadlocal_demo;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量，参数是设置在Thread.currentThread.map中的，只有自己的线程才能访问到自己线程中的参数
 *
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 *
 * Author : GuDao
 * 2020-11-16
 */

public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal local = new ThreadLocal();

        //t1线程系向threadLocal中设值gudao
        Thread t1 = new Thread(() -> {
            local.set("gudao");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //休息2秒钟后，自己的线程还是能获取自己线程中的值
            System.out.println(Thread.currentThread().getName()+"获取参数："+local.get());
        }, "t1");

        //t2休息1秒钟之后向threadLocal中取值
        Thread t2 = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"获取参数："+local.get());
        }, "t2");

        t1.start();
        t2.start();

    }

}
