package com.leran.serial.listener;

import com.leran.serial.manager.SerialPortManager;
import com.leran.serial.utils.ConvertHexStrAndStrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialPortCallback {

    private final Logger log =  LoggerFactory.getLogger(this.getClass());

    public void dataAvailable() {
        try {
            //throw new Exception();
            byte[] data = SerialPortManager.readSerialPortData();
            String s = ConvertHexStrAndStrUtils.bytesToHexStr(data);
            log.info("rev--data:"+s);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }



}
