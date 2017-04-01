package com.hero.zhaoq.mygankiodemo;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.hero.zhaoq.mygankiodemo.view.activitys.BaseActivity;
import com.hero.zhaoq.mygankiodemo.presenter.MainPresenter;
import com.hero.zhaoq.mygankiodemo.view.TabAdapter;
import com.hero.zhaoq.mygankiodemo.view.inters.ImainView;

import java.util.List;

import butterknife.BindView;

/**
 * 仿写  gankIo demo
 */
public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener ,
        ImainView{

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    @BindView(R.id.view_pager) ViewPager mViewPager;

    @Override
    protected int getPageLayoutID() {
        return R.layout.activity_main;
    }

    private TabAdapter mTabAdapter;

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);

        //TODO  初始化 ViewPager=================================
        mTabAdapter = new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private MainPresenter mPresenter;

    @Override
    protected void initData() {
        //TODO  使用MVP  模式  获取  data数据
        mPresenter = new MainPresenter(this);
        mPresenter.requestData(getIntent());
    }

    @Override
    protected void initViewListener() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void process() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        if (item.getTitle().toString() != null){
            Toast.makeText(this,item.getTitle().toString(),Toast.LENGTH_LONG).show();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //===========TODO 从ImainView 继承的方法  实现 viewpager和Tablayout的联动===
    @Override
    public void setSelectPage(int item) {

    }

    @Override
    public void setTab(List<Fragment> fragmentList, String[] titles) {

    }
}
