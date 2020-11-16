package com.gudao.m012_interface_question.put_get;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 *
 * 此方法中的this.notifyAll()唤醒所有的等待线程，这个可能刚被wait的线程有抢到了执行的机会，又会走while语句，重新再来
 *
 * Author : GuDao
 * 2020-11-16
 */

public class WithSync {
    public static void main(String[] args) {
        Container<String> container = new Container<>();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for(int j=0; j<25; j++)container.put(""+j);
            }, "生产者" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for(int j=0; j<5; j++)container.get();
            }, "消费者" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(container.getCount());
    }
}

class Container<T> {
    //设定容器
    LinkedList<T> container = new LinkedList<T>();
    //设置容器最大值
    final int MAX_CONTAINER_SIZE = 10;
    int count = 0;

    //定义put方法
    public synchronized void put(T value) {
        //容器满了让线程等待
        while (container.size() == MAX_CONTAINER_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        container.add(value);
        ++count;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + "生产了：" + value);
    }

    //定义get方法
    public synchronized T get() {
        while (container.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T value = container.removeFirst();
        count--;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + "消费了：" + value);
        return value;
    }

    //定义get方法
    public int getCount() {
        return count;
    }
}
