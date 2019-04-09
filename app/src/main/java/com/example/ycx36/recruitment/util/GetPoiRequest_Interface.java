package com.example.ycx36.recruitment.util;

import com.example.ycx36.recruitment.model.dataBean.Gson_PoiDataBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GetPoiRequest_Interface {
    //Get请求  写的是网址底下的子目录
    @GET("/Near/Search")
    Observable<Gson_PoiDataBean> getPoiData(@Query("key") String key , @Query("keyWord")String keyWord, @Query("location")String location, @Query("cityName")String cityName , @Query("radius")String radius,@Query("number")String number,@Query("page")String page);
}
