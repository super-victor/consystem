package com.sicnu.consystem.Util;

public enum StatusEnum {
    SUCCESS(200,"Success"),
    FIAL(500,"Fial"),
    NOTFOUND(404,"NotFound")
    ;
    private int sta;
    private String s;
    StatusEnum(int sta,String s){
        this.sta=sta;
        this.s=s;
    }

    public int getSta() {
        return sta;
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
