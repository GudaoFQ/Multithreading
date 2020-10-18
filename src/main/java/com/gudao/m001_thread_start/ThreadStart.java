package com.gudao.m001_thread_start;

import java.util.concurrent.TimeUnit;

/**
 * Author : GuDao
 * 2020-10-18
 */

public class ThreadStart {
    private static class T1 extends Thread{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                try {
                    //sleep 1 微秒
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }

        public static void main(String[] args) {
            //启动T1中的run方法【此时不会去创建一个新的线程去执行任务，只会沿着main线程执行】
            //new T1().run();
            //启动T1进程【此时会启动T1线程，同时执行main线程和T1线程】
            new T1().start();

            for(int i=0;i<10;i++){
                try {
                    //sleep 1 微秒
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("main");
            }
        }
    }
}
