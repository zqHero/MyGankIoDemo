package com.hero.zhaoq.mygankiodemo.utils;

import android.content.Context;

import com.hero.zhaoq.mygankiodemo.MApplication;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.utils
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/05   12/14
 */
public class DimensUtils {

    private static Context sContext;

    /**
     * 初始化 DisplayUtil
     * @param context ApplicationContext
     */
    public static void init(Context context) {
        sContext = context;
    }

    /**
     * 将 px 转换为 dp 值
     * @param px
     * @return
     */
    public static int px2dp(float px) {
        float scale = sContext.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 将 dp 转换为 sp 值
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {
        float scale = sContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 将 px 转换为 sp 值
     * @param px
     * @return
     */
    public static int px2sp(float px) {
        float scale = sContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 将 sp 转换为 px 值
     * @param sp
     * @return
     */
    public static int sp2px(float sp) {
        float scale = sContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    /**
     * 将 px 转换为 dp 值
     * @param context
     * @param px
     * @return
     */
    public static int px2dp(Context context, float px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 将 dp 转换为 sp 值
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 将 px 转换为 sp 值
     * @param context
     * @param px
     * @return
     */
    public static int px2sp(Context context, float px) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 将 sp 转换为 px 值
     * @param context
     * @param sp
     * @return
     */
    public static int sp2px(Context context, float sp) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }
}
