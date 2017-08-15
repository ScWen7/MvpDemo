package com.scwen7.mvpdemo.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.scwen7.mvpdemo.R;
import com.scwen7.mvpdemo.base.BaseMvpActivity;
import com.scwen7.mvpdemo.common.Contacts;
import com.scwen7.mvpdemo.common.fastjsonconverter.FastJsonConvertFactory;
import com.scwen7.mvpdemo.data.ApiService;
import com.scwen7.mvpdemo.data.LoadStatus;
import com.scwen7.mvpdemo.data.bean.GankBean;
import com.scwen7.mvpdemo.presenter.MvpPresenter;
import com.scwen7.mvpdemo.ui.view.MvpView;
import com.scwen7.mvpdemo.ui.adapter.GankAdapter;
import com.scwen7.mvpdemo.weight.loadmoreRecycler.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class MvpTestActivity extends BaseMvpActivity<MvpPresenter> implements MvpView {


    @BindView(R.id.recycler_mvp)
    XRecyclerView mRecyclerMvp;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    private List<GankBean> mGankBeanList = new ArrayList<>();
    private GankAdapter mGankAdapter;

    @Override
    protected void initView() {
        mRefresh.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_green_light, android.R.color.holo_red_light, android.R.color.holo_blue_light);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                mPresenter.getGankData(LoadStatus.REFRESH);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerMvp.setLayoutManager(linearLayoutManager);
        mRecyclerMvp.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mGankAdapter = new GankAdapter(this, mGankBeanList);
        mRecyclerMvp.setAdapter(mGankAdapter);


        mRecyclerMvp.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onLoadMore() {
                mPresenter.getGankData(LoadStatus.LOADMORE);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvp_test;
    }

    @Override
    protected void initData() {
        mRefresh.setRefreshing(true);
        mPresenter.getGankData(LoadStatus.REFRESH);

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Contacts.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConvertFactory.create())
                .client(mOkHttpClient).build();

        ApiService apiService = mRetrofit.create(ApiService.class);
        Call<ResponseBody> data = apiService.getData(1);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    Log.e("TAG", "String:" + string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void loadMoreSuccess(List<GankBean> transactions) {
        if (transactions != null && transactions.size() > 0) {
            mRecyclerMvp.loadMoreComplete();
            mGankAdapter.addData(mGankAdapter.getItemCount(), transactions);
        } else {
            mRecyclerMvp.setNoMore(true);
        }
    }

    @Override
    public void loadMoreFailed() {
        mRecyclerMvp.loadMoreComplete();
    }

    @Override
    public void refreshFailed() {
        mRefresh.setRefreshing(false);
    }

    @Override
    public void refreshSuccess(List<GankBean> transactions) {
        mRefresh.setRefreshing(false);
        mGankAdapter.setData(transactions);
    }
}
