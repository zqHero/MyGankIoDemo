package com.hero.zhaoq.mygankiodemo.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hero.zhaoq.mygankiodemo.Constant;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.view.fragments
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/01   18/46
 */
public class CategoryFragment extends BaseFragment {

    /**
     * 初始化  Tablayout的 数据
     * @param categoryTitStr
     * @return
     */
    public static CategoryFragment newInstance(String categoryTitStr) {
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        args.putString(Constant.EXTRA_CATEGORY, categoryTitStr);
        fragment.setArguments(args);

        return fragment;
    }
}
