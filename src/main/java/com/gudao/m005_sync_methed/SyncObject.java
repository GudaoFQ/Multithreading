package com.gudao.m005_sync_methed;

/**
 * 使用Object作为lock的Synchronized
 * Author : GuDao
 * 2020-10-27
 */

public class SyncObject {
    private int a = 0;
    private Object lock = new Object();

    public void objectLock(){
        synchronized (lock){
            a++;
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        SyncObject s = new SyncObject();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                s.objectLock();
            }).start();
        }
    }
}
