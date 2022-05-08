package com.sicnu.consystem.Pojo;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Swot
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/5 15:19
 * @Version v1.0
 */

public class Swot {
    String Swotname;
    int sid;
    int uid;
    List<String>advantege;
    List<String>disadvantege;
    List<String>opportunity;
    List<String>threaten;
    Date createtime;

    @Override
    public String toString() {
        return "Swot{" +
                "Swotname='" + Swotname + '\'' +
                ", sid=" + sid +
                ", uid=" + uid +
                ", advantege=" + advantege +
                ", disadvantege=" + disadvantege +
                ", opportunity=" + opportunity +
                ", threaten=" + threaten +
                ", createtime=" + createtime +
                '}';
    }

    public String getSwotname() {
        return Swotname;
    }

    public void setSwotname(String swotname) {
        Swotname = swotname;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<String> getAdvantege() {
        return advantege;
    }

    public void setAdvantege(List<String> advantege) {
        this.advantege = advantege;
    }

    public List<String> getDisadvantege() {
        return disadvantege;
    }

    public void setDisadvantege(List<String> disadvantege) {
        this.disadvantege = disadvantege;
    }

    public List<String> getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(List<String> opportunity) {
        this.opportunity = opportunity;
    }

    public List<String> getThreaten() {
        return threaten;
    }

    public void setThreaten(List<String> threaten) {
        this.threaten = threaten;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}

