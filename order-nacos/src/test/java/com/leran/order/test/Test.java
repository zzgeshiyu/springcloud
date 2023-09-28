package com.leran.order.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        InetAddress local = null;
//        try {
//            local = InetAddress.getLocalHost();
//            InetAddress remote = InetAddress.getByName("www.baidu.com");
//            System.out.println("本机的IP地址：" + local.getHostAddress());
//            System.out.println("itcast的IP地址：" + remote.getHostAddress());
//            System.out.println("itcast的主机名为：" + remote.getHostName());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }

        HashMap<String, String> a = new HashMap<>();
        a.put("11","44");
        a.put(new String("11"),"33");
        System.out.println(a);
    }
}
