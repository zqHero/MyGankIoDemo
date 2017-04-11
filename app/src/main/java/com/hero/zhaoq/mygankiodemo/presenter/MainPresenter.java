package com.hero.zhaoq.mygankiodemo.presenter;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.hero.zhaoq.mygankiodemo.R;
import com.hero.zhaoq.mygankiodemo.presenter.inters.ImainPres;
import com.hero.zhaoq.mygankiodemo.utils.StringUtils;
import com.hero.zhaoq.mygankiodemo.view.fragments.CategoryFragment;
import com.hero.zhaoq.mygankiodemo.view.inters.ImainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.presenter
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/01   14/34
 *
 * main界面的 业务逻辑封装
 */
public class MainPresenter implements ImainPres {

    private ImainView mView;

    private String[] mTitles;
    private List<Fragment> mFragmentList;

    //TODO  实现   view和presenter的交互：
    public MainPresenter(ImainView imainView) {
        this.mView = imainView;
    }

    @Override
    public void requestData(Intent intent) {
        //请求 数据
        mTitles = StringUtils.getStringArray(R.array.tab_str_arr);
        initFragmentList();//TODO
    }

    /**
     * 初始化 fragment
     */
    private void initFragmentList() {
        mFragmentList = new ArrayList<>();
//        mFragmentList.add(DayPushFragment.newInstance());

        for (int i = 0; i < mTitles.length; i++) {
            mFragmentList.add(CategoryFragment.newInstance(mTitles[i]));
        }
    }

    @Override
    public void process() {
        mView.setTab(mFragmentList, mTitles);
        mView.setSelectPage(0);
    }
}
