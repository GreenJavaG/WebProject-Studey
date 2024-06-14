package com.gq.controller;

import com.gq.uitl.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResponseController {
    @RequestMapping("/hello1")
    public String hello(){
        System.out.println("Hello World");
        return "Hello World";
    }
    @RequestMapping("/getAddr")
    public Address getAddr(){
        Address addr = new Address();
        addr.setProvince("广东");
        addr.setCity("深圳");
        return addr;
    }
    //用Result同一返回数据的格式
    @RequestMapping("/listAddr")
    public Result listAddr(){
        List<Address> list = new ArrayList<>();
        Address addr = new Address();
        addr.setProvince("广东");
        addr.setCity("深圳");

        Address addr1 = new Address();
        addr1.setProvince("陕西");
        addr1.setCity("西安");

        list.add(addr);
        list.add(addr1);
        return Result.success(list);
    }
}
