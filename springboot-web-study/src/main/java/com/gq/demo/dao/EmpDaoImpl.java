package com.gq.demo.dao;

import com.gq.demo.pojo.Emp;
import com.gq.uitl.XmlParserUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/*
* 数据访问层（持久层），负责数据访问操作，包括数据的增、删、改、查
* */
@Repository
public class EmpDaoImpl implements EmpDao{
    @Override
    public List<Emp> getData() {
        //1、加载并解析emp.xml
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
