package com.gq.mapper;

import com.gq.pojo.Emp;
import com.gq.pojo.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmpMapper {
    @Select("select * from emp")
    List<Emp> list();

    @Select("select * from emp limit #{start},#{pageSize}")
    List<Emp> getPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);

    @Select("select count(*) from emp")
    Long getTotal();


}
