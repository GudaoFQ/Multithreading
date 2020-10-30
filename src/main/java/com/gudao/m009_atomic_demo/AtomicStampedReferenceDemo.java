package com.gudao.m009_atomic_demo;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Author : GuDao
 * 2020-10-30
 */

public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {

        int a = 99, b = 100;

        /**
         * V initialRef : 引用；个人定义为可能修改的对象
         * int initialStamp : 戳；个人定义为版本号
         */
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(a, 1);
        stampedReference.compareAndSet(
                99,//引用的期望值【V initialRef】
                b,//引用的新值
                stampedReference.getStamp(),//版本戳期望值
                stampedReference.getStamp()+1);//版本戳新值

        //返回最新的引用值
        System.out.println("引用值：" + stampedReference.getReference());
        //返回最新的版本戳
        System.out.println("版本戳：" + stampedReference.getStamp());

        //如果当前引用 等于 预期引用, 将更新新的版本戳到内存【修改版本戳】{当前引用值已经变成了b，所以这边以a为引用期望值是不可能修改成功的}
        boolean changeResult = stampedReference.attemptStamp(
                a, //引用的期望值
                stampedReference.getStamp() + 1);//版本戳新值
        System.out.println("修改结果："+changeResult + "，版本戳：" + stampedReference.getStamp());

        boolean weakResult = stampedReference.weakCompareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println("weakCompareAndSet修改结果：" + weakResult);
    }
}
