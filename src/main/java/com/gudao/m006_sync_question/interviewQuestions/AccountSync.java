package com.gudao.m006_sync_question.interviewQuestions;

/**
 *
 * 存钱添加同步方法，但是查询余额不添加就会产生脏读
 *
 * Author : GuDao
 * 2020-10-27
 */

public class AccountSync {
    public static void main(String[] args) {
        Account account = new Account();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                account.write(10);
                account.read();
            }).start();
        }
    }
}
