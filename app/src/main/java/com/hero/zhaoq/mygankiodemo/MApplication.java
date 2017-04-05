package com.hero.zhaoq.mygankiodemo;

import android.app.Application;
import android.content.Context;

import com.hero.zhaoq.mygankiodemo.utils.DimensUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/01   12/00
 */
public class MApplication extends Application {

    private static Context mInstance;

    public static Context getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
    }

    private void init () {
        //TODO 初始化  请求服务
        ApiService.init();  //初始化  Retrofit请求服务
        DimensUtils.init(this);  //

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }


}
