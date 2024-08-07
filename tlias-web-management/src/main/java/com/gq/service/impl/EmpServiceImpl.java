package com.gq.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gq.mapper.EmpMapper;
import com.gq.pojo.Emp;
import com.gq.pojo.PageBean;
import com.gq.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    /*@Override
    public PageBean getPage(Integer page,Integer pageSize) {
        //1、获取总记录数
        Long total = empMapper.getTotal();
        //2、获取分页查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> empList = empMapper.getPage(start,pageSize);
        //3、封装PageBean对象
        PageBean pageBean = new PageBean(total, empList);
        return pageBean;
    }*/
    //使用分页插件进行分页
    @Override
    public PageBean getPage(String name,
                            Short gender,
                            LocalDate begin,
                            LocalDate end, Integer page, Integer pageSize) {
        //1、设置分页参数
        PageHelper.startPage(page,pageSize);
        //2、执行查询
        List<Emp> empList = empMapper.alllist(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) empList;
        //3、封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    /**
     *
     * @param ids
     */
    @Override
    public void deleteByIds(int[] ids) {
        empMapper.deleteByIds(ids);
    }

    @Override
    public void addEmp(Emp emp) {
        empMapper.addEmp(emp);
    }

    @Override
    public Emp getById(Integer id) {
        Emp emp = empMapper.selectById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        empMapper.updateById(emp);
    }

    @Override
    public Emp login(Emp emp) {
        Emp emp1 = empMapper.selectByUsernameAndPassword(emp);
        return emp1;
    }

    /*根据部门编号删除员工*/
    @Override
    public void deleteByDeptId(Integer id) {
        empMapper.deleteByDeptId(id);
    }
}
