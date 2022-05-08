package com.sicnu.consystem.Pojo;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Questionnaire
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/2 19:34
 * @Version v1.0
 */

public class Questionnaire {
    int dpid;
    String dpname;
    Date createtime;
    List<Delphi>delphis;

    public int getDpid() {
        return dpid;
    }

    public void setDpid(int dpid) {
        this.dpid = dpid;
    }

    public String getDpname() {
        return dpname;
    }

    public void setDpname(String dpname) {
        this.dpname = dpname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public List<Delphi> getDelphis() {
        return delphis;
    }

    public void setDelphis(List<Delphi> delphis) {
        this.delphis = delphis;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "dpid=" + dpid +
                ", dpname='" + dpname + '\'' +
                ", createtime=" + createtime +
                ", delphis=" + delphis +
                '}';
    }
}

