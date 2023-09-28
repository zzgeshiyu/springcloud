package com.leran.serial;


import com.leran.serial.listener.MyLister;
import com.leran.serial.utils.SerialPortUtil;
import gnu.io.SerialPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class PortInit implements ApplicationRunner {

    public static SerialPort serialPort = null;

    @Value("${portname}")
    private String portname;


    @Override
    public void run(ApplicationArguments args) {
        //TestA();
        //查看所有串口
        SerialPortUtil serialPortUtil = SerialPortUtil.getSerialPortUtil();
        ArrayList<String> port = serialPortUtil.findPort();
        System.out.println("发现全部串口：" + port);

        System.out.println("打开指定portname:" + portname);
        //打开该对应portname名字的串口
        PortInit.serialPort = serialPortUtil.openPort(portname, 9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        //给对应的serialPort添加监听器
        serialPortUtil.addListener(PortInit.serialPort, new MyLister());
    }

}
