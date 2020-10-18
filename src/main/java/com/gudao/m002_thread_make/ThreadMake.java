package com.gudao.m002_thread_make;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Author : GuDao
 * 2020-10-18
 */

public class ThreadMake {
    //继承Thread类
    private static class T1 extends Thread{
        @Override
        public void run() {
            System.out.println("method Thread");
        }
    }
    //实现Runnable接口
    private static class T2 implements Runnable{
        @Override
        public void run() {
            System.out.println("method Runnable");
        }
    }
    //实现Callable接口，此线程带有返回值
    private static class T3 implements Callable{
        @Override
        public Object call() throws Exception {
            System.out.println("mothed Callable");
            return "CallBack";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Thread方法实现
        new T1().start();

        //Runnable方法实现
        new Thread(new T2()).start();

        //Callable方法实现
        FutureTask call = new FutureTask(new T3());
        new Thread(call).start();
        //获取运行的返回值
        System.out.println(call.get());

        //线程池实现
        new Thread(()->{
            System.out.println("mothed ThreadPool");
        }).start();
    }
}
