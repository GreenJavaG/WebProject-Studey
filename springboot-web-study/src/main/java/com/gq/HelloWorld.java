package com.gq;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @RequestMapping("hello")
    public String  hello(){
        System.out.println("hello world");
        return "hello world";
    }
}
