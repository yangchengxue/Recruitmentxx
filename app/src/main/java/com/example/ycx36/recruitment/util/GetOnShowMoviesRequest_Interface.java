package com.example.ycx36.recruitment.util;

import com.example.ycx36.recruitment.model.dataBean.Gson_BDPhotosBean;
import com.example.ycx36.recruitment.model.dataBean.Gson_OnShowMoviesData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GetOnShowMoviesRequest_Interface {
    //Get请求  写的是网址底下的子目录
    @GET("/v2/movie/in_theaters")
    Observable<Gson_OnShowMoviesData> getOnShowMoviesData();
}
