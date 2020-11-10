package com.gudao.m010_reentrantlock_demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * ReentrantLock实现公平锁
 *
 * 两个线程循环去获取锁和释放锁，来实现公平锁
 *
 * Author : GuDao
 * 2020-11-10
 */

public class ReentrantLockFair implements Runnable {
    ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockFair fair = new ReentrantLockFair();
        Thread t1 = new Thread(fair,"Thread001");
        Thread t2 = new Thread(fair,"Thread002");

        t1.start();
        t2.start();
    }
}
