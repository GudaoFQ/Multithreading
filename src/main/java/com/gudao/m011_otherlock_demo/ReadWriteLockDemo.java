package com.gudao.m011_otherlock_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * 通过读写锁，普通情况下8个线程需要8秒处理完的业务，在读写锁的情况下只用了1秒读锁+2秒写锁
 *
 * Author : GuDao
 * 2020-11-11
 */

public class ReadWriteLockDemo {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock writeLock = lock.writeLock();
    Lock readLock = lock.readLock();

    String name = "gudao";

    public void read(){
        try {
            readLock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("读取了：name "+name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write(String name){
        try {
            writeLock.lock();
            TimeUnit.SECONDS.sleep(1);
            this.name = name;
            System.out.println("修改了：name "+name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }



    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();

        for (int i = 0; i < 6; i++) {
            new Thread(demo :: read).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                demo.write("test");
            }).start();
        }
    }
}
