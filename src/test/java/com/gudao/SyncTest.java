package com.gudao;

import com.gudao.m005_sync_methed.SyncObject;

/**
 * Author : GuDao
 * 2020-10-27
 */

public class SyncTest {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                SyncObject s = new SyncObject();
                s.objectLock();
            });
        }
    }
}
