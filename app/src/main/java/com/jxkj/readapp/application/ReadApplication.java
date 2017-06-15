package com.jxkj.readapp.application;

import android.app.Application;

import com.jxkj.readapp.ioc.component.AppComponent;
import com.jxkj.readapp.ioc.component.DaggerAppComponent;
import com.jxkj.readapp.ioc.module.ApiModule;
import com.jxkj.readapp.ioc.module.AppModule;

/**
 * Created by An on 2017/6/15.
 */

public class ReadApplication extends Application{
    private AppComponent appComponent;
    public static ReadApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).apiModule(new ApiModule()).build();

    }

    public static ReadApplication getInstance() {
        return application;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
