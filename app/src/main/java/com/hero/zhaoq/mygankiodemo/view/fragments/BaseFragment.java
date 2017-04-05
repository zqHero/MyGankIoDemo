package com.hero.zhaoq.mygankiodemo.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.view.fragments
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/01   18/46
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);  //TODO  初始化 数据
    }

    protected abstract void initData(Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getPageLayoutID(), container, false);
    }

    protected abstract int getPageLayoutID();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);  //TODO  添加 注解
        initView(view, savedInstanceState);  //TODO 初始化  View控件
    }

    protected abstract void initView(View view, Bundle savedInstanceState);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewListener();   //TODO 初始化  事件
        bindData(savedInstanceState);  // TODO  绑定  数据
    }

    protected abstract void initViewListener();
    protected abstract void bindData(Bundle savedInstanceState);

    /**
     * 创建菜单，封装解决 Fragment 与 ViewPager 使用时   创建菜单不正确的问题
     */
    protected void createOptionsMenu(Menu menu, MenuInflater inflater) {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //1.判断是否有 Parent，若无，则表明是直接 attach 在 Activity 下
        //2.判断 getUserVisibleHint() 是否为 true，若为 true ，则表示对用户可见
        //综合 1 、 2 .  可以判断出  是否需要创建菜单
        boolean needCreate = getParentFragment() == null || getParentFragment().getUserVisibleHint();
        if (needCreate) {
            createOptionsMenu(menu, inflater);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //如果当前 Fragment 在 ViewPager 中为可见状态，则让 Activity 重绘菜单
        if (isVisibleToUser && getContext() != null) {
            ((Activity) getContext()).invalidateOptionsMenu();
        }
    }
}
