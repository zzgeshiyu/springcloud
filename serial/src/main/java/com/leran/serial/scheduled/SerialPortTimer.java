package com.leran.serial.scheduled;


import com.leran.serial.manager.SerialPortManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Service
public class SerialPortTimer {


    @Async
    @Scheduled(cron = "0 0/5 * * * ?")
    public void timeSerialPortScheduled() throws IOException, InterruptedException {
        long now = System.currentTimeMillis();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        System.out.println("timeSerialPortScheduled--"+format.format(now));
        long interval = 2 * 60 * 60;
        long rebootInterval = 24 * 60 * 60;
        long difference =  now/1000 - SerialPortManager.SERIAL_CALLBACK_TIME;

        // 当2个小时内收不到串口数据重启串口
        if(difference > interval){
            SerialPortManager.connectSerialPort();
        }

        //  当24小时内都还是收不到串口数据重启系统
        if(difference > rebootInterval){
            try {
                String osName = System.getProperty("os.name");
                if(osName.startsWith("Windows")) {
                    Runtime.getRuntime().exec("shutdown -r -t 0 -f");
                } else if(osName.startsWith("Linux")){
                    Runtime.getRuntime().exec("reboot");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
