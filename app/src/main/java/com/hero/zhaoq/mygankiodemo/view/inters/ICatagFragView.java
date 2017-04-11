package com.hero.zhaoq.mygankiodemo.view.inters;

import com.hero.zhaoq.mygankiodemo.modle.DataInfo;

import java.util.List;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.view.inters
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/05   11/44
 *
 * 处理   catagory的数据
 */
public interface ICatagFragView {

    /**
     * 获取当前fragmentItem的类型
     */
    String getCategory();

    /**
     * 更新view   列表数据
     */
    void getDSucesUpdateUI(List<DataInfo> dataList);

}