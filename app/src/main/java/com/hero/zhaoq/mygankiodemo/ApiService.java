package com.hero.zhaoq.mygankiodemo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/05   13/53
 */
public class ApiService {

    private static GankApi sGankApi;

    public static void init() {
        // TODO  -------------------------------
        Retrofit sRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        sGankApi = sRetrofit.create(GankApi.class);
    }

    public static GankApi getGankApi() {
        return sGankApi;
    }

}

