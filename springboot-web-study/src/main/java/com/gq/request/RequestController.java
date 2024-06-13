package com.gq.request;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/*
* 利用postman模拟从get或post请求中获取不同参数接收
* */
@RestController
public class RequestController {
    //1、原始方式
    @RequestMapping("/simpleParam1")
    public String simpleParam1(HttpServletRequest request){
        //获取请求参数
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");

        int age = Integer.parseInt(ageStr);
        System.out.println(name+":"+age);

        return "OK";
    }
    //2、SpringBoot方式
    @RequestMapping("/simpleParam2")//如果方法形参名称与请求参数名称不匹配，可以使用@RequestParam完成映射
    public String simpleParam2(@RequestParam(name = "name") String name, Integer age){
        System.out.println(name+":"+age);

        return "OK";
    }
    //3、实体参数接收数据(复杂实体对象：按照对象层次结构关系即可接收嵌套POJO属性参数)
    @RequestMapping("/simpleParam3")
    public String simpleParam3(User user){
        System.out.println(user);
        return "OK";
    }
    //数组参数：请求参数名与形参数组名称相同且请求参数为多个，定义数组类型形参即可接收参数
    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby){
        System.out.println(Arrays.toString(hobby));
        return "OK";
    }
    //集合参数：请求参数名与形参数组名称相同且请求参数为多个，
    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby){
        System.out.println(hobby);
        return "OK";
    }
    //日期参数：使用@DateTimeFormat注解完成日期参数格式转换
    @RequestMapping("/dateParam")
    public String dataParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime updateTime){
        System.out.println(updateTime);

        return "OK";
    }
    //JSON参数：JSON数据键名与形参对象属性名相同，定义POJO类型参数即可接收参数，需要使用
    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user){
        System.out.println(user);

        return "OK";
    }
}
