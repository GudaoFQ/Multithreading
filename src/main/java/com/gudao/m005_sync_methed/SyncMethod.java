package com.gudao.m005_sync_methed;

/**
 * 写在方法前的Synchronized
 * Author : GuDao
 * 2020-10-27
 */

public class SyncMethod {
    private int a = 0;

    //等同于在方法的代码执行时要synchronized(this),锁住的是该类的实例对象
    public synchronized void methodOne(){
        a++;
        System.out.println(a);
    }

    //同步代码块,锁住的是该类的实例对象
    public void methodTwo(){
        synchronized (this){
            a++;
        }
        System.out.println(a);
    }

    public static void main(String[] args) {
        SyncMethod one = new SyncMethod();
        for (int i = 0; i < 10; i++) {
            new Thread(one::methodOne).start();
        }

        SyncMethod two = new SyncMethod();
        for (int i = 0; i < 10; i++) {
            new Thread(two::methodTwo).start();
        }
    }
}
