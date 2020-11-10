package com.gudao.m011_otherlock_demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *
 * 通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了
 *
 * Author : GuDao
 * 2020-11-10
 */

public class CountDownLatchDemo {
    private static CountDownLatch latch = new CountDownLatch(10);

    public void methed(){
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("通过countDown方法进行减一操作，执行第"+i+"次");
            //对new CountDownLatch(int)中的int进行-1操作，当int为0时候，被await()的方法继续执行
            latch.countDown();
        }
    }

    public static void main(String[] args) {

        CountDownLatchDemo demo = new CountDownLatchDemo();

        System.out.println("主方法开始执行");

        try {
            System.out.println("通过await方法进行线程等待");

            new Thread(demo :: methed).start();

            latch.await();

            System.out.println("执行完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
