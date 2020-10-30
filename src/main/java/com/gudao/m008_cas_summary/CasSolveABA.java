package com.gudao.m008_cas_summary;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *
 * 大学选课小伙伴应该深有体会，一到选课系统卡，崩溃重启还在卡，多人选课多人卡，除了支付都会卡。现在有一门网红网课，课程人数限制为 100 人，这门课程相当火爆，选课系统开启后，半分钟这门课程就达到了 99 人，还剩余1 个名额。学生 1 和 学生 2同时进入系统，并点击了该课程选择课程按钮，每一个名额都有一个相应的版本号进行控制，学生 2 在弹框出现的时候校园网突然断了，学生 1 就轻松选到了课程，但是学生1 发现心仪的女生没有选到此课程，就退掉了此课程，学生 2 网络好了 ，界面也出现虽然还剩下一个名额，但是已被学生 1 操作过，学生 2 拿出上次的版本号，已经无法选择课程。
 *
 * 使用AtomicStampedReference类彻底解决ABA问题
 * Author : GuDao
 * 2020-10-30
 */

public class CasSolveABA {
    public static void main(String[] args) {
        AtomicStampedReference reference = new AtomicStampedReference(99,1);

        Thread t1 = new Thread(() -> {
            //第一次修改，选中当前课程
            boolean one = reference.compareAndSet(99, 100, 1, reference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"第一次修改："+one+"，版本戳："+reference.getStamp());

            //第二次修改，将当前课程取消
            boolean two = reference.compareAndSet(100, 99, 2, reference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"第二次修改："+two+"，版本戳："+reference.getStamp());
        },"T001");

        Thread t2 = new Thread(() -> {
            //模拟网络等待
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //当还能报名时，线程2报名【如果线程二还是认为这个是第一次修改，那么永远修改不了值】
            //boolean one = reference.compareAndSet(99, 100, /*reference.getStamp()*/1, reference.getStamp()+1);
            boolean one = reference.compareAndSet(99, 100, reference.getStamp(), reference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"第一次修改："+one+"，版本戳："+reference.getStamp());
        },"T002");

        t1.start();
        t2.start();

    }
}
