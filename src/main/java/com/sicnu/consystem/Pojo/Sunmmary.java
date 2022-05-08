package com.sicnu.consystem.Pojo;

import java.util.Date;

/**
 * @ClassName Sunmmary
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/8 12:53
 * @Version v1.0
 */

public class Sunmmary {
    int id;
    int mid;
    String content;
    Date createtime;

    @Override
    public String toString() {
        return "Sunmmary{" +
                "id=" + id +
                ", mid=" + mid +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}

