package com.gudao.m006_sync_question;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 * 也就是说synchronized获得的锁是可重入的
 * Author : GuDao
 * 2020-10-27
 */

public class SyncWithReenty {
    public synchronized void methodOne(){
        for (int i = 0; i < 5; i++) {
            System.out.println("methodOne：" + i);
        }
        methodTwo();
    }

    public synchronized void methodTwo(){
        for (int i = 5; i < 10; i++) {
            System.out.println("methodTwo：" + i);
        }
    }

    public static void main(String[] args) {
        new SyncWithReenty().methodOne();
    }
}
