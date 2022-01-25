package com.sicnu.consystem.Json;

import com.sicnu.consystem.Util.StatusEnum;

/**
 * @Description 用户登录操作时返回给前端的json
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

public class BackFrontMessage {
    // 200成功
    // 300账户被锁
    // 301权限不足
    // 500失败
    private int status;
    private String msg;
    private Object object;


    public BackFrontMessage(StatusEnum statusEnum,Object object){
        this.msg=statusEnum.getS();
        this.status=statusEnum.getSta();
        this.setObject(object);
    }
    public BackFrontMessage(int status, String msg, Object object) {
        this.status = status;
        this.msg = msg;
        this.setObject(object);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
