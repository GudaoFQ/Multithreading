package com.gudao.m007_volatile_visible;

import java.util.concurrent.TimeUnit;

/**
 * 测试Volatile对引用类型的测试，结论：volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 * 该示例效果不明显，Object引用对象测试Volatile请看VolatileQuoteObjectVisibleObvious
 * 注意：下面的示例中由于sleep操作过多，这些时间已经够将引用写到内存中去了，所以每次执行都是正确的，推荐看VolatileQuoteListVisible
 *
 * Author : GuDao
 * 2020-10-29
 */

public class VolatileQuoteObjectVisible {
    boolean status = true;
    static volatile VolatileQuoteObjectVisible vqb = new VolatileQuoteObjectVisible();

    Thread tOne = new Thread(() -> {
        //System.out.println(Thread.currentThread().getName()+"：开始");
        while (vqb.status){

        }
        //System.out.println(Thread.currentThread().getName()+"：结束");
    },"001");

    Thread tTwo = new Thread(() -> {
        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //System.out.println(Thread.currentThread().getName()+"：修改了引用中的status");
        vqb.status = false;

    },"002");

    public static void main(String[] args) {
        VolatileQuoteObjectVisible volatileQuoteVisible = new VolatileQuoteObjectVisible();
        volatileQuoteVisible.tOne.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        volatileQuoteVisible.tTwo.start();
    }
}
