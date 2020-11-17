package com.gudao.m015_java_reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * 软引用
 * 当系统不够用的时候，会将软引用回收
 *
 * Author : GuDao
 * 2020-11-17
 */

public class SoftReferenceDemo {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024*1024*10];
        SoftReference reference = new SoftReference<>(bytes);

        System.out.println(reference.get());
        System.gc();

        System.out.println(reference.get());

        //向堆内存中添加对象，让其超出设置的大小
        byte[] other = new byte[1024*1024*15];
        System.out.println(reference.get());
    }
}
