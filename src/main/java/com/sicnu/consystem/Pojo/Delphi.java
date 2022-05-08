package com.sicnu.consystem.Pojo;

import java.util.Date;

/**
 * @ClassName Delphi
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/2 12:18
 * @Version v1.0
 */

public class Delphi {

    int did;
    int uid;
    int type;
    String dname;
    String delphia;
    String delphib;
    String delphic;
    String delphid;
    Date createtime;

    @Override
    public String toString() {
        return "Delphi{" +
                "did=" + did +
                ", uid=" + uid +
                ", type=" + type +
                ", dname='" + dname + '\'' +
                ", delphia='" + delphia + '\'' +
                ", delphib='" + delphib + '\'' +
                ", delphic='" + delphic + '\'' +
                ", delphid='" + delphid + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDelphia() {
        return delphia;
    }

    public void setDelphia(String delphia) {
        this.delphia = delphia;
    }

    public String getDelphib() {
        return delphib;
    }

    public void setDelphib(String delphib) {
        this.delphib = delphib;
    }

    public String getDelphic() {
        return delphic;
    }

    public void setDelphic(String delphic) {
        this.delphic = delphic;
    }

    public String getDelphid() {
        return delphid;
    }

    public void setDelphid(String delphid) {
        this.delphid = delphid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}

