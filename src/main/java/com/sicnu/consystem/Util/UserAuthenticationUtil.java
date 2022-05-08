package com.sicnu.consystem.Util;

import com.sicnu.consystem.Pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserAuthenticationUtil {
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

//    public static List<Map<String,String>>userlist=new ArrayList<>();
//    todo 直接保存string 如 mid=1&uid=2
    public  static List<String>userLisut=new ArrayList<>();
    public static void setCurrentUser(User user) {
        threadLocal.set(user);
    }

    public static User getCurrentUser() {
        return threadLocal.get();
    }

    public static void removeUser() {
        threadLocal.remove();
    }

}