package com.gudao.m012_interface_question.a1b2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author : GuDao
 * 2020-11-16
 */

public class WithCondition {
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        //定义锁
        Lock lock = new ReentrantLock();

        //定义等待队列
        Condition aICondition = lock.newCondition();
        Condition aCCondition = lock.newCondition();

        new Thread(() -> {
            //所有的condition必须在lock下操作来保证线程安全
            lock.lock();
            try {
                for (int i = 0; i < aI.length; i++) {
                    //打印ai的第一个元素
                    System.out.println(aI[i]);
                    //唤醒ac等待队列中的一个线程到同步队列中去
                    aCCondition.signal();
                    //将当前线程堵塞
                    aICondition.await();
                }
                //for循环结束后最后每个线程各执行了await方法，此处是将最后的线程唤醒
                aCCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放锁，一定要记得
                lock.unlock();
            }
        }).start();

        //方法注释与上面大致相同，不做操作
        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < aC.length; i++) {
                    System.out.println(aC[i]);
                    aICondition.signal();
                    aCCondition.await();
                }
                aICondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
