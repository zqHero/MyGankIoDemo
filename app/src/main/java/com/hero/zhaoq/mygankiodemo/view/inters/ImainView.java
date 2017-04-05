package com.hero.zhaoq.mygankiodemo.view.inters;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.view.inters
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/01   14/32
 *
 * main页面的  view  处理的  接口封装
 */
public interface ImainView {

    //选中  viewpager页
    void setSelectPage(int item);

    //viewpager  设置    Tab变化
    void setTab(List<Fragment> fragmentList, String[] titles);
}
