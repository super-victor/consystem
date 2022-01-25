package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName EmployeeMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/1/8 19:14
 * @Version v1.0
 */
@Mapper
public interface EmployeeMapper {
    //查找所有的用户
    List<Employee>allemployee();
}

