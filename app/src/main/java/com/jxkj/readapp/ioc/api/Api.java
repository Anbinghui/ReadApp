package com.jxkj.readapp.ioc.api;


import com.google.gson.GsonBuilder;
import com.jxkj.readapp.common.UrlConfig;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static Api instance;

    public static ApiService apiService;

    public static Api instanceInJava;

    public static ApiService apiServiceInJava;

    public Api(OkHttpClient okHttpClient) {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        addSerializationExclusionStrategy
//        addDeserializationExclusionStrategy().
//        Gson gson = gsonBuilder.serializeNulls().excludeFieldsWithoutExposeAnnotation()
//                .excludeFieldsWithModifiers(Modifier.PROTECTED).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConfig.BASE_URL)//"http://localhost:8080/jx-otm"
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create(/*gson*/)) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

//    public Api(OkHttpClient okHttpClient, String cusUrl) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(cusUrl)//"http://localhost:8080/jx-otm"
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
//                .addConverterFactory(GsonConverterFactory.create(/*gson*/)) // 添加Gson转换器
//                .client(okHttpClient)
//                .build();
//        apiServiceInJava = retrofit.create(ApiService.class);
//    }

    public static Api getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new Api(okHttpClient);
        return instance;
    }

    @Deprecated
    public static ApiService getService(OkHttpClient okHttpClient) {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    apiService = getApiService();//后续改写
                }
            }
        }
        return apiService;
    }

    public static ApiService getApiService() {
        return apiService;
    }
//
//    /***
//     * java 提供的服务端接口
//     *
//     * @param okHttpClient
//     * @return
//     */
//    public static Api getInstanceInJava(OkHttpClient okHttpClient) {
//        if (instanceInJava == null)
//            instanceInJava = new Api(okHttpClient, UrlConfig.BASE_URL_JAVA);
//        return instanceInJava;
//    }


    public synchronized Call<List<Object>> getTask() {
        return apiService.getModuleList();
    }


}
