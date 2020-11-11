package com.gudao.m011_otherlock_demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *
 * Semaphore用于限制可以访问某些资源（物理或逻辑的）的线程数目，他维护了一个许可证集合，有多少资源需要限制就维护多少许可证集合，假如这里有N个资源，那就对应于N个许可证，同一时刻也只能有N个线程访问。
 * 一个线程获取许可证就调用acquire方法，用完了释放资源就调用release方法。
 *
 * Author : GuDao
 * 2020-11-11
 */

public class SemaphoreDemo {
    public static void main(String[] args) {
        //定义资源同时访问量，默认是非公平访问，这里设置公平
        Semaphore semaphore = new Semaphore(1, true);

        new Thread(() -> {
            //注册许可【new Semaphore(int)中的int -1 操作】
            try {
                semaphore.acquire();

                System.out.println(Thread.currentThread().getName()+"执行了操作");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"执行了操作");

                //重置【new Semaphore(int)中的int +1 操作】
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread001").start();

        new Thread(() -> {
            //注册许可【new Semaphore(int)中的int -1 操作】
            try {
                semaphore.acquire();

                System.out.println(Thread.currentThread().getName()+"执行了操作");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"执行了操作");

                //重置【new Semaphore(int)中的int +1 操作】
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread002").start();
    }
}
