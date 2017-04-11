package com.hero.zhaoq.mygankiodemo.view.mwidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.view.mwidgets
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/11   11/19
 */
public class ScrollWebView extends WebView {

    private OnScrollChangedListener mOnScrollChangedListener;

    public ScrollWebView(Context context) {
        super(context);
    }

    public ScrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(l, t);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        mOnScrollChangedListener = onScrollChangedListener;
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(int l, int t);
    }

}
