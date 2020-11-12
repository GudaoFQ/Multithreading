package com.gudao.m011_otherlock_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * LockSupport的方法都是静态方法，可以让线程在任意位置阻塞，当然阻塞之后肯定得有唤醒的方法。
 *
 * Author : GuDao
 * 2020-11-11
 */

public class LockSupportDemo {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("线程运行开始");
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if(i==5){
                    //将线程阻塞
                    LockSupport.park();
                }
            }
            System.out.println("线程运行结束");
        });

        t.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //将线程唤醒
        LockSupport.unpark(t);
    }
}
