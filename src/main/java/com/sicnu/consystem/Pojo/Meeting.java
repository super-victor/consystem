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
    Date staarttime;
    Date endtime;
    Date creatime;

    @Override
    public String toString() {
        return "Meeting{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", theme='" + theme + '\'' +
                ", remark='" + remark + '\'' +
                ", staarttime=" + staarttime +
                ", endtime=" + endtime +
                ", creatime=" + creatime +
                '}';
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

    public Date getStaarttime() {
        return staarttime;
    }

    public void setStaarttime(Date staarttime) {
        this.staarttime = staarttime;
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

