package com.example.ycx36.recruitment.util;

import com.example.ycx36.recruitment.model.dataBean.Gson_BDPhotosBean;
import com.example.ycx36.recruitment.model.dataBean.Gson_PoiDataBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GetBDPhotosRequest_Interface {

    //Get请求  写的是网址底下的子目录
    @GET("/channel/listjson")
    //http://image.baidu.com/channel/listjson?pn=0&rn=50&tag1=壁纸&tag2=全部&ie=utf8
    Observable<Gson_BDPhotosBean> getBDPhotosData(@Query("pn") String pn , @Query("rn")String rn, @Query("tag1")String tag1, @Query("tag2")String tag2 , @Query("ie")String ie);
}
