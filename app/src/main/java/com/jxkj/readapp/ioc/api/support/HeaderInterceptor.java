package com.jxkj.readapp.ioc.api.support;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Retrofit2 Cookie拦截器。用于保存和设置Cookies
 */
public final class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        if (original.url().toString().contains("book/") ||
                original.url().toString().contains("task-list/") ||
                original.url().toString().contains("top/") ||
                original.url().toString().contains("post/") ||
                original.url().toString().contains("user/")) {
            Request request = original.newBuilder()
                    .addHeader("User-Agent", "jxClient/3.40[preload=false;locale=zh_CN;clientidbase=android-nvidia]") // 不能转UTF-8
                    .addHeader("X-User-Agent", "jxClient/3.40[preload=false;locale=zh_CN;clientidbase=android-nvidia]")
//                    .addHeader("X-Device-Id", DeviceUtils.getIMEI(AppUtils.getAppContext()))
                    .addHeader("Host", "api.jxkj.com")
                    .addHeader("Connection", "Keep-Alive")
                    .addHeader("If-None-Match", "W/\"2a04-4nguJ+XAaA1yAeFHyxVImg\"")
                    .addHeader("If-Modified-Since", "Tue, 02 Aug 2016 03:20:06 UTC")
                    .build();
            return chain.proceed(request);
        }
        return chain.proceed(original);
    }
}
