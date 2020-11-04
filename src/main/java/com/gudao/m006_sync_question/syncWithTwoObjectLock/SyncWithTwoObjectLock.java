package com.gudao.m006_sync_question.syncWithTwoObjectLock;

import java.util.concurrent.TimeUnit;

/**
 * Author : GuDao
 * 2020-11-02
 */

public class SyncWithTwoObjectLock {
    private Object lock = new Object();

    public void syncMethed(){
        synchronized (lock){
            while (true){
                System.out.println(Thread.currentThread().getName());

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SyncWithTwoObjectLock lock = new SyncWithTwoObjectLock();
        new Thread(lock :: syncMethed,"Thread001").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //将SyncWithTwoObjectLock中的object锁改变
        lock.lock = new Object();
        //此时main线程也能进入syncMethed方法
        lock.syncMethed();
    }
}
