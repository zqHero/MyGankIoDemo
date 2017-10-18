###前言
####最近在一论坛发现一个不错的开源项目本着学习的目的。就比这原著仿写了一遍。在此先感谢原著。

####修改：
1，项目相比原著  结构更加清晰，对mvp的     vp的 关联进行了拆分。更适合初级学习者学习。
2，使用了沉浸式状态栏。使得app更加一体化。

####上图：

![这里写图片描述](http://img.blog.csdn.net/20170411145334912?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzIzMzA5Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170411145401480?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzIzMzA5Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)![这里写图片描述](http://img.blog.csdn.net/20170411145552794?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzIzMzA5Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

####项目 目录结构：
![这里写图片描述](http://img.blog.csdn.net/20170411150008781?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzIzMzA5Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


###项目介绍：

1，主要类：

retrofit使用：
ApiService.java:

```
public class ApiService {

    private static GankApiServerMethod sGankApi;

    //初始化  Retrofit  请求
    public static void init() {
        // TODO  -------------------------------
        Retrofit sRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        sGankApi = sRetrofit.create(GankApiServerMethod.class);
    }

    public static GankApiServerMethod getGankApi() {
        return sGankApi;
    }
}
```

GankApiServerMethod.java  封装retrofit的 请求方法：

```

/**
 * Package_name:com.hero.zhaoq.mygankiodemo
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/05   13/54
 */
public interface GankApiServerMethod {

    @GET("day/{date}")
    Observable<GankInfo<DayInfo>> getDayGank(@Path("date") String date);

    /**
     * 获取发布干货的日期
     * @return
     */
    @GET("day/history")
    Observable<GankInfo<List<String>>> getPublishDays();

    /**
     * 获取分类数据
     * @param type  数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * @param count 请求个数： 数字，大于0
     * @param page  第几页：数字，大于0
     * @return
     */
    @GET("data/{type}/{count}/{page}")
    Observable<GankInfo<List<DataInfo>>> getData(@Path("type") String type,
                                                 @Path("count") int count, @Path("page") int page);
}

```
MVP使用：  原著将vp放到了一起     不变学习  我便拆开了。
MainPresenter.java:

```
public class MainPresenter implements ImainPres {

    private ImainView mView;//TODO 持有 View的  引用

    private String[] mTitles;
    private List<Fragment> mFragmentList;

    //TODO  实现    view 和   presenter的交互：
    public MainPresenter(ImainView imainView) {
        this.mView = imainView;
    }

    @Override
    public void requestData(Intent intent) {
        //TODO  请求 数据
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

```

CategoryPresenter.java

```
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

```

Design包主要使用了：NavigationView，CoordinatorLayout，AppBarLayout，TabLayout。以及v7包和v4包的CardView，DrawLayout。


#### 沉浸式状态栏的实现：工具类：

```
public class StatusBarUtil {

      /**
     * 设置状态栏颜色
     * @param activity 需要设置的 activity
     * @param color    状态栏颜色值
     */
    public static void setColor(Activity activity, @ColorInt int color) {
        setColor(activity, color, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏颜色
     * @param activity       需要设置的activity
     * @param color          状态栏颜色值
     * @param statusBarAlpha 状态栏透明度
     */

    public static void setColor(Activity activity, @ColorInt int color, int statusBarAlpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(calculateStatusColor(color, statusBarAlpha));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            View fakeStatusBarView = decorView.findViewById(FAKE_STATUS_BAR_VIEW_ID);
            if (fakeStatusBarView != null) {
                if (fakeStatusBarView.getVisibility() == View.GONE) {
                    fakeStatusBarView.setVisibility(View.VISIBLE);
                }
                fakeStatusBarView.setBackgroundColor(calculateStatusColor(color, statusBarAlpha));
            } else {
                decorView.addView(createStatusBarView(activity, color, statusBarAlpha));
            }
            setRootView(activity);
        }
    }

    /**
     * 为滑动返回界面设置状态栏颜色
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     */
    public static void setColorForSwipeBack(Activity activity, int color) {
        setColorForSwipeBack(activity, color, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 为滑动返回界面设置状态栏颜色
     * @param activity       需要设置的activity
     * @param color          状态栏颜色值
     * @param statusBarAlpha 状态栏透明度
     */
    public static void setColorForSwipeBack(Activity activity, @ColorInt int color, int statusBarAlpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            ViewGroup contentView = ((ViewGroup) activity.findViewById(android.R.id.content));
            View rootView = contentView.getChildAt(0);
            int statusBarHeight = getStatusBarHeight(activity);
            if (rootView != null && rootView instanceof CoordinatorLayout) {
                final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) rootView;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    coordinatorLayout.setFitsSystemWindows(false);
                    contentView.setBackgroundColor(calculateStatusColor(color, statusBarAlpha));
                    boolean isNeedRequestLayout = contentView.getPaddingTop() < statusBarHeight;
                    if (isNeedRequestLayout) {
                        contentView.setPadding(0, statusBarHeight, 0, 0);
                        coordinatorLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                coordinatorLayout.requestLayout();
                            }
                        });
                    }
                } else {
                    coordinatorLayout.setStatusBarBackgroundColor(calculateStatusColor(color, statusBarAlpha));
                }
            } else {
                contentView.setPadding(0, statusBarHeight, 0, 0);
                contentView.setBackgroundColor(calculateStatusColor(color, statusBarAlpha));
            }
            setTransparentForWindow(activity);
        }
    }

    /**
     * 设置状态栏纯色 不加半透明效果
     * @param activity 需要设置的 activity
     * @param color    状态栏颜色值
     */
    public static void setColorNoTranslucent(Activity activity, @ColorInt int color) {
        setColor(activity, color, 0);
    }



    /**
     * 使状态栏半透明
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     * @param activity 需要设置的activity
     */
    public static void setTranslucent(Activity activity) {
        setTranslucent(activity, DEFAULT_STATUS_BAR_ALPHA);
    }
}

```


Github:https://github.com/zqHero/MyGankIoDemo

csdn:http://blog.csdn.net/u013233097/article/details/70054659


再次感谢一下作者：
http://www.open-open.com/lib/view/open1455584716230.html
http://jaeger.itscoder.com/android/2016/02/15/status-bar-demo.html
http://www.apkbus.com/thread-272535-1-1.html
https://github.com/MyLifeMyTravel/GankIO




仅供学习交流使用，若有侵犯，立马删除。

