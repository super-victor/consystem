package com.sicnu.consystem.Pojo;

import java.util.Date;

/**
 * @ClassName Log
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/20 16:44
 * @Version v1.0
 */

public class Log {

    int lid;
    String level;
    String type;
    String content;
    Date creattime;

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return creattime;
    }

    public void setDate(Date date) {
        this.creattime = date;
    }

    @Override
    public String toString() {
        return "Log{" +
                "lid=" + lid +
                ", level='" + level + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", creattime=" + creattime +
                '}';
    }
}

