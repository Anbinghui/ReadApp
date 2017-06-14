package com.jxkj.readapp.ioc.bean;

import android.content.Context;
import android.widget.Toast;

/**
 * 管理toast的类，整个app用该类来显示toast
 */
public class ToastUtil {

    private Context mContext;

    public ToastUtil(Context context){
        this.mContext = context;
    }

    public void showToast(String message){
        Toast.makeText(mContext,message+"free", Toast.LENGTH_SHORT).show();
    }


}
