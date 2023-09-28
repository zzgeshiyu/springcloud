package com.leran.serial.manager;

import com.leran.serial.listener.SerialPortCallback;
import com.leran.serial.listener.SerialPortListener;
import com.leran.serial.utils.ConvertHexStrAndStrUtils;
import gnu.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.concurrent.TimeUnit;

public class SerialPortManager {

    private static final Logger log =  LoggerFactory.getLogger(SerialPortManager.class);

    public static String SERIAL_PORT_NUMBER = "COM1";

    public static final int SERIAL_BAUD_RATE = 9600;

    public static volatile long SERIAL_CALLBACK_TIME = System.currentTimeMillis()/1000;

    public static volatile boolean SERIAL_PORT_STATE = false;

    public static volatile SerialPort SERIAL_PORT_OBJECT = null;

    //  获得系统可用的端口名称列表
    @SuppressWarnings("unchecked")
    public static List<String> getSerialPortList() {
        List<String> systemPorts = new ArrayList<>();
        //获得系统可用的端口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();//获得端口的名字
            systemPorts.add(portName);
        }
        return systemPorts;
    }

    public static void connectSerialPort(){
        try {
            closeSerialPort();
            TimeUnit.MILLISECONDS.sleep(4000);
            if(openSerialPort()){
                System.out.println("serial port com start success");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    //  打开串口  设置中断和监听事件
    public static boolean openSerialPort() {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(SERIAL_PORT_NUMBER);
            CommPort commPort = portIdentifier.open(SERIAL_PORT_NUMBER, 3000);
            if(commPort == null){return false;}
            //判断是不是串口
            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                //设置串口参数（波特率，数据位8，停止位1，校验位无）
                serialPort.setSerialPortParams(SERIAL_BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                // 当有数据到达时唤醒数据接收线程
                serialPort.notifyOnDataAvailable(true);
                // 当串口连接中断时唤醒中断线程
                serialPort.notifyOnBreakInterrupt(true);
                serialPort.notifyOnCarrierDetect(true);
                serialPort.notifyOnDSR(true);
                // 添加串口监听事件
                serialPort.addEventListener(new SerialPortListener(new SerialPortCallback()));
                SerialPortManager.SERIAL_PORT_OBJECT = serialPort;
                SerialPortManager.SERIAL_PORT_STATE = true;
                log.info("open serial port success:" + SERIAL_PORT_NUMBER);
                return true;
            } else {
                //是其他类型的端口
                throw new NoSuchPortException();
            }
        } catch (NoSuchPortException e) {
            log.error("not find serial port:" + e.getMessage());
        } catch (PortInUseException e) {
            log.error("the serial port used:" + e.getMessage());
        } catch (UnsupportedCommOperationException e) {
            log.error("open others serial port:" + e.getMessage());
        } catch (TooManyListenersException e) {
            log.error("the more listener this serial port:"+ e.getMessage());
        }

        return false;
    }

    //  关闭串口
    public static void closeSerialPort() {
        SERIAL_PORT_STATE = false;
        if (SERIAL_PORT_OBJECT != null) {
            SERIAL_PORT_OBJECT.close();
            SERIAL_PORT_OBJECT = null;
            log.info("serial port close");
        }
    }

    //  向串口发送数据
    public static void sendSerialPortData(String data) {
        OutputStream outputStream = null;
        try {
            outputStream = SERIAL_PORT_OBJECT.getOutputStream();
            outputStream.write(ConvertHexStrAndStrUtils.hexStrToBytes(data));
            outputStream.flush();
            log.info("send data success:"+data);
        } catch (IOException e) {
            log.error("read data exception:"+e.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error("read data inputStream close error:"+ e.getMessage());
            }
        }
    }

    //  从串口读取数据
    public static byte[] readSerialPortData() {
        InputStream in = null;
        byte[] bytes = {};
        try {
            TimeUnit.MILLISECONDS.sleep(200);
            in = SERIAL_PORT_OBJECT.getInputStream();
            byte[] readBuffer = new byte[1];
            int bytesNum = in.read(readBuffer);
            while (bytesNum > 0) {
                bytes = concat(bytes, readBuffer);
                bytesNum = in.read(readBuffer);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }


    public static byte[] concat(byte[] firstArray, byte[] secondArray) {
        if (firstArray == null || secondArray == null) {
            return null;
        }
        byte[] bytes = new byte[firstArray.length + secondArray.length];
        System.arraycopy(firstArray, 0, bytes, 0, firstArray.length);
        System.arraycopy(secondArray, 0, bytes, firstArray.length, secondArray.length);
        return bytes;
    }

}

