package com.jxkj.readapp.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jxkj.readapp.application.ReadApplication;
import com.jxkj.readapp.ioc.api.Api;
import com.jxkj.readapp.ioc.api.ApiService;
import com.jxkj.readapp.ioc.component.ActivityComponent;
import com.jxkj.readapp.ioc.component.AppComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected AppComponent appComponent;
    protected Api api;
    protected Context mContext;
    protected ApiService apiService;
    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        if(getLayoutId() !=-1) {
            setContentView(getLayoutId());
        }
        mContext = this;
        ButterKnife.bind(this);
        appComponent = ReadApplication.getInstance().getAppComponent();
        api = appComponent.getApi();
        apiService = appComponent.getApi().getApiService();
        activityComponent = appComponent.activityComponent();
        initViews();
        initData();
    }
    public  int getLayoutId() {
        return -1;
    }

    public abstract void initViews();
    public abstract void initData();


}
