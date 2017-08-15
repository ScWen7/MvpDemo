package com.scwen7.mvpdemo.presenter;

import com.scwen7.mvpdemo.common.rxhelper.RxExceptionHandler;
import com.scwen7.mvpdemo.data.LoadStatus;
import com.scwen7.mvpdemo.data.bean.GankBean;
import com.scwen7.mvpdemo.model.MvpModel;
import com.scwen7.mvpdemo.ui.view.MvpView;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by 解晓辉 on 2017/8/15.
 * 作用：
 */

public class MvpPresenter extends BasePresenter<MvpModel, MvpView> {


    private int page = 1;

    public MvpPresenter(MvpView view) {
        super(view);
    }

    @Override
    protected MvpModel createModel() {
        return new MvpModel();
    }


    public void getGankData(final LoadStatus loadStatus) {
        if (loadStatus == LoadStatus.REFRESH) {
            page = 1;
        } else {
            page++;
        }
        addRx(mModel.getGankData(page)
                .subscribe(new Consumer<List<GankBean>>() {
                    @Override
                    public void accept(@NonNull List<GankBean> gankBeen) throws Exception {
                        if (loadStatus == LoadStatus.REFRESH) {
                            mView.refreshSuccess(gankBeen);
                        } else {
                            mView.loadMoreSuccess(gankBeen);
                        }
                    }
                }, new RxExceptionHandler<Throwable>(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                        if (loadStatus == LoadStatus.REFRESH) {
                            mView.refreshFailed();
                        } else {
                            mView.loadMoreFailed();
                        }
                    }
                })));
    }
}
