package com.hero.zhaoq.mygankiodemo.modle;

import java.util.List;

/**
 * Package_name:com.hero.zhaoq.mygankiodemo.modle
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/04/05   13/55
 */
public class GankInfo<T> {

    private List<String> category;
    private boolean error;
    private T results;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GankInfo{" +
                "category=" + category +
                ", error=" + error +
                ", results=" + results +
                '}';
    }
}

