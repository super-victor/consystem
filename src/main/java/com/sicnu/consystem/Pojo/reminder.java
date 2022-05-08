package com.sicnu.consystem.Pojo;

import java.util.Date;

/**
 * @ClassName reminder
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/19 22:03
 * @Version v1.0
 */

public class reminder {
    int rid;
    int uid;
    String content;
    String title;
    Date createtime;

    @Override
    public String toString() {
        return "reminder{" +
                "rid=" + rid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

