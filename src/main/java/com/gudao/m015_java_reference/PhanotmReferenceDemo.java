package com.gudao.m015_java_reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 虚引用
 * 一般给写JVM的人用的
 *
 * Author : GuDao
 * 2020-11-17
 */

public class PhanotmReferenceDemo {
    private static final List<Object> LIST = new ArrayList<>();
    private static final ReferenceQueue QUEUE = new ReferenceQueue();

    public static void main(String[] args) {
        PhantomReference<GcClassDemo> phantomReference = new PhantomReference<>(new GcClassDemo(), QUEUE);

        //使用线程一直向堆内存中方对象
        new Thread(() -> {
            while (true){
                LIST.add(new byte[1024*1024]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.interrupted();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        //使用线程一直监视队列中是否有数据添加
        new Thread(() -> {
            Reference<? extends GcClassDemo> reference = QUEUE.poll();
            if(reference != null){
                System.out.println("虚引用被回收了"+reference);
            }
        }).start();
    }
}
