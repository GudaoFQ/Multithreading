package com.gudao.m007_volatile_visible;

import java.util.ArrayList;
import java.util.List;

/**
 * list引用的可见性验证
 * Author : GuDao
 * 2020-10-29
 */

public class VolatileQuoteListVisible {
    private volatile List list = new ArrayList();

    //list中加参数操作
    public void add(String name){
        this.list.add(name);
    }
    //查看list大小
    public int size(){
        return this.list.size();
    }

    //测试
    public static void main(String[] args) {
        VolatileQuoteListVisible vqlv = new VolatileQuoteListVisible();

        //线程1循化执行，当list的大小等于5的时候就输出结束
        new Thread(() -> {
            while (true){
                if(vqlv.size()==5){
                    return;
                }
            }
        },"T001").start();

        //线程2向list中添加10个参数
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                //这两行代码互换对结果影响比较大
                vqlv.add("T001："+i);
                System.out.println("list新增 T001："+i);
            }
        },"T001").start();
    }
}
