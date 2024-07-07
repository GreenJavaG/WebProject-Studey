package com.gq.service;

import com.gq.pojo.Emp;
import com.gq.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageBean getPage(String name,
                     Short gender,
                     LocalDate begin,
                     LocalDate end,
                     Integer page, Integer pageSize);

    void deleteByIds(int[] ids);

    void addEmp(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);

    void deleteByDeptId(Integer id);
}
