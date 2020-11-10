package com.gudao.m010_reentrantlock_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * ReentrantLock可以对interrupt()方法做出响应
 *
 * 当ReentrantLock.lockInterruptinly()被执行的时候，当前线程正在执行的任务将会被打断，执行catch代码块中的业务
 *
 * Author : GuDao
 * 2020-11-10
 */

public class ReentrantLockMethedLockInterruptibly {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行");

                lock.tryLock(10, TimeUnit.SECONDS);

                //捕获线程中的interrup
                lock.lockInterruptibly();

                System.out.println(Thread.currentThread().getName()+"结束执行");
            } catch (InterruptedException e) {

                //t1捕获到interrup之后，try中的代码就会被打断
                System.out.println(Thread.currentThread().getName()+"被打断了");

            }
        },"T001");

        Thread t2 = new Thread(() -> {
            try {
                //获得锁
                lock.lock();

                System.out.println(Thread.currentThread().getName()+"开始执行");

                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

                System.out.println(Thread.currentThread().getName()+"结束执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放锁
                lock.unlock();
            }
        },"T002");

        t2.start();
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //将t1线程打断
        t1.interrupt();
    }
}
