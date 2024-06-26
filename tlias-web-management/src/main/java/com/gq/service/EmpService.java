package com.gq.service;

import com.gq.pojo.Emp;
import com.gq.pojo.PageBean;

import java.util.List;

public interface EmpService {
    PageBean getPage(Integer page,Integer pageSize);
}
