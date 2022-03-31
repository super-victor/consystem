package com.sicnu.consystem.Controller;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Service.LoginService;
import com.sicnu.consystem.Service.Servicelpml.LoginServicelmpl;
import com.sicnu.consystem.Util.*;
import com.sicnu.consystem.Util.File.FileUtil;
import com.sicnu.consystem.Util.File.nativeFileUtil;
import com.sun.corba.se.impl.oa.toa.TOA;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    LoginServicelmpl loginService;

    @Resource
    EncryptUtil encryptUtil;


    @Resource
    JwtUtil jwtUtil;

    @Resource
    ServerConfig serverConfig;

    @Resource
    nativeFileUtil fileUtil;
    @PostMapping("/login")
    public BackFrontMessage login(String username, String password, HttpServletResponse response){
        if (username!=null && password !=null){
            User user = loginService.login(username, password);
            if (user!=null){
                String avatarurl = user.getAvatarurl();
                    if (avatarurl.startsWith(fileUtil.getLocalPath())){
//                    user.setAvatarurl(serverConfig.getStaticResouceUrl()+avatarurl.substring(avatarurl.lastIndexOf("\\"),avatarurl.length()));
                        user.setAvatarurl(fileUtil.getNativeAvatarurl(avatarurl));
                    }
                Map<String,Object>userMap=new HashMap<>();
                userMap.put("id",user.getUid());
                userMap.put("username",user.getUsername());
                String token = jwtUtil.generateToken(user.getUsername(), userMap);
                response.setHeader("token",token);
                return new BackFrontMessage(StatusEnum.SUCCESS,user);
            }else {
                return new BackFrontMessage(StatusEnum.NOTFOUND,"用户不存在",null);
            }
        }else {
            return new BackFrontMessage(StatusEnum.FIAL,"账号或密码为空",null);
        }
    }
}

