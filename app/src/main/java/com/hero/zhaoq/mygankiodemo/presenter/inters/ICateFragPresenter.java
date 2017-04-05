package com.hero.zhaoq.mygankiodemo.presenter.inters;

import android.os.Bundle;

import com.hero.zhaoq.mygankiodemo.modle.DataInfo;

import java.util.List;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.presenter.inters
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/05   12/05
 */
public interface ICateFragPresenter {
    /**
     * 保存状态
     */
    void onSaveInstanceState(Bundle outState);

    /**
     * 初始化数据
     * @param bundle      参数
     * @param savedInstanceState
     */
    void initData(Bundle bundle, Bundle savedInstanceState);

    void bindData(Bundle savedInstanceState);

    /**
     * 请求分类数据
     */
    void pullToRefresh(boolean isLoadMore);

    /**
     * 加载数据成功，通过 Model 中对应方法获取
     */
    void getDataSuccess(List<DataInfo> dataList);

    /**
     * 网络请求失败
     */
    void fail(String msg);

}
