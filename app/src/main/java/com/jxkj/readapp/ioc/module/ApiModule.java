package com.jxkj.readapp.ioc.module;


import com.jxkj.readapp.ioc.api.Api;
import com.jxkj.readapp.ioc.api.ApiInJava;
import com.jxkj.readapp.ioc.api.ApiService;
import com.jxkj.readapp.ioc.api.ApiServiceInJava;
import com.jxkj.readapp.ioc.api.support.HeaderInterceptor;
import com.jxkj.readapp.ioc.api.support.LoggingInterceptor;
import com.jxkj.readapp.util.LogUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class ApiModule {
    public static final int TIME_OUT = 20 * 1000;

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor());
//        if (BuildConfig.DEBUG) {
//            LoggingInterceptor loggingInterceptor = new LoggingInterceptor(new MyLog());
//            loggingInterceptor.setLevel(LoggingInterceptor.Level.BODY);
//            builder.addInterceptor(loggingInterceptor)
//                    .addNetworkInterceptor(new StethoInterceptor());
//        }
        return builder.build();
    }

    @Singleton
    @Provides
    protected Api provideApi(OkHttpClient okHttpClient) {
      //  LogUtil.e("ioc", okHttpClient + "--->" + Thread.currentThread());
        return Api.getInstance(okHttpClient);
    }

    @Singleton
    @Provides
    protected ApiInJava provideApiInJava(OkHttpClient okHttpClient) {
        LogUtil.e("ioc", okHttpClient + "--->" + Thread.currentThread());
        return ApiInJava.getInstanceInJava(okHttpClient);
    }

    // java serverSide
    @Singleton
    @Provides
    @Named("java")
    protected Api provideJavaApi(OkHttpClient okHttpClient) {
        LogUtil.e("ioc", okHttpClient + "--->" + Thread.currentThread());
        return Api.getInstance(okHttpClient);
    }


    @Singleton
    @Provides
    protected ApiService provideApiService(OkHttpClient okHttpClient) {
        LogUtil.e("ioc", okHttpClient + "--->" + Thread.currentThread());
        return Api.getApiService();
    }

    @Singleton
    @Provides
    protected ApiServiceInJava provideApiServiceInJava(OkHttpClient okHttpClient) {
        LogUtil.e("ioc", okHttpClient + "--->" + Thread.currentThread());
        return ApiInJava.getApiService();
    }

    public static class MyLog implements LoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            LogUtil.json("log---", message);
        }
    }
}