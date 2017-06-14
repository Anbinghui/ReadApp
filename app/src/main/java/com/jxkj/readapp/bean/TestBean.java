package com.jxkj.readapp.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 */
public class TestBean implements Serializable {
    String text;
    String icon;
    @SerializedName("desc")
    String desc;
    @Inject
    public TestBean() {
    }

    public TestBean(String text, String icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static List<TestBean> getDataList() {
        List<TestBean> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(new TestBean("TestBean.this", ""));
        }
        return list;
    }
}
