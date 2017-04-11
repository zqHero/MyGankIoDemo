package com.hero.zhaoq.mygankiodemo;


import com.hero.zhaoq.mygankiodemo.modle.DataInfo;
import com.hero.zhaoq.mygankiodemo.modle.DayInfo;
import com.hero.zhaoq.mygankiodemo.modle.GankInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/05   13/54
 */
public interface GankApi {

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
