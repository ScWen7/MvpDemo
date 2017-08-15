package com.scwen7.mvpdemo.common.rxhelper;


import com.scwen7.mvpdemo.common.exception.BaseException;
import com.scwen7.mvpdemo.data.bean.GankRestlt;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by 解晓辉  on 2017/6/4 13:56 *
 * QQ  ：811733738
 * 作用: 对Retrofit 返回数据的预处理
 */

public class RxResultCompat {
    public static <T> ObservableTransformer<GankRestlt<T>, T> handleResult() {
        return new ObservableTransformer<GankRestlt<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<GankRestlt<T>> upstream) {
                return upstream.flatMap(new Function<GankRestlt<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(GankRestlt<T> tBaseResult) throws Exception {

                        if (!tBaseResult.isError()) {
                            return Observable.just(tBaseResult.getResults());
                        } else {
                            return Observable.error(new BaseException("1001", "获取数据异常"));
                        }

                    }
                });
            }
        };
    }
}
