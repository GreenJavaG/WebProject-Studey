package com.gq.mapper;

import com.gq.pojo.Emp;
import com.gq.pojo.PageBean;
import org.apache.ibatis.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Mapper
public interface EmpMapper {
    //@Select("select * from emp")
    List<Emp> alllist(@Param("name") String name,
                   @Param("gender") Short gender,
                   @Param("begin") LocalDate begin,
                   @Param("end") LocalDate end);

    @Select("select * from emp limit #{start},#{pageSize}")
    List<Emp> getPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);

    @Select("select count(*) from emp")
    Long getTotal();


    void deleteByIds(@Param("ids") int[] ids);

    @Insert("insert into emp (username,name,gender,image,job,entrydate,dept_id,create_time,update_time) values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},now(),now())")
    void addEmp(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp selectById(Integer id);

    void updateById(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
