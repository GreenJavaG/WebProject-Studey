package com.gq.mapper;

import com.gq.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Repository
@Mapper
public interface DeptMapper {
    @Select("select * from dept;")
    List<Dept> getDeptsList();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept (name,create_time,update_time) values(#{name},now(),now()) ")
    void addDept(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept selectById(Integer id);

    @Update("update dept set name = #{name} ,update_time = now() where id = #{id} ")
    void update(Dept dept);
}
