package com.scwen7.mvpdemo.common.http;


import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by 解晓辉  on 2017/6/10 09:48 *
 * QQ  ：811733738
 * 作用:
 */

public class HttpParamInterceptor implements Interceptor {


    private Context mContext;



    public HttpParamInterceptor(Context context) {
        this.mContext = context;
        init(context);

    }

    /**
     * 初始化 网络地址工具类
     *
     * @param context
     */
    public void init(Context context) {


    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        //执行特定的逻辑

        return chain.proceed(request);
    }

}
