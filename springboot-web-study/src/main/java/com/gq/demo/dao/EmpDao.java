package com.gq.demo.dao;

import com.gq.demo.pojo.Emp;

import javax.annotation.Resource;
import java.util.List;

public interface EmpDao {
    public List<Emp> getData();
}
