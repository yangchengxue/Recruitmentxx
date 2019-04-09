package com.example.ycx36.recruitment.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.adapter.Adapter_WorkExperience;
import com.example.ycx36.recruitment.adapter.Adapter_WorkExperience_Simple;
import com.example.ycx36.recruitment.model.dataBean.WorkExperience_Simple_Data;
import com.example.ycx36.recruitment.util.LearnCloudSubclass_User;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**编辑个人简历的界面*/
public class FillResmeActivity extends AppCompatActivity {

    @BindView(R.id.tv_name) TextView tv_name;
    @BindView(R.id.tv_sex) TextView tv_sex;
    @BindView(R.id.U_HeadPortraits) SimpleDraweeView U_HeadPortraits;
    @BindView(R.id.RV1) RecyclerView RV1;
    @BindView(R.id.RV2) RecyclerView RV2;
    @BindView(R.id.RV3) RecyclerView RV3;

    private AVUser currentUser = AVUser.getCurrentUser();
    private Adapter_WorkExperience_Simple adapter_workExperience_simple;
    private ArrayList<WorkExperience_Simple_Data> mData = new ArrayList<>();   //数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_resme);
        ButterKnife.bind(this);
        initUI();
        WorkExperience_Simple_Data x = new WorkExperience_Simple_Data("广西石油电气有限公司","2016.06","2017.06");
        mData.add(x);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL); //3为指定布局的列数，VERTICAL为排列方式
        RV1.setLayoutManager(layoutManager);  //将布局管理器设置到recyclerView中
        RV1.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        RV1.setNestedScrollingEnabled(false);  //recyclerView设置禁止嵌套滑动
        adapter_workExperience_simple = new Adapter_WorkExperience_Simple(mData); //获取适配器实例
        RV1.setAdapter(adapter_workExperience_simple);
    }

    public void initUI(){
        if (currentUser != null) {
            AVQuery<LearnCloudSubclass_User> query = AVObject.getQuery(LearnCloudSubclass_User.class);
            query.whereEqualTo("username", currentUser.getUsername());
            query.findInBackground(new FindCallback<LearnCloudSubclass_User>() {
                @Override
                public void done(List<LearnCloudSubclass_User> results, AVException e) {
                    if (results.size() != 0) {
                        for (final LearnCloudSubclass_User a : results) {
                            String userName = a.getuserName();
                            String userSex = a.getuserSex();
                            String userPhotoUri = a.getuserPhotoUri();
                            if (userPhotoUri != null) {
                                Uri uri = Uri.parse(userPhotoUri);
                                U_HeadPortraits.setImageURI(uri);
                            }
                            tv_name.setText(userName);
                            if (userSex != null) tv_sex.setText(userSex);
                            else tv_sex.setText("");

                        }
                    }
                }
            });
        }
    }

    @OnClick({R.id.tv_header_back,R.id.RL_ToUserInfoActivity,R.id.RL_addWorkExperience,R.id.RL_addProjectExperience,R.id.RL_addEducationExperience,R.id.bt_ToMyResumeActivity})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回键
                finish();
                break;
            case R.id.RL_ToUserInfoActivity:
                startActivity(new Intent(this,UserInfoActivity.class));
                break;
            case R.id.RL_addWorkExperience:  //点击添加工作经历
                startActivity(new Intent(this,AddWorkExperienceActivity.class));
                break;
            case R.id.RL_addProjectExperience:
                startActivity(new Intent(this,AddProjectExperienceActivity.class));
                break;
            case R.id.RL_addEducationExperience:
                startActivity(new Intent(this,AddEducationexpErienceActivity.class));
                break;
            case R.id.bt_ToMyResumeActivity:
                startActivity(new Intent(this,MyResmeActivity.class));
                break;
        }
    }
}
