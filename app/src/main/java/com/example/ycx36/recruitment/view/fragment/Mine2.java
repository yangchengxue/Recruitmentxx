package com.example.ycx36.recruitment.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.util.LearnCloudSubclass_User;
import com.example.ycx36.recruitment.view.activity.ContactUsActivity;
import com.example.ycx36.recruitment.view.activity.EditAccountInfoActivity;
import com.example.ycx36.recruitment.view.activity.FeedbackActivity;
import com.example.ycx36.recruitment.view.activity.FillResmeActivity;
import com.example.ycx36.recruitment.view.activity.LoginActivity;
import com.example.ycx36.recruitment.view.activity.LoginActivity2;
import com.example.ycx36.recruitment.view.activity.MapActivity;
import com.example.ycx36.recruitment.view.activity.MyDetailInfoActivity;
import com.example.ycx36.recruitment.view.activity.SchoolMapActivity;
import com.example.ycx36.recruitment.view.activity.activity_JobStrategy;
import com.example.ycx36.recruitment.view.activity.activity_aboutApp;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Mine2 extends Fragment{

    @BindView(R.id.image_userPhoto) SimpleDraweeView image_userPhoto;
    @BindView(R.id.tv_userName) TextView tv_userName;

    private View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.mine2_layout, container, false);
            ButterKnife.bind(this ,view) ;
        }
        return view;
    }

    public void setUserInfo(){
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            AVQuery<LearnCloudSubclass_User> query = AVObject.getQuery(LearnCloudSubclass_User.class);
            query.whereEqualTo("username",currentUser.getUsername());
            query.findInBackground(new FindCallback<LearnCloudSubclass_User>() {
                @Override
                public void done(List<LearnCloudSubclass_User> results, AVException e) {
                    if (results.size() != 0) {
                        for (final LearnCloudSubclass_User a : results) {
                            if (a.getuserPhotoUri() != null){
                                String userPhotoUri = a.getuserPhotoUri();
                                Uri uri = Uri.parse(userPhotoUri);
                                image_userPhoto.setImageURI(uri);
                            }
                        }
                    }
                }
            });
            String userName = currentUser.getUsername();
            tv_userName.setText(userName);
        }else {
            image_userPhoto.setImageURI("");
            tv_userName.setText("点击登陆/注册");
        }
    }

    @OnClick({R.id.LL_User,R.id.LL_myinfo,R.id.LL_myresume,R.id.LL_strategy,R.id.LL1,R.id.LL2,R.id.LL3,R.id.LL4,R.id.LL5,R.id.LL6})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.LL_User:
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null){
                    startActivity(new Intent(getActivity(),MyDetailInfoActivity.class));
                }else
                    startActivity(new Intent(getActivity(), LoginActivity2.class));
                break;
            case R.id.LL_myinfo:
                startActivity(new Intent(getActivity(),EditAccountInfoActivity.class));
                break;
            case R.id.LL_myresume:
                startActivity(new Intent(getActivity(),FillResmeActivity.class));
                break;
            case R.id.LL_strategy:
                //跳转到求职攻略
                startActivity(new Intent(getActivity(),activity_JobStrategy.class));
                break;
            case R.id.LL1:
                startActivity(new Intent(getActivity(),SchoolMapActivity.class));
                break;
            case R.id.LL2:
                //跳转到地点导航
                startActivity(new Intent(getActivity(),MapActivity.class));
                break;
            case R.id.LL3:
                //跳转到关于app
                startActivity(new Intent(getActivity(),activity_aboutApp.class));
                break;
            case R.id.LL4:
                startActivity(new Intent(getActivity(),FeedbackActivity.class));
                break;
            case R.id.LL5:
                startActivity(new Intent(getActivity(),ContactUsActivity.class));
                break;
            case R.id.LL6:
                //退出程序
                break;
        }
    }

    public void onResume() {
        super.onResume();
        setUserInfo();
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
