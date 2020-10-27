package com.gudao.m006_sync_question.interviewQuestions;

import java.util.concurrent.TimeUnit;

/**
 * 另一种执行方法
 * Author : GuDao
 * 2020-10-27
 */

public class AnOtherMothed {
    private double money;

    public synchronized void write(double money){
        //模拟write中的业务，模拟写的操作运行比读的慢
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.money = money;
    }

    public void read(){
        //读取金额
        System.out.println(this.money);
    }

    public static void main(String[] args) {
        AnOtherMothed anOtherMothed = new AnOtherMothed();
        new Thread(() -> {anOtherMothed.write(100);}).start();

        //此时读比写运行快，金额未存到账户中，产生脏读
        anOtherMothed.read();

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //金额存到账户中，再读
        anOtherMothed.read();
    }
}
