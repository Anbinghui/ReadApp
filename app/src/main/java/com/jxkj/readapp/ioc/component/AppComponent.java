package com.jxkj.readapp.ioc.component;

import android.content.Context;

import com.jxkj.readapp.ioc.api.Api;
import com.jxkj.readapp.ioc.api.ApiInJava;
import com.jxkj.readapp.ioc.api.ApiService;
import com.jxkj.readapp.ioc.bean.AppInfo;
import com.jxkj.readapp.ioc.bean.ToastUtil;
import com.jxkj.readapp.ioc.bean.UserInfo;
import com.jxkj.readapp.ioc.module.ApiModule;
import com.jxkj.readapp.ioc.module.AppModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

/**
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    ActivityComponent activityComponent();

    Context getContext();
    Api getApi();

    @Named("java")
    Api getApiInJava_();

    ApiInJava getApiInJava();

    ApiService getApiService();

    ToastUtil getToastUtil();

    AppInfo getAppInfo();

    UserInfo getUserInfo();

  //  TestBean getTestBean();

//    MessagePresenterImpl getPresenter();

}