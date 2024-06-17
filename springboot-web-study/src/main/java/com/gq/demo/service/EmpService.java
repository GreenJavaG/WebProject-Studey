package com.gq.demo.service;

import com.gq.demo.pojo.Emp;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmpService {
    public List<Emp> getEmpList();
}
