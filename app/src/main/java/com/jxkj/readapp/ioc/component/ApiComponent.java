package com.jxkj.readapp.ioc.component;


import com.jxkj.readapp.ioc.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})//dependencies = AppComponent.class
public interface ApiComponent {
//    MessageActivity inject(MessageActivity activity);
//    TestActivity inject(TestActivity activity);
//    ShoppingTrolleyActivity inject(ShoppingTrolleyActivity activity);
//    MallOrderActivity inject(MallOrderActivity activity);
//    GoodDetailActivity inject(GoodDetailActivity goodDetailActivity);
//    void inject(GroupSetMVPV3Activity activity);
//    void  inject(MyTeamListActivity activity);
}