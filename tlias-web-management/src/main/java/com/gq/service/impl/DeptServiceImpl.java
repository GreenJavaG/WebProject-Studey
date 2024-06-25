package com.gq.service.impl;

import com.gq.mapper.DeptMapper;
import com.gq.pojo.Dept;
import com.gq.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> DeptsList() {
        List<Dept> depts = deptMapper.getDeptsList();
        System.out.println(depts);
        return depts;
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void addDept(Dept dept) {
        deptMapper.addDept (dept);
    }

    @Override
    public Dept selectById(Integer id) {
        Dept dept = deptMapper.selectById(id);
        return dept;
    }

    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }
}
