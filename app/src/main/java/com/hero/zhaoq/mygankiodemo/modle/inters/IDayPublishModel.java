package com.hero.zhaoq.mygankiodemo.modle.inters;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.modle.inters
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/05   13/51
 *
 * 每日精选类目的Model
 */
public interface IDayPublishModel extends IModel {

    /**
     * 获取发布干货的日期
     */
    void loadPublishDays();

//        /**
//         * 加载每日精选数据
//         * @param index 日期索引
//         */
//        void loadDayPush(int index);

}