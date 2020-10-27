package com.gudao.m006_sync_question.interviewQuestions;

/**
 * 银行账户
 * Author : GuDao
 * 2020-10-27
 */

public class Account {
    private double money = 0;

    //存钱【同步方法】
    public synchronized void write(double money){
        this.money += money;
        System.out.println("write："+this.money);
    }
    //查看余额
    public double read(){
        System.out.println("read："+money);
        return money;
    }
}
