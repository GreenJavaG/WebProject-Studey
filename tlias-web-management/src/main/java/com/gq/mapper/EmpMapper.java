package com.gq.mapper;

import com.gq.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmpMapper {
    @Select("select * from emp ;")
    List<Emp> getList();
}
