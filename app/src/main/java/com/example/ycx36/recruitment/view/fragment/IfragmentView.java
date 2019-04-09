package com.example.ycx36.recruitment.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.example.ycx36.recruitment.adapter.Adapter_Message;
import com.example.ycx36.recruitment.adapter.Adapter_Position;
import com.example.ycx36.recruitment.adapter.SurroundindPlaceAdapter;
import com.example.ycx36.recruitment.adapter.SurroundingThingAdapter;
import com.example.ycx36.recruitment.model.dataBean.MessageDataBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public interface IfragmentView {

    //Recycler的显示
    void showRecyclerViewToSurroundingThingsTop(RecyclerView recycler1,RecyclerView recycler2,final AVLoadingIndicatorView avi,final AVLoadingIndicatorView avi2);
    void showRecyclerViewToPrivateMessage(RecyclerView recycler,Adapter_Message adapter);
    void showRecyclerViewToRecruitmentInformation(RecyclerView recycler,Adapter_Position adapter);
    void GetMyFollwersToPrivateMessage(RecyclerView recycler,ArrayList<MessageDataBean> itemFollwerslist);  //获取当前用户的关注列表


    void setBanner(BGABanner mContentBanner);  //设置轮播图

    //显示顶部导航栏
    void showTopNavigation(int Flag ,FragmentActivity fragmentActivity,FragmentPagerItemAdapter adapter, ViewPager DiscoverViewpager, SmartTabLayout DiscoverViewpagertab);

    //初始化UI--异步更新UI
    void initUI(IfragmentView ifragmentView,RecyclerView re1,RecyclerView re2,AVLoadingIndicatorView avi,final AVLoadingIndicatorView avi2);

    void showSmallMapPhoto(String path,SimpleDraweeView Dview);

    void ChangeMessageTitleStyle(int Flag, Context context, RelativeLayout relativeLayout1, TextView textView1, RelativeLayout relativeLayout2, TextView textView2, RelativeLayout relativeLayout3, TextView textView3);

}
