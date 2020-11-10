package com.gudao.m010_reentrantlock_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用reentrantlock可以完成sync同样的功能
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 *
 * Author : GuDao
 * 2020-11-09
 */

public class ReentrantLockCompareSync {
    ReentrantLock lock = new ReentrantLock();

    public void methedReentrantLock(){
        try{
            lock.lock();//相当于synchromized
            System.out.println(Thread.currentThread().getName()+"得到了锁");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //与sync的不同就是reentrantLock需要手动释放锁
            lock.unlock();
        }
    }

    public synchronized void methedSynchromized(){
        try{
            System.out.println(Thread.currentThread().getName()+"得到了锁");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }

    }

    public static void main(String[] args) throws Exception {
        ReentrantLockCompareSync reent = new ReentrantLockCompareSync();
        List<Thread> list = new ArrayList<>();

        //测试lock.lock()
        /*for (int i = 0; i < 100; i++) {
            list.add(new Thread(reent::methedReentrantLock));
        }
        list.forEach(e->{
            e.start();
        });*/

        //测试synchronized
        for (int i = 0; i < 100; i++) {
            list.add(new Thread(reent::methedSynchromized));
        }
        list.forEach(e->{
            e.start();
        });
    }
}
