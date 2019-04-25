package com.example.ycx36.recruitment.view.sonFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.adapter.Adapter_PhotoText;
import com.example.ycx36.recruitment.adapter.SurroundindPlaceAdapter;
import com.example.ycx36.recruitment.adapter.SurroundingThingAdapter;
import com.example.ycx36.recruitment.model.dataBean.Gson_PoiDataBean;
import com.example.ycx36.recruitment.model.dataBean.SurroundingPlaceData;
import com.example.ycx36.recruitment.presenter.ActivityPresenter;
import com.example.ycx36.recruitment.util.GetPoiRequest_Interface;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IactivityView;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IfragmentView;
import com.example.ycx36.recruitment.view.activity.IactivityView;
import com.example.ycx36.recruitment.view.activity.PhotosShowActivity;
import com.example.ycx36.recruitment.view.fragment.IfragmentView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nightonke.boommenu.BoomMenuButton;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**发现 模块的 周边 模块*/
public class SurroundingThings extends Fragment{

    @BindView(R.id.SurrRecyclerview) public RecyclerView SurrRecyclerview;
    @BindView(R.id.SurrRecyclerview2) public RecyclerView SurrRecyclerview2;
    @BindView(R.id.discoverRefresh) CircleRefreshLayout discoverRefresh;
    @BindView(R.id.avi) AVLoadingIndicatorView avi;
    @BindView(R.id.avi2) AVLoadingIndicatorView avi2;

    IactivityView iactivityView;
    IfragmentView ifragmentView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.surroundingthings, container, false);
        ButterKnife.bind(this ,view) ;
        avi2.bringToFront();
        ifragmentView = new ImplClass_IfragmentView(view.getContext());
        iactivityView = new ImplClass_IactivityView(view.getContext());
        iactivityView.showWaterDropFresh(discoverRefresh);
        ifragmentView.initUI(ifragmentView,SurrRecyclerview,SurrRecyclerview2,avi,avi2);
        return view;
    }


    @OnClick({R.id.t2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.t2:   //给 查看更多 一个点击事件
                Intent intent = new Intent(getActivity(), PhotosShowActivity.class);
                startActivity(intent);
                break;
        }
    }

}
