package com.sicnu.consystem.Controller;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.EmployeeMapper;
import com.sicnu.consystem.Util.Status;
import com.sicnu.consystem.Util.StatusEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName test
 * @Description
 * @Author pan
 * @LastChangeDate 2022/1/8 18:54
 * @Version v1.0
 */
@RestController
public class test {

    @Resource
    EmployeeMapper employeeMapper;

    @GetMapping("/hello")
    public BackFrontMessage test(){
        return new BackFrontMessage(StatusEnum.SUCCESS,employeeMapper.allemployee());
    }
}

