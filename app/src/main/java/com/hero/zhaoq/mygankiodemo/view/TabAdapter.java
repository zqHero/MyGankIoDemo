package com.hero.zhaoq.mygankiodemo.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.ui
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/01   14/19
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList;
    private String[] mTitles;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
        super(fm);
        mFragmentList = fragmentList;
        mTitles = titles;
        notifyDataSetChanged();
    }

    public void setData(List<Fragment> fragmentList, String[] titles) {
        mFragmentList = fragmentList;
        mTitles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList == null ? null : mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? "null" : mTitles[position];
    }
}
