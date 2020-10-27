package com.gudao.m005_sync_methed;

/**
 * 静态方法加锁
 * 对某个对象加锁
 * Author : GuDao
 * 2020-10-21
 */

public class SyncStatic {

    static int num = 0;

    public synchronized static void methodOne(){
        num++;
        System.out.println(num);
    }

    public static void methodTwo(){
        synchronized (SyncStatic.class){ //此处不能使用this，因为方法是静态方法
            num++;
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(SyncStatic::methodOne).start();
        }
        new Thread(SyncStatic::methodTwo).start();
    }
}
