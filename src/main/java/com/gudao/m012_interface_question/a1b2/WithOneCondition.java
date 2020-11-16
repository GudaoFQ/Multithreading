package com.gudao.m012_interface_question.a1b2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author : GuDao
 * 2020-11-16
 */

public class WithOneCondition {
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        new Thread(() -> {
            try{
                lock.lock();
                for (char c : aI) {
                    System.out.println(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try{
                lock.lock();
                for (char c : aC) {
                    System.out.println(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
