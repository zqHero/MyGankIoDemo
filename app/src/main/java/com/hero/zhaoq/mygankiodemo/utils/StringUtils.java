package com.hero.zhaoq.mygankiodemo.utils;

import android.content.Context;

import com.hero.zhaoq.mygankiodemo.MApplication;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.utils
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/01   18/41
 */
public class StringUtils {

    private static Context mContext;

    public static Context getApplicationContext() {
        return MApplication.getInstance();
    }

    public static String[] getStringArray(int array) {
        return getApplicationContext().getResources().getStringArray(array);
    }

}
