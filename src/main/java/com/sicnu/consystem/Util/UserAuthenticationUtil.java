package com.sicnu.consystem.Util;

import com.sicnu.consystem.Pojo.User;


public class UserAuthenticationUtil {
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

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