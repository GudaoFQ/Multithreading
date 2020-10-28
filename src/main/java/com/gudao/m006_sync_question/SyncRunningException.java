package com.gudao.m006_sync_question;

import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中，如果出现异常，默认情况锁会被释放
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况
 * 在第一个线程中抛出异常，其他线程就会进入同步代码区，有可能会访问到异常产生时的数据
 * 因此要非常小心的处理同步业务逻辑中的异常
 * Author : GuDao
 * 2020-10-28
 */

public class SyncRunningException {
    private int a = 0;

    public synchronized void method(){
        while (true){
            a++;
            System.out.println(Thread.currentThread().getName()+"：" + a);

            //让程序看的跟明白使用
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //让程序出现异常
            if (a == 5) {
                /*
                此时运行当前程序的线程会解锁，让其它线程进入
                这是如果是多线程的情况下，如果业务逻辑处理有问题，就会造成其它线程读取的数据有问题
                 */
                int a = Integer.parseInt("a");

                /*//如果不想将当前线程获得的锁释放，就要使用try..catch来让程序继续执行
                try {
                    int a = Integer.parseInt("a");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }

    public static void main(String[] args) {
        SyncRunningException sync = new SyncRunningException();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                sync.method();
            }
        };

        new Thread(run,"T001").start();

        //让T001先执行使用
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(run,"T002").start();
    }
}
