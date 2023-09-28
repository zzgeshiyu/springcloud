package com.leran.serial.listener;

import com.leran.serial.manager.SerialPortManager;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialPortListener implements SerialPortEventListener {

    private final Logger log =  LoggerFactory.getLogger(this.getClass());

    private final SerialPortCallback serialPortCallback;

    public SerialPortListener(SerialPortCallback serialPortCallback) {
        this.serialPortCallback = serialPortCallback;
    }

    public void serialEvent(SerialPortEvent serialPortEvent) {
        log.warn("SerialPortTestListener:"+serialPortEvent.getEventType());
        SerialPortManager.SERIAL_CALLBACK_TIME = System.currentTimeMillis()/1000;
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            if (serialPortCallback != null) {
                serialPortCallback.dataAvailable();
            }
        }

    }




}