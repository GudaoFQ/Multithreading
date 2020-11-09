package com.gudao.m009_atomic_demo;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdder、AtomicLong、Synchromiezd效率比较
 *
 * 线程量为10万时
 * longAdder效率最高；sync效率其次；atomic内部使用的cas自旋，效率最低
 *
 * 线程量为1000时
 * longAdder效率最高；atomic效率其次；sync内部使用了锁升级，只升不降，所以升到重量级锁后影响了效率
 *
 * Author : GuDao
 * 2020-11-09
 */

public class LongAdderAtomicLongSynchromiezdCompare {

    static AtomicLong aclong = new AtomicLong();
    static LongAdder longAdder = new LongAdder();
    static Long aLong = 0L;


    public void methedOne(){


    }

    public void methedTwo(){


    }

    public void methedThree(){


    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        LongAdderAtomicLongSynchromiezdCompare compare = new LongAdderAtomicLongSynchromiezdCompare();
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        aclong.incrementAndGet();
                    }
                }
            });
        }
        long startAL = System.currentTimeMillis();
        for (Thread t : threads)t.start();
        for (Thread t : threads)t.join();
        long endAL = System.currentTimeMillis();
        System.out.println("AtomicLong Value：" +aclong.get()+ " Time："+(endAL-startAL));


        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        longAdder.increment();
                    }
                }
            });
        }
        long startLD = System.currentTimeMillis();
        for (Thread t : threads)t.start();
        for (Thread t : threads)t.join();
        long endLD = System.currentTimeMillis();
        System.out.println("LongAdder Value：" +longAdder.longValue()+ " Time："+(endLD-startLD));


        Object lock = new Object();
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        synchronized (lock){
                            aLong++;
                        }
                    }
                }
            });
        }
        long startL = System.currentTimeMillis();
        for (Thread t : threads)t.start();
        for (Thread t : threads)t.join();
        long endL = System.currentTimeMillis();
        System.out.println("Sync Value：" +aLong+ " Time："+(endL-startL));

    }

}
