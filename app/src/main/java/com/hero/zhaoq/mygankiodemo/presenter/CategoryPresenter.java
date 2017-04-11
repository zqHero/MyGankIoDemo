package com.hero.zhaoq.mygankiodemo.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.hero.zhaoq.mygankiodemo.modle.CategoryModel;
import com.hero.zhaoq.mygankiodemo.modle.DataInfo;
import com.hero.zhaoq.mygankiodemo.modle.DayPublishModel;
import com.hero.zhaoq.mygankiodemo.modle.inters.IModel;
import com.hero.zhaoq.mygankiodemo.presenter.inters.ICateFragPresenter;
import com.hero.zhaoq.mygankiodemo.view.inters.ICatagFragView;

import java.util.ArrayList;
import java.util.List;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.presenter
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/05   12/09
 */
public class CategoryPresenter implements ICateFragPresenter {

    private List<DataInfo> mDataList = new ArrayList<>();

    private ICatagFragView catagFragView;  //持有 view 的引用
    private IModel mModel;  //持有  modle的 引用

    private String mCategory;
    private int mCurrentPage;

    public CategoryPresenter(ICatagFragView iCatagFragView) {
        this.catagFragView = iCatagFragView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("data", (ArrayList<DataInfo>) mDataList);
        outState.putInt("page", mCurrentPage);
    }

    @Override
    public void initData(Bundle bundle, Bundle savedInstanceState) {
        mCategory = catagFragView.getCategory();
        //初始化  modle
        initModel();
        mCurrentPage = getDefaultPage();
        //恢复保存的数据
        if (savedInstanceState != null) {
            mDataList = savedInstanceState.getParcelableArrayList("data");
            //恢复之前    保存的请求页数
            mCurrentPage = savedInstanceState.getInt("page", mCurrentPage);
        }
    }

    private void initModel() {
        if ("每日精选".equals(mCategory)) {
            mModel = new DayPublishModel(this);
        } else {
            mModel = new CategoryModel(this);
        }
    }

    private int getDefaultPage() {
        if (TextUtils.isEmpty(mCategory)
                || !"每日精选".equals(mCategory)) {
            return 1;
        }
        return 0;
    }

    /**
     * 数据   获取成功  开始适配
     */
    @Override
    public void getDataSuccess(List<DataInfo> dataList) {
        mDataList.addAll(dataList);
        //更新   view
        catagFragView.getDSucesUpdateUI(mDataList);
    }

    @Override
    public void bindData(Bundle savedInstanceState) {
        //处理  数据信息：
        if (mDataList != null && mDataList.size() != 0) {
            catagFragView.getDSucesUpdateUI(mDataList);
        } else {
            mModel.requestData(mCategory, mCurrentPage);
        }
    }

    @Override
    public void fail(String msg) {
        Log.i("info", "加载数据失败============");
    }

    @Override
    public void pullToRefresh(boolean isLoadMore) {
        mModel.requestData(mCategory, ++mCurrentPage);
    }
}
