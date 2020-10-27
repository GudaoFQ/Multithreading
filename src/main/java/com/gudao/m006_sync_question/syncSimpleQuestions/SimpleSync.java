package com.gudao.m006_sync_question.syncSimpleQuestions;

import java.util.concurrent.TimeUnit;

/**
 * Author : GuDao
 * 2020-10-27
 */

public class SimpleSync {
    private static int a = 0;

    static Thread t1 = new Thread(() -> {
        add();
    },"t001");

    static Thread t2 = new Thread(() -> {
        add();
    },"t002");

    public static /*synchronized*/ void add(){
        for (int i = 0; i < 100; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            a++;
        }
        System.out.println(Thread.currentThread().getName()+"："+a);
    }

    public static void main(String[] args) {
        t1.start();
        t2.start();
    }
}
