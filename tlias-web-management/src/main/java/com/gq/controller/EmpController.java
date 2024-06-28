package com.gq.controller;

import com.gq.pojo.Emp;
import com.gq.pojo.PageBean;
import com.gq.service.EmpService;
import com.gq.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    /*员工列表查询*/
    @GetMapping
    public Result getList( String name,
                           Short gender,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,//@DateTimeFormat(pattern = "yyyy-MM-dd")约定前端传来数据的格式及类型
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                           @RequestParam(defaultValue = "1") int page,//@RequestParam(defaultValue = "")可以用来设置默认值
                           @RequestParam(defaultValue = "10") int pageSize){
        log.info("分页起始页为{}，每页大小为{}",page,pageSize);
        log.info("姓名为{}，性别为{}，入职日期区间为{}-{}",gender,begin,end);
        PageBean pageBean = empService.getPage(name,gender,begin,end,page,pageSize);
        return Result.success(pageBean);
    }
    /*删除员工(可批量)*/
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable int[] ids){
        log.info("数组为：{}",ids);
        empService.deleteByIds(ids);
        return Result.success();
    }
    /*添加员工*/
    @PostMapping
    public Result addEmp(@RequestBody Emp emp){
        log.info("新增员工的信息为：{}",emp);
        empService.addEmp(emp);
        return Result.success();
    }
}
