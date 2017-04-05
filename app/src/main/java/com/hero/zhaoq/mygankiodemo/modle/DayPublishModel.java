package com.hero.zhaoq.mygankiodemo.modle;

import com.hero.zhaoq.mygankiodemo.ApiService;
import com.hero.zhaoq.mygankiodemo.modle.inters.IDayPublishModel;
import com.hero.zhaoq.mygankiodemo.presenter.inters.ICateFragPresenter;
import com.hero.zhaoq.mygankiodemo.utils.TimeUtil;
import com.hero.zhaoq.mygankiodemo.view.mwidgets.GankException;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.modle
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/05   13/49
 */
public class DayPublishModel implements IDayPublishModel {

    private List<String> mPublishDayList;

    private ICateFragPresenter mPresenter;

    public DayPublishModel(ICateFragPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadPublishDays() {
        ApiService.getGankApi().getPublishDays()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankInfo<List<String>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GankInfo<List<String>> listGankInfo) {
                        if (listGankInfo.isError()) {
                            throw new GankException("服务器错误");
                        }
                        mPublishDayList = listGankInfo.getResults();
                        requestData(null, 0);  //请求  每日精选数据
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void requestData(String category, int index) {
        if (mPublishDayList == null) {
            loadPublishDays();
            return;
        }
        String date = convert(mPublishDayList.get(index));
        final DataInfo dInfo = new DataInfo();
        dInfo.setPublishedTime(date);
        ApiService.getGankApi().getDayGank(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankInfo<DayInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankInfo<DayInfo> dayInfoGankInfo) {
                        if (dayInfoGankInfo.isError()) {
                            throw new GankException("服务器错误");
                        }
                        List<DataInfo> dataList = TimeUtil.convertTime(
                                dayInfoGankInfo.getResults()
                                .getData());
                        //将日期添加到数据的最顶部
                        dataList.add(0, dInfo);
                        mPresenter.getDataSuccess(dataList); //获取 数据成功  回调数据
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPresenter.fail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private String convert(String time) {
        return time.replace("-", "/");
    }
}
