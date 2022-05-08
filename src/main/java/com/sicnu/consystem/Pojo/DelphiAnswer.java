package com.sicnu.consystem.Pojo;

import java.util.Date;

/**
 * @ClassName DelphiAnswer
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/4 23:31
 * @Version v1.0
 */

public class DelphiAnswer {

    int daid;
    int uid;
    int mid;
    int dpid;
    String answer;
    Date createtime;


    @Override
    public String toString() {
        return "DelphiAnswer{" +
                "daid=" + daid +
                ", uid=" + uid +
                ", mid=" + mid +
                ", dpid=" + dpid +
                ", answer='" + answer + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    public int getDaid() {
        return daid;
    }

    public void setDaid(int daid) {
        this.daid = daid;
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

    public int getDpid() {
        return dpid;
    }

    public void setDpid(int dpid) {
        this.dpid = dpid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}

