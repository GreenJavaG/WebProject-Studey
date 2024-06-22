package com.gq;

import com.gq.mapper.UserMapper;
import com.gq.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisQuickApplicationTests {
    @Autowired
    UserMapper userMapper;

    
    @Test
    public void testSelect(){
        List<User> users = userMapper.select();
        System.out.println(users);
    }
}
