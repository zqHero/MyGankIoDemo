package com.hero.zhaoq.mygankiodemo.view.activitys;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hero.zhaoq.mygankiodemo.R;
import com.hero.zhaoq.mygankiodemo.view.mwidgets.ScrollWebView;

import butterknife.BindView;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.view.activitys
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/11   10/56
 */
public class WebActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.swipe_refresh) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.web_view) ScrollWebView mWebView;

    private String mUrl;

    @Override
    protected int getPageLayoutID() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData() {
        if (getIntent() == null) {
            return;
        }
        mUrl = getIntent().getStringExtra("url");
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true)  给左上角图标的左边加上一个返回的图标 。
        // true 图标可以点击  false 不可以点击。
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initWebViewSettings();
    }

    @Override
    protected void initViewListener() {

        //TODO  重新加载 URL  刷新界面
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.loadUrl(mUrl);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        //webViewClient
        mWebView.setWebChromeClient(new MWebViewChormClient());
        //webViewClient
        mWebView.setWebViewClient(new MWebViewClient());

        mWebView.setOnScrollChangedListener(new ScrollWebView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t) {
                if (mWebView.getScrollY() == 0) {
                    mSwipeRefreshLayout.setEnabled(true);
                } else {
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void process() {
        //TODO  加载数据
        mWebView.loadUrl(mUrl);
    }

    //=====================================Activity  父类 方法  ============================
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //TODO  回退  图标的  点击事件
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //--------------------------------------工具 方法-----------------------------------------
    private void initWebViewSettings() {
        WebSettings settings = mWebView.getSettings();
        // 开启 JS 支持
        settings.setJavaScriptEnabled(true);
        // 支持屏幕缩放
        settings.setSupportZoom(true);
        // 设置出现缩放工具
        settings.setBuiltInZoomControls(true);
        // 不显示webview缩放按钮
        settings.setDisplayZoomControls(false);
        // 扩大比例的缩放
        settings.setUseWideViewPort(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
    }
    //======================================内部类 ============================================
    //TODO  webChromClient
    private class MWebViewChormClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            //TODO 加载 进度
            if(newProgress == 100){
                mProgressBar.setVisibility(View.GONE);
            }else{
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
        }
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //TODO  设置 标题
            mToolbar.setTitle(title);
        }
    }
    //TODO  webClient
    private class MWebViewClient extends WebViewClient {
        //以下  两方法 实现相同效果
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);//在当前的webview中跳转到新的url
            return true;
        }
        //该方法  要求APi>=21
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
////            view.loadUrl(request.getUrl().toString());//在当前的webview中跳转到新的url
//            return true;
//        }
    }

}
