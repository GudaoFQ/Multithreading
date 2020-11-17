package com.gudao.m015_java_reference;

import java.io.IOException;

/**
 * 普通的引用【强引用】
 *
 * 只有属性的指向为空的时候，才会被垃圾回收机制回收
 *
 * Author : GuDao
 * 2020-11-17
 */

public class NormalReferenceDemo {
    public static void main(String[] args) throws IOException {
        GcClassDemo demo = new GcClassDemo();

        //将其引用指向空值
        demo = null;

        //调用垃圾回收机制
        System.gc();

        //方便看测试效果
        System.in.read();
    }
}
