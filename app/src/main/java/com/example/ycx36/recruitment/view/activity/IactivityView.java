package com.example.ycx36.recruitment.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ycx36.recruitment.adapter.Adapter_PhotoText;
import com.example.ycx36.recruitment.adapter.Adapter_SetUserInfo;
import com.nightonke.boommenu.BoomMenuButton;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

public interface IactivityView {
    void showRecyclerViewToPhotoShowActivity(RecyclerView recycler); //显示RecyclerView
    void showRecyclerViewToUserInfoActivity(RecyclerView recycler); //显示RecyclerView

    void setBoomMenuButton(BoomMenuButton bmb); //设置菜单按钮

    void showWaterDropFresh(CircleRefreshLayout refresh_layout); //水滴状下拉刷新

    void ChangeTitle(TextView textView ,String title);  //改变标题名称

    void replaceFragment(int Flag,FragmentManager fragmentManager,Fragment fragment);  //切换fragment

    void showFragment(int index,FragmentManager fragmentManager,Fragment fragment1,Fragment fragment2);//替换fragment的方法，且不会销毁被替换的fragment的现有实例

    void EditTextSet(EditText editText, TextView RemainText,int hint, final String Remain); //设置EditText

    void RegistrationUser(EditText et_userName,EditText et_userPassword,EditText et_userPhone);//注册用户


}
