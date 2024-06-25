package com.gq.service;

import com.gq.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> DeptsList();

    void deleteById(Integer id);

    void addDept(Dept dept);

    Dept selectById(Integer id);

    void update(Dept dept);
}
