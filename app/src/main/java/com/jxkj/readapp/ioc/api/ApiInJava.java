package com.jxkj.readapp.ioc.api;


import com.jxkj.readapp.common.UrlConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 效力于 java 提供的api接口
 */
public class ApiInJava {


    public static ApiInJava instanceInJava;

    public static ApiServiceInJava apiServiceInJava;

    public ApiInJava(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConfig.BASE_URL_JAVA)//"http://localhost:8080/jx-otm"
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create(/*gson*/)) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        apiServiceInJava = retrofit.create(ApiServiceInJava.class);
    }


    public static ApiServiceInJava getApiService() {
        return apiServiceInJava;
    }

    /***
     * java 提供的服务端接口
     *
     * @param okHttpClient
     * @return
     */
    public static ApiInJava getInstanceInJava(OkHttpClient okHttpClient) {
        if (instanceInJava == null)
            instanceInJava = new ApiInJava(okHttpClient);
        return instanceInJava;
    }



}
