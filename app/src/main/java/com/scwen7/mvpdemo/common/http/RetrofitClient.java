package com.scwen7.mvpdemo.common.http;

import android.content.Context;

import com.scwen7.mvpdemo.common.Contacts;
import com.scwen7.mvpdemo.common.cookie.CookieJarImpl;
import com.scwen7.mvpdemo.common.cookie.store.PersistentCookieStore;
import com.scwen7.mvpdemo.common.fastjsonconverter.FastJsonConvertFactory;
import com.scwen7.mvpdemo.data.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by 解晓辉  on 2017/5/24 20:49 *
 * QQ  ：811733738
 * 作用:   提供 Retrofit 网络连接 ApiService
 *
 */

public class RetrofitClient {

    private static RetrofitClient instance;

    private OkHttpClient mOkHttpClient;


    private Context mContext;

    private Retrofit mRetrofit;

    private ApiService mApiService;
    private CookieJarImpl cookieJar;

    private RetrofitClient(Context context) {
        this.mContext = context;
        cookieJar = new CookieJarImpl(new PersistentCookieStore(mContext));
    }

    public static RetrofitClient init(Context context) {
        if (instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }

    public static RetrofitClient getInstance() {
        return instance;
    }

    private OkHttpClient provideOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
//                    .addInterceptor(new HttpParamInterceptor(mContext))
//                    .cookieJar(cookieJar)
                    .build();
        }


        return mOkHttpClient;
    }

    /**
     * 获取全局的cookie实例
     */
    public CookieJarImpl getCookieJar() {
        return cookieJar;
    }


    private Retrofit provideRetrofit() {

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Contacts.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(FastJsonConvertFactory.create())
                    .client(provideOkHttpClient()).build();
        }

        return mRetrofit;
    }

    public ApiService provideApiService() {
        if (mApiService == null) {

            mApiService = provideRetrofit().create(ApiService.class);
        }
        return mApiService;
    }


}
