package com.scwen7.mvpdemo.base;

import android.app.Application;

import com.scwen7.mvpdemo.common.http.RetrofitClient;

/**
 * Created by 解晓辉 on 2017/6/30.
 * 作用：
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient.init(this);
    }
}
