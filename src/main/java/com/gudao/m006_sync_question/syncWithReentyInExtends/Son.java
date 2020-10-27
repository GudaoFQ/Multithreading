package com.gudao.m006_sync_question.syncWithReentyInExtends;

/**
 * Author : GuDao
 * 2020-10-27
 */
public class Son extends Father {
    public synchronized void sonMethod(){
        System.out.println("son Start");
        super.fatherMethod();
        System.out.println("son end");
    }
}
