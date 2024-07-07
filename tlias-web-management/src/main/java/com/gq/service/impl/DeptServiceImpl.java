package com.gq.service.impl;

import com.gq.mapper.DeptMapper;
import com.gq.pojo.Dept;
import com.gq.pojo.DeptLog;
import com.gq.service.DeptLogService;
import com.gq.service.DeptService;
import com.gq.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpService empService;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> DeptsList() {
        List<Dept> depts = deptMapper.getDeptsList();
        System.out.println(depts);
        return depts;
    }

    @Transactional(rollbackFor = Exception.class)//spring事务管理,rollbackFor指定抓所有异常，如果不指定，只有运行时异常才会回滚
    @Override
    public void deleteById(Integer id) {
        try {
            deptMapper.deleteById(id);

            int i = 1/0;

            empService.deleteByDeptId(id);
        } finally {
            //无论上方删除操作执行成功与否，都会记录日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }
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
