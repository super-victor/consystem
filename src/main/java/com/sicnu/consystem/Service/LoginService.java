package com.sicnu.consystem.Service;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.UserMapper;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Service.Servicelpml.LoginServicelmpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName LoginService
 * @Description
 * @Author pan
 * @LastChangeDate 2022/2/9 17:24
 * @Version v1.0
 */
@Service
public class LoginService implements LoginServicelmpl {

    @Resource
    UserMapper userMapper;

    @Override
    public User login(String username,String password){
        User userByNameAndPwd = userMapper.getUserByNameAndPwd(username, password);
        return userByNameAndPwd;
    }

}

