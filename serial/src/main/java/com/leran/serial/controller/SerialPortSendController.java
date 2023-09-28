package com.leran.serial.controller;

import com.leran.serial.manager.SerialPortManager;
import com.leran.serial.utils.ConvertHexStrAndStrUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SerialPortSendController {

    //  http://localhost:8781/sendTest?message=mywmyyhtw

    @GetMapping(value = "/sendTest")
    @ResponseBody
    public String sendStringTopic(@RequestParam(name="message",required = true) String message) throws Exception {
//        SerialPortManager.sendSerialPortData(ConvertHexStrAndStrUtils.strToHexStr(message));
        return "success";
    }

}