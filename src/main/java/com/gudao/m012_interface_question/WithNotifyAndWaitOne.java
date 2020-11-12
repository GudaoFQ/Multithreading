package com.gudao.m012_interface_question;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : GuDao
 * 2020-11-12
 */

public class WithNotifyAndWaitOne {
    public static void main(String[] args) {
        ContainerDemo demo = new ContainerDemo();
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            System.out.println("t1开始");
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    demo.add(i);
                    System.out.println(i);
                    if (5 == demo.size()) {
                        try {
                            //让t1等待
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("t1结束");
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2开始");
                //唤醒t1，因为锁的关系，t1被唤醒，但还是抢不到锁，要等t2执行完成
                lock.notify();
                System.out.println("t2结束");
            }
        });

        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}

//容器类
class ContainerDemo{
    List list = new ArrayList();

    public void add(int num){
        list.add(num);
    }

    public int size(){
        return this.list.size();
    }
}
