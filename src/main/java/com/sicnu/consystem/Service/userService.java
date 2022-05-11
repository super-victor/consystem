package com.sicnu.consystem.Service;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.UserMapper;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Util.LogUtil;
import com.sicnu.consystem.Util.StatusEnum;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName userService
 * @Description
 * @Author pan
 * @LastChangeDate 2022/2/22 17:59
 * @Version v1.0
 */
@Service
public class userService {

    @Resource
    UserMapper userMapper;

    @Resource
    LogUtil logUtil;

    public User getUserByNameAndPass(String username,String pass){
        User userByNameAndPwd = userMapper.getUserByNameAndPwd(username, pass);
        return userByNameAndPwd;
    }

    public boolean userAdd(User user){
        int i = userMapper.userAdd(user);
        if (i!=0){
//            logUtil.addLog(user.toString());
            logUtil.addLog(UserAuthenticationUtil.getCurrentUser().getUsername()+"添加了用户" +user.toString().replace("\'","\'\'")+" ");
            return true;
        }
        return false;
    }

    public boolean updateUser(User user){
        int i = userMapper.updateUser(user);
        if (i!=0){
            return true;
        }
        return false;
    }

    public boolean deleteUser(int uid){
        int i = userMapper.deleteUserByUid(uid);
        if (i!=0){
            return true;
        }
        return false;
    }
    public List<User>getUsersByCondition(String username,int isforbid,int isAesc){
        username='%'+username+'%';
        return userMapper.getUsersByCondition(username,isforbid,isAesc);
    }

    public List<User>getAllUsers(){
        return userMapper.getAllUsers();
    }

    public BackFrontMessage isadmin(){
        int uid = UserAuthenticationUtil.getCurrentUser().getUid();
        int isadmin = userMapper.isadmin(uid);
        if (isadmin==1){
            return new BackFrontMessage(StatusEnum.SUCCESS,"",isadmin);
        }
        return new BackFrontMessage(StatusEnum.SUCCESS,"",isadmin);
    }

    public BackFrontMessage modifyUserInfo(String username,String emial, String phone, String password){
        int uid = UserAuthenticationUtil.getCurrentUser().getUid();
        int i = userMapper.modifyUserInfo(username, emial, phone, password, uid);
        if (i!=0){
            User userByUid = userMapper.getUserByUid(uid);
            return new BackFrontMessage(StatusEnum.SUCCESS,"修改个人信息成功",userByUid);
        }
        return new BackFrontMessage(StatusEnum.FIAL,"修改个人信息失败",null);
    }
}

