package com.jxkj.readapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.jxkj.readapp.application.ReadApplication;

public class HooLiganActivity extends Activity {
    private static  HooLiganActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);

        initView();
    }

    private void initView() {


    }

    public static void startHooLigan() {
        Intent intent = new Intent(ReadApplication.getInstance(),HooLiganActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ReadApplication.getInstance().startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    public static void killHooLigan() {
        if(instance!=null) {
            instance.finish();
        }
    }
}
