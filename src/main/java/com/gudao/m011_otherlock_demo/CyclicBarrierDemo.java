package com.gudao.m011_otherlock_demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * CyclicBarrier相当于一个栅栏，当值满了之后，相当于栅栏被人推倒了，代码才能继续向下执行
 * 实现原理图看multithreading-cyclicbarrier实现原理图.jpg
 *
 * Author : GuDao
 * 2020-11-10
 */

public class CyclicBarrierDemo {
    //Runnable 参数，这个参数的意思是最后一个到达线程要做的任务
    private static CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
            System.out.println("满员了，发车");
        }
    });

    //人员上车的方法
    public void methed(){
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                //前四个线程将会在此处被阻塞等待，当第五个线程来到后就会向下执行
                try {
                    barrier.await();

                    System.out.println("--线程"+Thread.currentThread().getName()+"报道--");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierDemo demo = new CyclicBarrierDemo();
        System.out.println("主线程开始运行");

        new Thread(demo :: methed).start();

        System.out.println("主线程执行结束");


    }
}
