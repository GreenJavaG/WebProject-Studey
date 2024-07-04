package com.gq.controller;

import com.gq.pojo.Emp;
import com.gq.service.EmpService;
import com.gq.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("输入的登录信息为，{}",emp);
        Emp emp1 = empService.login(emp);
        log.info("查询到的用户信息为：{}",emp1);
        return emp1 != null?Result.success():Result.err("用户不存在");
    }
}
