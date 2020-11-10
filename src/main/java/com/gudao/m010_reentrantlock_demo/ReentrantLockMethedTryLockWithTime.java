package com.gudao.m010_reentrantlock_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 测试两个线程抢一个锁，通过tryLock(time)来给定抢锁时间
 * 也可以指定tryLock的时间，由于tryLock(time)抛出异常，所以要注意unclock的处理，必须放到finally中
 * 使用reentrantlock可以进行“尝试锁定”tryLock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 *
 * Author : GuDao
 * 2020-11-10
 */

public class ReentrantLockMethedTryLockWithTime {
    ReentrantLock lock = new ReentrantLock();

    public void methed(){
        try {
            //通过tryLock来设置抢锁时间
            boolean lock = this.lock.tryLock(5, TimeUnit.SECONDS);

            if(lock){
                System.out.println(Thread.currentThread().getName()+"抢到锁了");
                //模拟业务代码的执行时间
                TimeUnit.SECONDS.sleep(8);
            }else{
                System.out.println(Thread.currentThread().getName()+"没抢到锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放锁，当然此处不是放锁，其他线程也会抢不到锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockMethedTryLockWithTime time = new ReentrantLockMethedTryLockWithTime();

        new Thread(time::methed,"T001").start();
        new Thread(time::methed,"T002").start();

    }
}
