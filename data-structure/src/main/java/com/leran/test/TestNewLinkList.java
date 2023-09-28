package com.leran.test;

import com.leran.linklist.NewLinkList;

import java.util.HashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class TestNewLinkList {
    public static void main(String[] args) {
        NewLinkList<Integer> newLinkList = new NewLinkList<>();
        for (int i = 1; i < 16; i++) {
            newLinkList.addLast(i);
        }

        newLinkList.head();


    }

}
