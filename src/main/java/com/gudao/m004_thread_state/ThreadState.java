package com.gudao.m004_thread_state;

import java.util.concurrent.TimeUnit;

/**
 * Author : GuDao
 * 2020-10-18
 */

public class ThreadState {
    private static class MyThread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("MyThread : "+i);
            }
        }
    }

    //通过Thread.getState()来获取线程的状态
    public static void main(String[] args) throws InterruptedException {
        Thread threadTest = new MyThread();
        System.out.println(threadTest.getState());
        threadTest.start();
        threadTest.join();
        System.out.println(threadTest.getState());
    }
}
