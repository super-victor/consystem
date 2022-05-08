package com.sicnu.consystem.Pojo;

/**
 * @ClassName User
 * @Description
 * @Author pan
 * @LastChangeDate 2022/2/7 9:04
 * @Version v1.0
 */

public class User {
    int uid;
    String account; //账号
    String username; //用户名
    String password; //密码
    String phone; //密码
    String email;
    String avatarurl;
    int isforbid;
    int isadmin;


    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarUrl) {
        this.avatarurl = avatarUrl;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsforbid() {
        return isforbid;
    }

    public void setIsforbid(int isforbid) {
        this.isforbid = isforbid;
    }

    public int getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(int isadmin) {
        this.isadmin = isadmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatarurl='" + avatarurl + '\'' +
                ", isforbid=" + isforbid +
                ", isadmin=" + isadmin +
                '}';
    }
}

