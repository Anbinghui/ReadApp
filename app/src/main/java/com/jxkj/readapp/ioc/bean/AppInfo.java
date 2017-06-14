package com.jxkj.readapp.ioc.bean;

/**
 * Created by Administrator on 2016/11/29.
 */

public class AppInfo {
    public AppInfo(String desc) {
        this.desc = desc;
    }

    private String desc;
    //TODO complete this


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("desc->").append(desc);
        return sb.toString();
    }
}
