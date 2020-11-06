package com.gudao.m009_atomic_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * AtomicInteger中的原子操作
 *
 * Author : GuDao
 * 2020-11-04
 */

public class AtomicIntegerMethed {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);

        //方法incrementAndGet()
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ai.incrementAndGet();//自增相似于a++；但它是线程安全的
            }
            System.out.println(Thread.currentThread().getName()+" ai的值："+ai.get());
        },"Thread001").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //cas方法compareAndSet(期望值，设定新值);
        new Thread(() -> {
                ai.compareAndSet(ai.get(), 5);//cas操作
                System.out.println(Thread.currentThread().getName()+" ai的值："+ai.get());
        },"Thread002").start();
    }
}
