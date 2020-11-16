package com.gudao.m012_interface_question.put_get;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用wait和notify/notifyAll来实现
 *
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 *
 * Author : GuDao
 * 2020-11-16
 */

public class WithCondition {
    public static void main(String[] args) {
        ContainerReen<String> container = new ContainerReen();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) container.put("" + j);
            }, "生产者" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) container.get();
            }, "消费者" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(container.getCount());
    }
}

class ContainerReen<T> {
    LinkedList<T> list = new LinkedList();

    Lock lock = new ReentrantLock();

    Condition cliCondtiton = lock.newCondition();
    Condition proCondition = lock.newCondition();

    private final int MAX = 10;
    private final int MIN = 0;
    int count = 0;

    public void put(T value) {
        lock.lock();
        try {
            while (list.size() == MAX) {
                proCondition.await();
            }
            list.add(value);
            ++count;
            System.out.println(Thread.currentThread().getName() + "创建产品：" + value);
            cliCondtiton.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        lock.lock();
        try {
            while (list.size() == MIN) {
                cliCondtiton.await();
            }
            T value = list.removeFirst();
            --count;
            System.out.println(Thread.currentThread().getName() + "消费产品：" + value);
            proCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getCount(){
        return count;
    }
}
