package com.jxkj.readapp.application;

import android.app.Application;

import com.jiongbull.jlog.JLog;
import com.jiongbull.jlog.constant.LogLevel;
import com.jiongbull.jlog.constant.LogSegment;
import com.jiongbull.jlog.constant.ZoneOffset;
import com.jxkj.readapp.BuildConfig;
import com.jxkj.readapp.R;
import com.jxkj.readapp.ioc.component.AppComponent;
import com.jxkj.readapp.ioc.component.DaggerAppComponent;
import com.jxkj.readapp.ioc.module.ApiModule;
import com.jxkj.readapp.ioc.module.AppModule;
import com.jxkj.readapp.util.AppUtils;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

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
        AppUtils.init(this);
        initLog();


        if(LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }else {
            LeakCanary.install(this);
        }

    }

    private void initLog() {
        List<LogLevel> logLevels = new ArrayList<>();
        logLevels.add(LogLevel.WARN);
        logLevels.add(LogLevel.ERROR);
        logLevels.add(LogLevel.WTF);

        JLog.init().writeToFile(true)
                .setDebug(BuildConfig.DEBUG)
                .setLogSegment(LogSegment.ONE_HOUR)
                .setLogDir(".a"+getString(R.string.app_name))
                .setZoneOffset(ZoneOffset.P0800)
                .setTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
                .setLogLevelsForFile(logLevels)
                .setPackagedLevel(1);
    }


    public static ReadApplication getInstance() {
        return application;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
