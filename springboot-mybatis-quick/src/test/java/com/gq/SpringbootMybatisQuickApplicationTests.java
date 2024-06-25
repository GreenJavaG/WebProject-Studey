package com.gq;

import com.gq.mapper.EmpMapper;
import com.gq.mapper.UserMapper;
import com.gq.pojo.Emp;
import com.gq.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class SpringbootMybatisQuickApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired

    private EmpMapper empMapper;


    @Test
    public void testSelect() {
        List<User> users = userMapper.select();
        System.out.println(users);
    }

    @Test
    public void testEmpDelete() {
        empMapper.delete(17);
        System.out.println("删除成功");
    }

    @Test
    public void testEmpInsert() {
        Emp emp = new Emp(null, "Tom", null, "汤姆", (short) 1, "1.jpg", (short) 1, LocalDate.of(2000, 1, 1), 1, LocalDateTime.now(), LocalDateTime.now());
        System.out.println(emp);
        empMapper.insert(emp);
        System.out.println("添加成功");
    }
}
