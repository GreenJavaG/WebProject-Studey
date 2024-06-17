package com.gq.demo.controller;

import com.gq.demo.pojo.Emp;
import com.gq.demo.service.EmpService;
import com.gq.demo.service.EmpServiceImpl;
import com.gq.uitl.Result;
import com.gq.uitl.XmlParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/*
* 控制层，接收前端发送的请求，对请求进行处理，并响应数据
* */
@RestController
public class EmpController {
    //需要注入的bean
    //private EmpService empService = new EmpServiceImpl();

    //使用spring注解进行自动注入bean
    @Autowired
    private EmpService empService;

    //使用三层架构解耦合
    @RequestMapping("/listEmp")
    public Result list(){
        List<Emp> empList = empService.getEmpList();
        //3、响应数据
        System.out.println(empList);
        return Result.success(empList);
    }

    //没有分层架构的写法，代码高度耦合，不易维护
    /*@RequestMapping("/listEmp")
    public Result list(){
        //1、加载并解析emp.xml
        //String file ="java\\com\\gq\\demo\\emp.xml";
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        //2、对数据进行转换处理 - gender,job
        empList.stream().forEach(emp -> {
            //处理gender 1：男，2：女
            String gender = emp.getGender();
            if ("1".equals(gender)){
                emp.setGender("男");
            }else if ("2".equals(gender)){
                emp.setGender("女");
            }
            //处理job 1：讲师，2：班主任，3：就业指导
            String job = emp.getJob();
            if ("1".equals(job)){
                emp.setJob("讲师");
            }else if ("2".equals(job)){
                emp.setJob("班主任");
            }else if ("3".equals(job)){
                emp.setJob("就业指导");
            }
        });
        //3、响应数据
        System.out.println(empList);
        return Result.success(empList);
    }*/
}
