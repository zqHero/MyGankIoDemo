package com.hero.zhaoq.mygankiodemo.view.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.hero.zhaoq.mygankiodemo.Constant;
import com.hero.zhaoq.mygankiodemo.R;
import com.hero.zhaoq.mygankiodemo.modle.DataInfo;
import com.hero.zhaoq.mygankiodemo.presenter.CategoryPresenter;
import com.hero.zhaoq.mygankiodemo.utils.DimensUtils;
import com.hero.zhaoq.mygankiodemo.view.adapters.CategoryAdapter;
import com.hero.zhaoq.mygankiodemo.view.inters.ICatagFragView;
import com.hero.zhaoq.mygankiodemo.view.mwidgets.SpaceDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.view.fragments
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/01   18/46
 */
public class CategoryFragment extends BaseFragment implements
        ICatagFragView{

    //初始化  所有的  fragment
    public static CategoryFragment newInstance(String categoryTitStr) {
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        args.putString("category", categoryTitStr);
        fragment.setArguments(args);

        return fragment;
    }

    private CategoryPresenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String mCategory;
    private int mOffset;

    private CategoryAdapter mAdapter;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mPresenter.onSaveInstanceState(outState);
        outState.putInt("offset", mRecyclerView.computeVerticalScrollOffset());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int getPageLayoutID() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter = new CategoryPresenter(this);
        if (savedInstanceState != null) {
            //恢复之前的RecyclerView的偏移量
            mOffset = savedInstanceState.getInt("offset");
        }
        if (getArguments() != null) {
            //因为category与UI显示相关，所以放在View中处理
            mCategory = getArguments().getString("category");
        }
        mPresenter.initData(getArguments(), savedInstanceState);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new SpaceDecoration(DimensUtils.dp2px(5)));
        //如果是图片类目，则按瀑布流显示否则按照线性布局显示
        mRecyclerView.setLayoutManager("福利".equals(mCategory)
                ? new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                : new LinearLayoutManager(getContext()));
        mAdapter = new CategoryAdapter();
        //设置是否为每日精选
        mAdapter.setDayPublish("每日精选".equals(mCategory));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initViewListener() {
        //下拉 刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //加载 新的数据
                mPresenter.pullToRefresh(true);
            }
        });
    }

    @Override
    protected void process(Bundle savedInstanceState) {
        mPresenter.process(savedInstanceState);
        //垂直  偏移距离
//        setVerticalOffset(mOffset);
    }

    /**
     * 当前  fragment的  category
     * @return
     */
    @Override
    public String getCategory() {
        return mCategory;
    }

    @Override
    public void getDSucesUpdateUI(List<DataInfo> dataList) {
        //获取  数据成功   刷新数据
        stopPullRefresh();
        mAdapter.setDataList(dataList);
    }

    /**
     * 停止 下拉刷新
     */
    public void stopPullRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void toWebActivity(String url) {

    }
}
