package com.jxkj.readapp.ioc.module;

import android.app.Activity;

import com.jxkj.readapp.ioc.bean.ToastUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private ToastUtil mToastUtil;

    public ActivityModule(Activity mainIncubatorActivity) {
        mToastUtil = new ToastUtil(mainIncubatorActivity);
    }

    @Provides
    ToastUtil provideToastUtil() {
        return mToastUtil;
    }
}