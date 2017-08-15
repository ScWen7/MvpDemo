package com.scwen7.mvpdemo.data.bean;

/**
 * Created by 解晓辉 on 2017/8/15.
 * 作用：
 */

public class GankRestlt<T> {
    private boolean error;
    private T results;

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
}
