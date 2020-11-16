package com.gudao.m012_interface_question.add_size;

import java.util.concurrent.TimeUnit;

/**
 * Author : GuDao
 * 2020-11-12
 */

public class WithNotifyAndWaitTwo {
    public static void main(String[] args) {
        Object lock = new Object();
        ContainerDemo demo = new ContainerDemo();
        new Thread(() -> {
            System.out.println("t2开始");
            synchronized (lock){
                if(demo.size()<5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t2结束");
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1开始");
            synchronized (lock){
                for (int i = 0; i < 10; i++) {
                    demo.add(i);
                    if(demo.size()==5){
                        lock.notify();
                    }
                }
            }
            System.out.println("t1结束");
        }).start();
    }
}

