package com.gudao.m007_volatile_visible.volatileQuoteObjectVisibleObvious;

/**
 *
 * 测试Volatile对引用类型的测试，结论：volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 * 测试对象的引用，看此类
 *
 * Author : GuDao
 * 2020-11-05
 */

public class VolatileQuoteObjectVisibleObvious {
    private static volatile TestObject object = new TestObject();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {//循环数太少，结果不明显
                object.changData(i, i);
            }
        },"T001");

        Thread t2 = new Thread(() -> {
            int numA = object.a;
            int numB = object.b;
            if(numA != numB){
                System.out.println("a != b; a = " + numA +" b = "+ numB);
            }
        },"T002");

        t1.start();
        t2.start();

        //防止主线程比两个线程执行快，没出结果就运行结束了
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
