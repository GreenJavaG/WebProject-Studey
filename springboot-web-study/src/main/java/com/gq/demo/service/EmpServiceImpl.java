package com.gq.demo.service;

import com.gq.demo.dao.EmpDao;
import com.gq.demo.dao.EmpDaoImpl;
import com.gq.demo.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 业务逻辑层，处理具体的业务逻辑
* */
@Service
public class EmpServiceImpl implements EmpService{
    //需要注入的bean
    //private EmpDao empDao = new EmpDaoImpl();

    //使用spring的注解进行自动依赖注入
    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> getEmpList() {
        List<Emp> empList = empDao.getData();
        //2、对数据进行转换处理 - gender,job
        empList.stream().forEach(emp -> {
            //处理gender 1：男，2：女
            String gender = emp.getGender();
            if ("1".equals(gender)){
                emp.setGender("男");
            }else if ("2".equals(gender)){
                emp.setGender("女");
            }
            //处理job 1：讲师，2：班主任，3：就业指导
            String job = emp.getJob();
            if ("1".equals(job)){
                emp.setJob("讲师");
            }else if ("2".equals(job)){
                emp.setJob("班主任");
            }else if ("3".equals(job)){
                emp.setJob("就业指导");
            }
        });
        return empList;
    }
}
