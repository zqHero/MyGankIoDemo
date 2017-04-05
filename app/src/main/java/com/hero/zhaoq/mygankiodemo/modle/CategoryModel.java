package com.hero.zhaoq.mygankiodemo.modle;

import android.util.Log;

import com.hero.zhaoq.mygankiodemo.ApiService;
import com.hero.zhaoq.mygankiodemo.Constant;
import com.hero.zhaoq.mygankiodemo.modle.inters.IModel;
import com.hero.zhaoq.mygankiodemo.presenter.CategoryPresenter;
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
 * Date:2017/04/05   14/18
 */
public class CategoryModel implements IModel {

    private CategoryPresenter mPresenter;

    public CategoryModel(CategoryPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void requestData(String category, int page) {
        ApiService.getGankApi().getData(category, Constant.COUNT, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankInfo<List<DataInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankInfo<List<DataInfo>> listGankInfo) {
                        if (listGankInfo.isError()) {
                            throw new GankException("服务器错误");
                        }
                        List<DataInfo> dataList = TimeUtil.convertTime(listGankInfo.getResults());
                        mPresenter.getDataSuccess(dataList); //  获取数据成功 回调
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
}
