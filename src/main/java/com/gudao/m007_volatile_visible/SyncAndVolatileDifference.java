package com.gudao.m007_volatile_visible;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * volatile只是保证了线程之间的共享变量的可见行，但是并不能保证原子性，所以volatile并不能完全代替synchronized
 *
 * 使用list的原因：for循环的时候，前一个线程可能已经把自己的线程执行完了，所以效果很难测试出来，使用list遍历能确保但部分线程同时执行
 *
 * Author : GuDao
 * 2020-11-06
 */

public class SyncAndVolatileDifference {
    volatile static int a = 0;

    public void methed(){
        for (int i = 0; i < 1000; i++) {
            //解决方法：不用volatile【sync也能实现线程间的可见性】，使用sync同步代码块给下面的a++加锁
            a++;
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        SyncAndVolatileDifference de = new SyncAndVolatileDifference();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            threads.add(new Thread(de :: methed));
        }

        threads.forEach(e -> {
            e.start();
        });

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end："+SyncAndVolatileDifference.a);
    }
}
