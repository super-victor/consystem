package com.sicnu.consystem.Pojo;

import java.util.Date;

/**
 * @ClassName Hypoth
 * @Description
 * @Author pan
 * @LastChangeDate 2022/5/3 22:58
 * @Version v1.0
 */
//竞争性假设
public class Hypoth {
    int hid;
    int uid;
    String hname;
    String hypoth;
    String evidence;
    Date createtime;

    @Override
    public String toString() {
        return "Hypoth{" +
                "hid=" + hid +
                ", uid=" + uid +
                ", hname='" + hname + '\'' +
                ", hypoth='" + hypoth + '\'' +
                ", evidence='" + evidence + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHypoth() {
        return hypoth;
    }

    public void setHypoth(String hypoth) {
        this.hypoth = hypoth;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}

