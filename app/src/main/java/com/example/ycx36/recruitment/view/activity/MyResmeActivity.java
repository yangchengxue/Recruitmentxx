package com.example.ycx36.recruitment.view.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.adapter.Adapter_EducationExperience;
import com.example.ycx36.recruitment.adapter.Adapter_ProjectExperience;
import com.example.ycx36.recruitment.adapter.Adapter_SetUserInfo;
import com.example.ycx36.recruitment.adapter.Adapter_WorkExperience;
import com.example.ycx36.recruitment.model.dataBean.EducationExperienceData;
import com.example.ycx36.recruitment.model.dataBean.ProjectExperienceData;
import com.example.ycx36.recruitment.model.dataBean.WorkExperienceData;
import com.example.ycx36.recruitment.model.db.users;
import com.example.ycx36.recruitment.model.db.userworkexperience;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyResmeActivity extends AppCompatActivity {

    @BindViews({R.id.tv_name,R.id.tv_sex,R.id.tv_age,R.id.tv_nativeplace,R.id.tv_school,R.id.tv_major}) public List<TextView> TextViewList;
    @BindView(R.id.RecyclerView_workExperience) RecyclerView RecyclerView_workExperience;
    @BindView(R.id.RecyclerView_ProjectExperience) RecyclerView RecyclerView_ProjectExperience;
    @BindView(R.id.RecyclerView_EducationExperience) RecyclerView RecyclerView_EducationExperience;
    @BindView(R.id.U_HeadPortraits) SimpleDraweeView U_HeadPortraits;

    Adapter_WorkExperience adapter;
    Adapter_ProjectExperience adapter_projectExperience;
    Adapter_EducationExperience adapter_educationExperience;
    ArrayList<WorkExperienceData> mData = new ArrayList<>();
    private String[] mVals = new String[]{"Java", "C++", "C", "PHP"};

    private ArrayList<ProjectExperienceData> mData2 = new ArrayList<>();   //数据
    private ArrayList<EducationExperienceData> mData3 = new ArrayList<>();   //数据
    String workLocation,jobTitle,time1,time2, workContent,achievementsInWork;
    String getProjectName,getProjectRole,getProjectTime1,getProjectTime2, getProjectIntroduction,getAchievementsInProject;
    String getSchoolName,getProfessional,getEducationTime1,getEducationTime2, getEducationExperience,getHighestDegree;

    int aplyCount_work = 0;
    int aplyCount_project = 0;
    int aplyCount_education = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resme);
        ButterKnife.bind(this);

        SharedPreferences pref = getSharedPreferences("ExperienceCount",MODE_PRIVATE);
        if (pref!=null) {
            aplyCount_work = pref.getInt("workCount", 0);
            aplyCount_project = pref.getInt("projectCount", 0);
            aplyCount_education = pref.getInt("educationCount", 0);
        }

        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
                List<users> users1 = DataSupport.where("U_Id = ?", currentUser.getObjectId()).find(users.class, true);
            if (users1.size() != 0) {
                if (users1.get(0).getU_Id().equals(currentUser.getObjectId())) {
                    if (users1.get(0).getUserinfo().getUserName() != null){
                        TextViewList.get(0).setText(users1.get(0).getUserinfo().getUserName());
                    }
                    if (users1.get(0).getUserinfo().getUserSex() != null){
                        TextViewList.get(1).setText(users1.get(0).getUserinfo().getUserSex());
                    }
                    if (users1.get(0).getUserinfo().getUserBirthday() != null){
                        TextViewList.get(2).setText(users1.get(0).getUserinfo().getUserBirthday());
                    }
                    if (users1.get(0).getUserinfo().getUserPlaceOfBirth() != null){
                        TextViewList.get(3).setText(users1.get(0).getUserinfo().getUserPlaceOfBirth());
                    }
                    if (users1.get(0).getUserinfo().getUserJobCandidates() != null){
                        TextViewList.get(4).setText(users1.get(0).getUserinfo().getUserJobCandidates());
                    }
                    if (users1.get(0).getUserinfo().getUserHighestEducation() != null){
                        TextViewList.get(5).setText(users1.get(0).getUserinfo().getUserHighestEducation());
                    }
                    if (users1.get(0).getUserworkexperience() != null) {
                        for (int i = 0 ; i<aplyCount_work ; i++){
                            if (users1.get(0).getUserworkexperience().get(i).getIsApply() == 1){
                                workLocation = users1.get(0).getUserworkexperience().get(i).getWorkLocation();
                                jobTitle = users1.get(0).getUserworkexperience().get(i).getJobTitle();
                                time1 = users1.get(0).getUserworkexperience().get(i).getWorkTime1();
                                time2 = users1.get(0).getUserworkexperience().get(i).getWorkTime2();
                                workContent = users1.get(0).getUserworkexperience().get(i).getWorkContent();
                                achievementsInWork = users1.get(0).getUserworkexperience().get(i).getAchievementsInWork();
                                mData.add(new WorkExperienceData(workLocation,jobTitle,time1,time2,
                                        "工作内容："+workContent,"工作业绩："+achievementsInWork));
                            }
                        }
                    }
                    if (users1.get(0).getUserprojectexperience() != null) {
                        for (int i = 0 ; i<aplyCount_project ; i++){
                            if (users1.get(0).getUserprojectexperience().get(i).getIsApply() == 1){
                                getProjectName = users1.get(0).getUserprojectexperience().get(i).getProjectName();
                                getProjectRole = users1.get(0).getUserprojectexperience().get(i).getProjectRole();
                                getProjectTime1 = users1.get(0).getUserprojectexperience().get(i).getProjectTime1();
                                getProjectTime2 = users1.get(0).getUserprojectexperience().get(i).getProjectTime2();
                                getProjectIntroduction = users1.get(0).getUserprojectexperience().get(i).getProjectIntroduction();
                                getAchievementsInProject = users1.get(0).getUserprojectexperience().get(i).getAchievementsInProject();
                                mData2.add(new ProjectExperienceData(getProjectName,getProjectRole,getProjectTime1,getProjectTime2,
                                        "项目简介："+getProjectIntroduction,"项目业绩："+getAchievementsInProject));
                            }
                        }
                    }
                    if (users1.get(0).getUsereducationexperience() != null) {
                        for (int i = 0 ; i<aplyCount_education ; i++){
                            if (users1.get(0).getUsereducationexperience().get(i).getIsApply() == 1){
                                getSchoolName = users1.get(0).getUsereducationexperience().get(i).getSchoolName();
                                getProfessional = users1.get(0).getUsereducationexperience().get(i).getProfessional();
                                getEducationTime1 = users1.get(0).getUsereducationexperience().get(i).getWorkTime1();
                                getEducationTime2 = users1.get(0).getUsereducationexperience().get(i).getWorkTime2();
                                getEducationExperience = users1.get(0).getUsereducationexperience().get(i).getEducationExperience();
                                getHighestDegree = users1.get(0).getUsereducationexperience().get(i).getHighestDegree();
                                mData3.add(new EducationExperienceData(getSchoolName,getProfessional,getHighestDegree,getEducationTime1,getEducationTime2,
                                        "荣誉奖项："+getEducationExperience));
                            }
                        }
                    }
                }
            }
        }

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL); //3为指定布局的列数，VERTICAL为排列方式
        RecyclerView_workExperience.setLayoutManager(layoutManager);  //将布局管理器设置到recyclerView中
        RecyclerView_workExperience.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        RecyclerView_workExperience.setNestedScrollingEnabled(false);  //recyclerView设置禁止嵌套滑动
        adapter = new Adapter_WorkExperience(this,mData,mVals); //获取适配器实例
        RecyclerView_workExperience.setAdapter(adapter);

        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL); //3为指定布局的列数，VERTICAL为排列方式
        RecyclerView_ProjectExperience.setLayoutManager(layoutManager2);  //将布局管理器设置到recyclerView中
        RecyclerView_ProjectExperience.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        RecyclerView_ProjectExperience.setNestedScrollingEnabled(false);  //recyclerView设置禁止嵌套滑动
        adapter_projectExperience = new Adapter_ProjectExperience(mData2); //获取适配器实例
        RecyclerView_ProjectExperience.setAdapter(adapter_projectExperience);

//        EducationExperienceData x4 = new EducationExperienceData("广西师范大学","电子科学与技术","研究生","2017.03","2020.06",
//                "学习经历："+"本系统主要是利用JNI调用OpenCV以及采用Android NDK生成共享库的目标检测方法来实现Android 平台下多目标的人脸检测和识别。为提高" +
//                        "人脸检测速度，该项目拟采用基于Haar特征的Adaboost级联人脸检测分类器。系统在进行人脸的检测时，用户可选择多种方式获取人脸信息，系统将抓取出来的人脸图片在" +
//                        "终端进行处理之后上传到云端，在云端进行信息识别对比操作，并返回是否匹配的相关信息。有效地减少了智能终端与云服务器之间数据传输过大从而引起的时延效应。");
//        mData3.add(x4);
        StaggeredGridLayoutManager layoutManager3 = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL); //3为指定布局的列数，VERTICAL为排列方式
        RecyclerView_EducationExperience.setLayoutManager(layoutManager3);  //将布局管理器设置到recyclerView中
        RecyclerView_EducationExperience.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        RecyclerView_EducationExperience.setNestedScrollingEnabled(false);  //recyclerView设置禁止嵌套滑动
        adapter_educationExperience = new Adapter_EducationExperience(mData3); //获取适配器实例
        RecyclerView_EducationExperience.setAdapter(adapter_educationExperience);
    }

    @OnClick({R.id.tv_header_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_header_back:   //返回键
                finish();
                break;
        }
    }
}
