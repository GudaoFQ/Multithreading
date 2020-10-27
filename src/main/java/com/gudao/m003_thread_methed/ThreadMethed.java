package com.gudao.m003_thread_methed;

/**
 * Author : GuDao
 * 2020-10-18
 */

public class ThreadMethed {
    public static void main(String[] args) {
        //sleepTest();
        //System.out.println("main");

        //yieldTest();

        joinTest();
    }

    //本线程休息500毫秒，CPU让给其他线程去运行
    static void sleepTest(){
        new Thread(() -> {
            System.out.println("sleepTest start");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sleepTest end");
        }).start();
    }

    //调用yield后的线程将会进入等待队列中【就绪状态】，当CPU空闲后它又会继续执行【本质就是让出一下CPU】
    static void yieldTest(){
        new Thread(() -> {
            for(int i=0;i<100;i++){
                System.out.println("yieldA : " + i);
                if(i%10 == 0){
                    Thread.yield();
                }
            }
        }).start();

        new Thread(() -> {
            for(int i=0;i<100;i++){
                System.out.println("yieldB : " + i);
                if(i%10 == 0){
                    Thread.yield();
                }
            }
        }).start();
    }

    //当执行到join部分是，会让join的线程先执行完成，再执行自己本身的内容
    static void joinTest(){
        Thread threadA = new Thread(() -> {
            //只是为了让A在B的后面执行【让效果更明显】
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<100;i++){
                System.out.println("threadA : " + i);
            }
        });

        Thread threadB = new Thread(() -> {
            System.out.println("threadB start");
            try {
                threadA.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadB End");
        });

        threadA.start();
        threadB.start();
    }
}
