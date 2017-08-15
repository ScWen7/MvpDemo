package com.scwen7.mvpdemo.model;

import com.scwen7.mvpdemo.common.http.RetrofitClient;
import com.scwen7.mvpdemo.common.rxhelper.RxResultCompat;
import com.scwen7.mvpdemo.common.rxhelper.RxSchedulerHepler;
import com.scwen7.mvpdemo.data.bean.GankBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 解晓辉 on 2017/8/15.
 * 作用：
 */

public class MvpModel {

    public Observable<List<GankBean>> getGankData(int page) {
        return RetrofitClient.getInstance().provideApiService()
                .getGankData(page)
                .compose(RxResultCompat.<List<GankBean>>handleResult())
                .compose(RxSchedulerHepler.<List<GankBean>>io_main());
    }
}
