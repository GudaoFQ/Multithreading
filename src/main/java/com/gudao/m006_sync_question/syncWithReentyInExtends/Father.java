package com.gudao.m006_sync_question.syncWithReentyInExtends;

/**
 * Author : GuDao
 * 2020-10-27
 */
public class Father{
    public synchronized void fatherMethod(){
        System.out.println("father method");
    }
}
