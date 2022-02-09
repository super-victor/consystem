package com.sicnu.consystem.Pojo;

/**
 * @ClassName User
 * @Description
 * @Author pan
 * @LastChangeDate 2022/2/7 9:04
 * @Version v1.0
 */

public class User {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

