package com.sicnu.consystem.Service;

import com.sicnu.consystem.Mapper.UserMapper;
import com.sicnu.consystem.Pojo.User;
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
public class LoginService {

    @Resource
    UserMapper userMapper;
    public User loginService(String username,String password){
        User userByNameAndPwd = userMapper.getUserByNameAndPwd(username, password);
        return userByNameAndPwd;
    }
}

