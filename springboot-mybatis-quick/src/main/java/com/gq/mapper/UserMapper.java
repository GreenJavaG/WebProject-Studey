package com.gq.mapper;

import com.gq.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper//运行时，会自动生成该接口的实现类对象（代理对象），并且将对该对象交给IOC容器管理
public interface UserMapper {
    @Select("select * from user; ")
    public List<User> select();
}
