package com.sicnu.consystem;

import com.sicnu.consystem.Mapper.EmployeeMapper;
import com.sicnu.consystem.Pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.xml.ws.RespectBinding;
import java.util.List;

@SpringBootTest
class ConsystemApplicationTests {

    @Resource
    EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
        List<Employee> allemployee = employeeMapper.allemployee();
        System.out.println(allemployee);
    }

}
