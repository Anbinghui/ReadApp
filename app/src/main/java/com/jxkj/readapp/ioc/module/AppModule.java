package com.jxkj.readapp.ioc.module;

import android.content.Context;

import com.jxkj.readapp.ioc.bean.AppInfo;
import com.jxkj.readapp.ioc.bean.ToastUtil;
import com.jxkj.readapp.ioc.bean.UserInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context context;
    private ToastUtil toastUtil;

    public AppModule(Context context) {
        this.context = context;
        this.toastUtil=new ToastUtil(context);
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public ToastUtil provideToastUtil() {
        return toastUtil;
    }

    @Provides
    @Singleton
    AppInfo provideAppInfo() {
        return new AppInfo("AppInfo from ---" + this);
    }

    @Provides
    @Singleton
    UserInfo provideUserBeanInfo(Context context) {
        return UserInfo.instance(context);
    }
}