package com.hero.zhaoq.mygankiodemo.view.activitys;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hero.zhaoq.mygankiodemo.R;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.view.activitys
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/11   11/59
 *
 * 状态栏 变色    需要变色  以及沉浸式状态栏 都可以  继承此Activtiy
 */
public abstract class StatusBarActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //1 ， 给  状态栏设置颜色
//        先设置状态栏透明属性；
//        给根布局加上一个和状态栏一样大小的矩形View（色块），添加到顶上；
//        然后设置根布局的 FitsSystemWindows 属性为 true,此时根布局会延伸到状态栏，
//        处在状态栏位置的就是之前添加的色块，这样就给状态栏设置上颜色了。
//        // setContentView() 之后调用 setColor(Activity activity, int color) 方法即可。

//        setStatusBarColor(this,getResources().getColor(R.color.cardview_light_background));
//        // 2，图片  作为背景时  状态栏透明：
//        // 这个实现比较简单，根布局背景设置为图片，然后添加状态栏透明 Flag，
//        // 然后设置根布局的 FitsSystemWindows 属性为 true
//        setTranslucent(this);
    }

    //========1,给状态栏  设置颜色 =======================================
    /** * 设置状态栏颜色 * * @param activity 需要设置的activity * @param color 状态栏颜色值 */
    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            //TODO 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);//不要  把  视图挤上去
        }
    }

    /** * 生成一个和状态栏大小相同的矩形条 * * @param activity 需要设置的activity * @param color 状态栏颜色值 * @return 状态栏矩形条 */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }

    //2，   使状态栏透明
    // * 适用于   图片作为背景的界面,此时需要图片填充到状态栏 *
    // * @param activity 需要设置的activity */
    public static void setStatusBarTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true); //TODO
            rootView.setClipToPadding(true);
        }
    }
}
