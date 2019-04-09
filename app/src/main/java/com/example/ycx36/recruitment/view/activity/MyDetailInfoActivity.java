package com.example.ycx36.recruitment.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.util.LearnCloudSubclass_User;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDetailInfoActivity extends AppCompatActivity {

    @BindView(R.id.AuserDraw) SimpleDraweeView AuserDraw;
    @BindView(R.id.tv_name) TextView tv_name;
    @BindView(R.id.tv_userPhone) TextView tv_userPhone;
    @BindView(R.id.tv_userEmail) TextView tv_userEmail;
    @BindView(R.id.tv_userSex) TextView tv_userSex;
    @BindView(R.id.tv_userAddress1) TextView tv_userAddress1;
    @BindView(R.id.tv_userAddress2) TextView tv_userAddress2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail_info);
        ButterKnife.bind(this);
    }


    public void showMyInfo(){
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null){
            AVQuery<LearnCloudSubclass_User> query = AVObject.getQuery(LearnCloudSubclass_User.class);
            query.whereEqualTo("username",currentUser.getUsername());
            query.findInBackground(new FindCallback<LearnCloudSubclass_User>() {
                @Override
                public void done(List<LearnCloudSubclass_User> results, AVException e) {
                    if (results.size() != 0) {
                        for (final LearnCloudSubclass_User a : results) {
                            String userPhotoUri = a.getuserPhotoUri();
                            String Sex = a.getuserSex();
                            String Province = a.getuserProvince();
                            String City = a.getuserCity();
                            String Email = a.getEmail();
                            tv_name.setText(a.getUsername());
                            tv_userPhone.setText(a.getMobilePhoneNumber());
                            if (userPhotoUri != null) {
                                Uri uri = Uri.parse(userPhotoUri);
                                AuserDraw.setImageURI(uri);
                            }
                            if (Sex != null) tv_userSex.setText(Sex);
                            else tv_userSex.setText("未填写");
                            if (Province != null) tv_userAddress1.setText(Province);
                            else tv_userAddress1.setText("未填写");
                            if (City != null) tv_userAddress2.setText(City);
                            else tv_userAddress2.setText("未填写");
                            if (Email != null) tv_userEmail.setText(Email);
                            else tv_userEmail.setText("未填写");
                        }
                    }
                }
            });
        }
    }

    @OnClick({R.id.tv_header_back,R.id.bt_changeInfo,R.id.bt_Logout})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:
                finish();
                break;
            case R.id.bt_changeInfo:
                startActivity(new Intent(MyDetailInfoActivity.this,EditAccountInfoActivity.class));
                break;
            case R.id.bt_Logout:
                AVUser.logOut();// 清除缓存用户对象
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser == null) {
                    Toast.makeText(MyDetailInfoActivity.this, "您已成功登出", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    public void onResume() {
        super.onResume();
        showMyInfo();
    }

}
