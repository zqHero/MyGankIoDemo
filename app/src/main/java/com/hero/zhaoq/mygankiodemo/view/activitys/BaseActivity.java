package com.hero.zhaoq.mygankiodemo.view.activitys;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hero.zhaoq.mygankiodemo.ActivityManager;

import butterknife.ButterKnife;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.ui
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/01   11/50
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        //设置所有 Activity 全部为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getPageLayoutID());  //初始化   界面布局

        ActivityManager.addActivity(this);

        ButterKnife.bind(this);

        initView();  //初始化   控件
        initData();  //初始化 数据

        initViewListener(); //初始化  事件
        process(); //处理  数据
    }

    //-==-=-=-=-===========需要  子类实现的  抽象 方法：=================
    protected abstract int getPageLayoutID();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initViewListener();

    protected abstract void process();
    //-------------------父类 方法-------------------------------------

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

}
