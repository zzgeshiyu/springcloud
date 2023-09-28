package com.leran.serial;

import com.leran.serial.listener.MyLister;
import com.leran.serial.manager.SerialPortManager;
import com.leran.serial.utils.SerialPortUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
//@EnableScheduling
@EnableAsync
public class SerialApplication{


    private final Logger log =  LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SerialApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        try{
//            List<String> portList = SerialPortManager.getSerialPortList();
//            if(!portList.isEmpty()){
//                log.info(portList.toString());
//                SerialPortManager.connectSerialPort();
//            }
//        } catch (Exception e){
//            log.error("获取串口信息失败");
//        }
//
//    }


    @PreDestroy
    public void destroy() {
//        SerialPortManager.closeSerialPort();
//        System.exit(0);
        //关闭应用前 关闭端口
        SerialPortUtil serialPortUtil = SerialPortUtil.getSerialPortUtil();
        serialPortUtil.removeListener(PortInit.serialPort, new MyLister());
        serialPortUtil.closePort(PortInit.serialPort);
    }

}
