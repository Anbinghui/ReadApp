package com.jxkj.readapp.ioc.module;


import com.jxkj.readapp.ioc.bean.UserInfo;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NomalModule {

    @Provides
    @Singleton
    @Named("normal")
    UserInfo provideUserBeanInfo() {
        return new UserInfo();
    }
}