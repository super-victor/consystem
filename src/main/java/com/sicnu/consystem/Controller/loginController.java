package com.sicnu.consystem.Controller;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Service.LoginService;
import com.sicnu.consystem.Util.StatusEnum;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName loginController
 * @Description
 * @Author pan
 * @LastChangeDate 2022/2/9 17:24
 * @Version v1.0
 */
@RestController
public class loginController {

    @Resource
    LoginService loginService;

    @PostMapping("/login")
    public BackFrontMessage login(String username, String password){
        if (username!=null && password !=null){
            User user = loginService.loginService(username, password);
            if (user!=null){
                return new BackFrontMessage(StatusEnum.SUCCESS,user);
            }else {
                return new BackFrontMessage(StatusEnum.FIAL,"用户不存在",null);
            }
        }else {
            return new BackFrontMessage(StatusEnum.SUCCESS,"账号或密码为空",null);
        }
    }
}

