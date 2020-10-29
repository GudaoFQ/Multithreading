package com.gudao.m007_volatile_visible;

import java.util.concurrent.TimeUnit;

/**
 *
 * volatile加在引用上只能保证引用的可见，不能保证引用中的参数可见
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
