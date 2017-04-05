package com.hero.zhaoq.mygankiodemo.presenter.inters;

import android.content.Intent;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.presenter
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/01  14/33
 */
public interface ImainPres {

    //获取  tab的  数据信息
    void requestData(Intent intent);

    //该回调  用于处理  Tab 变化
    void process();

}
