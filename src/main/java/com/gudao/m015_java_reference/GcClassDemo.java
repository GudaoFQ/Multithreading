package com.gudao.m015_java_reference;

/**
 *
 * 此类是为了方便测试，当GC回收当前类的时候会调用该类中的finalize()方法
 *
 * Author : GuDao
 * 2020-11-17
 */

public class GcClassDemo {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("GcClassDemo 被回收了！");
    }
}
