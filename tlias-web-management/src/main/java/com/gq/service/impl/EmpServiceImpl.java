package com.gq.service.impl;

import com.gq.mapper.EmpMapper;
import com.gq.pojo.Emp;
import com.gq.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Emp> getList() {
        List<Emp> empList = empMapper.getList();

        return empList;
    }
}
