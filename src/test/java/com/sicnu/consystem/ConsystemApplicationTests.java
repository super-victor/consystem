package com.sicnu.consystem;

import com.sicnu.consystem.Mapper.EmployeeMapper;
import com.sicnu.consystem.Mapper.LogMapper;
import com.sicnu.consystem.Mapper.UserMapper;
import com.sicnu.consystem.Pojo.Employee;
import com.sicnu.consystem.Pojo.Log;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Service.LogService;
import com.sicnu.consystem.Util.JwtUtil;
import com.sicnu.consystem.Util.LogUtil;
import com.sicnu.consystem.Util.ServerConfig;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.xml.ws.RespectBinding;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ConsystemApplicationTests {

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    ServerConfig serverConfig;

    @Resource
    JwtUtil jwtUtil;

    @Resource
    LogMapper logMapper;

    @Resource
    LogService logService;

    @Resource
    LogUtil logUtil;
    @Test
    void contextLoads() {
        User zhangsan = userMapper.getUserByNameAndPwd("zhangsan", "123456");
        System.out.println(zhangsan);
    }

    @Test
    void dataTest(){
//        User zhangsan = userMapper.getUserByNameAndPwd("zhangsan", "666666");
//        System.out.println(zhangsan);
        String s = jwtUtil.generateToken("zhangsan");
        System.out.println(s);
//        String getuserfromtoken = jwtUtil.getuserfromtoken(s);
//        System.out.println(getuserfromtoken);
    }

    @Test
    void splittest(){
        String m="D:\\Learning Materials\\web开发\\consystem\\src\\main\\resources\\static\\magazine-unlock-01-2.3.1111-_7797F883A5AB0477084F.jpg";
        String[] split = m.split("\\\\");
//        for (String s : split) {
//            System.out.println(s);
//        }
//        System.out.println(split[split.length-1]);
//        System.out.println(m.matches("\\\\*"));
        System.out.println(m.substring(m.lastIndexOf("\\"), m.length()));
        System.out.println(serverConfig.getLocalUrl());

    }

    @Test
    void  logtest(){
        List<Log> allLogs = logService.getAllLogs();
        allLogs.forEach((Log l)->{
//            Date date = l.getDate();
            System.out.println(l.getDate());
        });
    }

    @Test
    void JwtTest(){
        String user = jwtUtil.generateToken("张三");
        System.out.println(user);
        String getuserfromtoken = jwtUtil.parseToekn(user);
        System.out.println(getuserfromtoken);


        Map<String,Object>cliams=new HashMap<>();
        cliams.put("username","李四");
        cliams.put("id","1");
        String user1 = jwtUtil.generateToken("张三", cliams);

        Claims s = jwtUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsaXNpIiwiciI6InkiLCJpZCI6MiwiZXhwIjoxNjQ3ODA0MTk2LCJpYXQiOjE2NDc3NDM3MTYsInVzZXJuYW1lIjoibGlzaSJ9.2NNrwHjT7yhE3uDwjdMV87yRs-vKx7TBDB5feVw5GU8");
        System.out.println(user1);
        String m=(String)s.get("username");
        int n=(int) s.get("id");
        System.out.println(m);
        System.out.println(n);

    }

    @Test
    void formatetest(){
        String m = String.format("%s,年后", "只是");
        System.out.println("m = " + m);
    }
}
