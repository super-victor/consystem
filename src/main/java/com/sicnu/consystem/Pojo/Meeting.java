package com.sicnu.consystem.Pojo;

import java.util.Date;

/**
 * @ClassName Meeting
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/31 21:42
 * @Version v1.0
 */

public class Meeting {
    int mid;
    String name;
    String theme;
    String  remark;
    Date starttime;
    Date endtime;
    Date creatime;
    String username;
    int uid;

    @Override
    public String toString() {
        return "Meeting{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", theme='" + theme + '\'' +
                ", remark='" + remark + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", creatime=" + creatime +
                ", username='" + username + '\'' +
                ", uid=" + uid +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }
}

