package com.gq.mapper;

import com.gq.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EmpMapper {
    //根据ID删除数据
    @Delete("delete from emp where id = #{id}")//如果mapper接口方法只有一个普通类型的参数，#{...}里面的属性名可以随便写
    public void delete(Integer id);

    //新增员工
    @Insert("insert into emp(username,name,gender,image,job,entrydate,dept_id,create_time,update_time) " +
            "values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);
}
