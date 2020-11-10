package com.gudao.m010_reentrantlock_demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用tryLock进行尝试锁定，不管锁定与否，方法都将继续执行
 * 可以根据tryLock的返回值来判定是否锁定
 * 也可以指定tryLock的时间，由于tryLock(time)抛出异常，所以要注意unclock的处理，必须放到finally中
 *
 * Author : GuDao
 * 2020-11-09
 */

public class ReentrantLockMethedTryLock {
    ReentrantLock lock = new ReentrantLock();

    public void getLock(){
        boolean lockResult = lock.tryLock();
        if (lockResult){
            System.out.println(Thread.currentThread().getName()+"通过getLock获得了锁");
        }
        System.out.println("------------------");
        //不释放锁，其他线程就获取不到锁
        //lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLockMethedTryLock tryLock = new ReentrantLockMethedTryLock();
        //测试通过trylock获取锁
        new Thread(tryLock::getLock).start();
        new Thread(tryLock::getLock).start();
        new Thread(tryLock::getLock).start();
    }
}
