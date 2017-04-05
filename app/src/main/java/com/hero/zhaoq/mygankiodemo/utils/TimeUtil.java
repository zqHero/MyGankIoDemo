package com.hero.zhaoq.mygankiodemo.utils;


import com.hero.zhaoq.mygankiodemo.modle.DataInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by littlejie on 2017/3/12.
 */

public class TimeUtil {

    public static List<DataInfo> convertTime(List<DataInfo> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            DataInfo data = dataList.get(i);
            data.setPublishedTime(TimeUtil.convertTime(data.getPublishedTime()));
            dataList.set(i, data);
        }
        return dataList;
    }

    /**
     * 干货 api 接口获取到的时间格式为： 2017-03-11T11:43:50.30Z
     * 该方法用于处理此时间格式
     * @param time
     * @return
     */
    public static String convertTime(String time) {
        return time.substring(0, time.indexOf("T"));
    }

}
