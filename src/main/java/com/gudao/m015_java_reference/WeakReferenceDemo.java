package com.gudao.m015_java_reference;

import java.lang.ref.WeakReference;

/**
 *
 * 弱引用
 *
 * 只要调用GC，弱引用就会被回收
 *
 * 了解下weakHanshMap
 *
 * Author : GuDao
 * 2020-11-17
 */

public class WeakReferenceDemo {
    public static void main(String[] args) {
        WeakReference<GcClassDemo> reference = new WeakReference<>(new GcClassDemo());
        System.out.println(reference.get());

        System.gc();

        System.out.println(reference.get());
    }
}
