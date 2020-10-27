package com.gudao.m006_sync_question;

import java.util.concurrent.TimeUnit;

/**
 * 同步与非同步方法的互调
 *
 * 同步方法执行的同时是可以执行非同步方法的
 *
 * Author : GuDao
 * 2020-10-27
 */

public class SyncAndUnSyncMethod {
    private int a = 0;

    public synchronized void sync(){
        System.out.println("sync Start");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sync End");
    }

    public void unSync(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("unSync Start");
    }

    public static void main(String[] args) {
        SyncAndUnSyncMethod s = new SyncAndUnSyncMethod();
        new Thread(s::sync).start();
        new Thread(s::unSync).start();
    }
}
