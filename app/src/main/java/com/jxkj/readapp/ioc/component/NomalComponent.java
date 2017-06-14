package com.jxkj.readapp.ioc.component;


import com.jxkj.readapp.ioc.bean.UserInfo;
import com.jxkj.readapp.ioc.module.NomalModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

/**
 */
@Singleton
@Component(modules = {NomalModule.class})
public interface NomalComponent {

    @Named("normal")
    UserInfo getUserBeanInfo();
}