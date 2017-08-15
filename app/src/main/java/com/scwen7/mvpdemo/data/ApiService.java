package com.scwen7.mvpdemo.data;


import com.scwen7.mvpdemo.data.bean.GankBean;
import com.scwen7.mvpdemo.data.bean.GankRestlt;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by 解晓辉 on 2017/6/26.
 * 作用：
 */

public interface ApiService {


    @GET("{page}")
    Observable<GankRestlt<List<GankBean>>> getGankData(@Path("page") int page);

    @GET("{page}")
    Call<ResponseBody> getData(@Path("page")int page);


    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);


    @GET
    @Streaming
    Flowable<Response<ResponseBody>> download(@Url String url);

    @GET
    @Streaming
    Flowable<ResponseBody> download2(@Url String url);


}
