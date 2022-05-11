package com.sicnu.consystem.Controller;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.UserMapper;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Service.*;
import com.sicnu.consystem.Util.File.nativeFileUtil;
import com.sicnu.consystem.Util.StatusEnum;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName userController
 * @Description
 * @Author pan
 * @LastChangeDate 2022/2/22 17:58
 * @Version v1.0
 */
@RestController
public class userController {
    // TODO: 2022/3/15 用户列表展示的角色展示 ,将Boolean类型转为字符串类型

    @Resource
    userService userService;

    @Resource
    UserMapper userMapper;

    @Resource
    nativeFileUtil fileUtil;



    @PostMapping("/addAvatarImg")
    public BackFrontMessage avatarImg(@RequestParam("file") MultipartFile file){
        String s = null;
        try {
            s = fileUtil.saveFile(file);
            if (s==null){
                return new BackFrontMessage(StatusEnum.FIAL,"上传失败",null);
            }else {
                Map<String,String>avatarUrl=new HashMap<>();
                avatarUrl.put("avatarUrl",s);
                return new BackFrontMessage(StatusEnum.SUCCESS,avatarUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BackFrontMessage(StatusEnum.FIAL,"上传失败",null);
    }


/*
    todo   Required String parameter 'avatarurl' is not present]解决办法
 */

    @PostMapping("/userAdd")
    public BackFrontMessage userAdd(
            @RequestParam("account") String account,
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String phone,
            @RequestParam int isadmin,
            @RequestParam String avatarurl
    ){
        User user=new User();
        user.setPhone(phone);
        user.setAccount(account);
        user.setEmail(email);
        user.setUsername(username);
        user.setIsadmin(isadmin);
        user.setAvatarurl(avatarurl);
        user.setPassword(phone);
        if (user.getUsername()==null&&user.getPassword()==null){
            return  new BackFrontMessage(StatusEnum.FIAL,"添加用户失败",null);
        }
//        User userByNameAndPass = userService.getUserByNameAndPass(user.getUsername(), user.getPassword());
//        if (userByNameAndPass!=null){
//            return new BackFrontMessage(StatusEnum.FIAL,"用户已存在");
//        }
        boolean b = userService.userAdd(user);
        if (b){
            return new BackFrontMessage(StatusEnum.SUCCESS,"添加用户成功",null);
        }
        return new BackFrontMessage(StatusEnum.FIAL,"添加用户失败",null);
    }
    


    @PostMapping(value = "/updateUser")
    public BackFrontMessage updateUser(
            @RequestParam("account") String account,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String username,
            @RequestParam String phone,
            @RequestParam int uid,
            @RequestParam int isadmin,
            @RequestParam int isforbid

    ){
        User user=new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setIsadmin(isadmin);
        user.setUid(uid);
        user.setUsername(username);
        user.setIsforbid(isforbid);
        user.setEmail(email);
        user.setPhone(phone);
        System.out.println(user);
        if (user==null||user.getUid()==0) return new BackFrontMessage(StatusEnum.FIAL,"用户数据出错");
        boolean b = userService.updateUser(user);
        if (b) return new BackFrontMessage(StatusEnum.SUCCESS,"更改用户信息成功");
        return new BackFrontMessage(StatusEnum.FIAL,"更改用户信息失败");
    }

    @DeleteMapping("/deleteUser")
    public BackFrontMessage deleteUser(@RequestParam int uid){

        if (uid<=0) {
            return new BackFrontMessage(StatusEnum.FIAL,"删除用户失败");
        }
        User userByUid = userMapper.getUserByUid(uid);
        if (userByUid==null){
            return new BackFrontMessage(StatusEnum.FIAL,"删除用户失败");
        }
        boolean b = userService.deleteUser(uid);
        if (b) {
            return new BackFrontMessage(StatusEnum.SUCCESS,"删除用户成功");
        }else {
            return new BackFrontMessage(StatusEnum.FIAL,"删除用户失败");
        }
    }

    @GetMapping("/getUsersByConditions")
    public BackFrontMessage getUsersByCondition(@RequestParam String username, @RequestParam int isforbid,@RequestParam int isAesc){
        List<User> usersByCondition = userService.getUsersByCondition(username, isforbid, isAesc);
        if (usersByCondition!=null&&usersByCondition.size()!=0){
            return new BackFrontMessage(StatusEnum.SUCCESS,"查找成功",usersByCondition);
        }
        return new BackFrontMessage(StatusEnum.FIAL,"查找失败");
    }

    @GetMapping("/getAllUsers")
    public BackFrontMessage getAlluUsers(){
        List<User> allUsers = userService.getAllUsers();
        return new BackFrontMessage(StatusEnum.SUCCESS,"查找所有用户成功",allUsers);
    }

    @GetMapping("/isadmin")
    public BackFrontMessage isadmin(){
        BackFrontMessage isadmin = userService.isadmin();
        return isadmin;
    }

    @PostMapping("/modifyUserInfo")
    public BackFrontMessage modifyUserInfo(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String password
    ){
        BackFrontMessage backFrontMessage = userService.modifyUserInfo(username, email, phone, password);
        return backFrontMessage;
    }
}

