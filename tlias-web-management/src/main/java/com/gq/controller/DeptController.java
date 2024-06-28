package com.gq.controller;

import com.gq.pojo.Dept;
import com.gq.service.DeptService;
import com.gq.uitl.Result;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* 部门管理
* */
@Slf4j//日志简化注解
@RestController
@RequestMapping("/depts")
/*@CrossOrigin*/
public class DeptController {
    //记录日志
    //private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    //部门列表数据查询
    @GetMapping
    public Result getDepts(){
        List<Dept> depts = deptService.DeptsList();
        log.info("查询全部部门");
        return Result.success(depts);
    }
    //部门删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        deptService.deleteById(id);
        log.info("删除的部门编号：{}",id);//{}为占位符，会被后面id替换掉
        return Result.success();
    }
    //部门添加
    @PostMapping
    public Result add(@RequestBody Dept dept){
        deptService.addDept(dept);
        log.info("添加部门信息");
        return Result.success();
    }
    //根据ID查询
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        Dept dept = deptService.selectById(id);
        log.info("查询到部门信息:{}",dept);
        return Result.success(dept);
    }
    //修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        log.info("修改部门信息:{}",dept);
        return Result.success();
    }

}
