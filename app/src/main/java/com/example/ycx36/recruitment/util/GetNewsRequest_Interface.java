package com.example.ycx36.recruitment.util;

import com.example.ycx36.recruitment.model.dataBean.Gson_BDPhotosBean;
import com.example.ycx36.recruitment.model.dataBean.Gson_NewsDataBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GetNewsRequest_Interface {

    //http://rsc.fiysj.cn/api/news?tdsourcetag=s_pcqq_aiomsg
    //Get请求  写的是网址底下的子目录
    @GET("/api/ann")
    Observable<Gson_NewsDataBean> getNewsData();
}
