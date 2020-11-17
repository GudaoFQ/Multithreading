package com.gudao.m016_unsafe_demo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * JDK1.8中的unsafe可以通过反射来调用
 * <p>
 * Author : GuDao
 * 2020-11-17
 */

public class UnsafeDemo {
    class Demo {
        public Demo() {
        }

        int num = 1;
    }

    //通过unsafe获取到Demo并修改其参数num
    public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = reflectGetUnsafe();
        Demo demo = (Demo) unsafe.allocateInstance(Demo.class);
        demo.num = 9;
        System.out.println(demo.num);
    }


    /**
     * 反射获取unsafe
     *
     * @return {@link Unsafe}
     */
    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
