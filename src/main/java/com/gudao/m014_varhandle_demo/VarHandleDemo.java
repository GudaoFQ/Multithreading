package com.gudao.m014_varhandle_demo;

import java.lang.invoke.MethodHandles;

/**
 *
 * varHandle属性实在java9中添加的，运行下列代码需要JDK9的环境
 *
 * Author : GuDao
 * 2020-11-17
 */

/*public class VarHandleDemo {
    private static long num = 0L;
    private static VarHandle handler = new VarHandle();

    //通过静态代码块将handler的指向也指向到0【和num同一个引用】
    static {
        try {
            *//**
             * 变量所在的对象
             * 变量名
             * 变量类型
             *//*
            MethodHandles.lookup().finVarHandler(VarHandleDemo.class, "num", long.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VarHandleDemo demo = new VarHandleDemo();

        //获取handle的值
        System.out.println((int)handler.get(demo));

        //修改demo中的num的值
        handler.set(demo,9);
        System.out.println(t.num);

        //varHandle能对普通属性进行原子性操作
        handler.compareAndSwap(t,9,10);
        System.out.println(t.num);

        //对demo中的num值进行++操作
        handler.getAndAdd(t,10);
        System.out.println(t.num);
    }
}*/
