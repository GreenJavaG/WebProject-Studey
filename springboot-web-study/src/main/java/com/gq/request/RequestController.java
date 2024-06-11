package com.gq.request;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/*
* 利用postman模拟从get或post请求中获取不同参数
* */
@RestController
public class RequestController {
    @RequestMapping("/PojoParam")
    public String dataParam(User user){
        System.out.println(user);
        return "OK";
    }

    @RequestMapping("/dateParam")
    public String dataParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime updateTime){
        System.out.println(updateTime);
        return "OK";
    }
}
