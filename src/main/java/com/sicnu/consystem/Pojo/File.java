package com.sicnu.consystem.Pojo;

import java.util.Date;

/**
 * @ClassName File
 * @Description
 * @Author pan
 * @LastChangeDate 2022/5/8 16:59
 * @Version v1.0
 */

public class File {
    int fid;
    String fname;
    String furl;
    String fpath;
    Date createtime;

    @Override
    public String toString() {
        return "File{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", furl='" + furl + '\'' +
                ", fpath='" + fpath + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public String getFpath() {
        return fpath;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}

