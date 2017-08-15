package com.scwen7.mvpdemo.ui.view;

import com.scwen7.mvpdemo.data.bean.GankBean;

import java.util.List;

/**
 * Created by 解晓辉 on 2017/8/15.
 * 作用：
 */

public interface MvpView extends BaseView {

     void loadMoreSuccess(List<GankBean> transactions);

     void loadMoreFailed() ;


     void refreshFailed() ;


     void refreshSuccess(List<GankBean> transactions);
}
