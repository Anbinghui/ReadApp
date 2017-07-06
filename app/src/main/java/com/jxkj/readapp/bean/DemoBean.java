package com.jxkj.readapp.bean;

/**
 * Created by An on 2017/7/5.
 */

public class DemoBean {
    private int id;
    private String title;
    public DemoBean(int id,String title) {
        this.id = id;
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
