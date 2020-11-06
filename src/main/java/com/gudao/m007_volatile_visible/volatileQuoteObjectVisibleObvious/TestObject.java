package com.gudao.m007_volatile_visible.volatileQuoteObjectVisibleObvious;

/**
 * Author : GuDao
 * 2020-11-06
 */

public class TestObject {
    int a = 0;
    int b = 0;

    public void changData(int a, int b){
        this.a = a;
        this.b = b;
    }
}
