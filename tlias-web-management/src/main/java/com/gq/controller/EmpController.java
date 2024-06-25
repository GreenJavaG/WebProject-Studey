package com.gq.controller;

import com.gq.pojo.Emp;
import com.gq.service.EmpService;
import com.gq.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
* 员工管理
* */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result getList(){
        List<Emp> empList = empService.getList();
        return Result.success();
    }
}
