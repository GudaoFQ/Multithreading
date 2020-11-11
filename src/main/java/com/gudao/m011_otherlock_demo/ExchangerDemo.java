package com.gudao.m011_otherlock_demo;

import java.util.concurrent.Exchanger;

/**
 *
 * 两个线程之间传输数据，Exchanger中的public V exchange(V x)方法被调用后等待另一个线程到达交换点（如果当前线程没有被中断），然后将已知的对象传给它，返回接收的对象
 * 如果没有第二个线程与第一个线程来进行交换，那么第一个线程就会一直被阻塞【可以通过exchange(Object,time,TimeUnit.SECONDS)来实现阻塞时间的设置】
 *
 * Author : GuDao
 * 2020-11-11
 */

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        new Thread(() -> {
            String name = "test001";

            try {
                System.out.println(Thread.currentThread().getName()+"交换前："+name);

                //将名字交换给另一个线程
                name = (String) exchanger.exchange(name);

                System.out.println(Thread.currentThread().getName()+"交换后："+name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"Thread001").start();

        new Thread(() -> {
            String name = "test002";

            try {
                System.out.println(Thread.currentThread().getName()+"交换前："+name);

                //将名字交换给另一个线程
                name = (String) exchanger.exchange(name);

                System.out.println(Thread.currentThread().getName()+"交换后："+name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"Thread002").start();
    }
}
