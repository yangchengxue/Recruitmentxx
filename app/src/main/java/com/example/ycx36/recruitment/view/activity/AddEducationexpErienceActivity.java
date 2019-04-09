package com.example.ycx36.recruitment.view.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.db.usereducationexperience;
import com.example.ycx36.recruitment.model.db.userprojectexperience;
import com.example.ycx36.recruitment.model.db.users;
import com.example.ycx36.recruitment.okhttp.HttpUtil;

import org.feezu.liuli.timeselector.TimeSelector;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddEducationexpErienceActivity extends AppCompatActivity {

    @BindView(R.id.Text_workName)
    TextView Text_workName;
    @BindView(R.id.Text_JobTitle) TextView Text_JobTitle;
    @BindView(R.id.Text_workTime1) TextView Text_workTime1;
    @BindView(R.id.Text_workTime2) TextView Text_workTime2;
    @BindView(R.id.Text_WorkContent) TextView Text_WorkContent;
    @BindView(R.id.Text_WorkAchievements) TextView Text_WorkAchievements;

    int aplyCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_educationexp_erience);
        ButterKnife.bind(this);
        SharedPreferences pref = getSharedPreferences("ExperienceCount",MODE_PRIVATE);
        if (pref!=null)
            aplyCount = pref.getInt("educationCount",0);
    }

    @OnClick({R.id.tv_header_back,R.id.R1,R.id.R2,R.id.Text_workTime1,R.id.Text_workTime2,R.id.R3,R.id.R4,R.id.R5,R.id.bt_finish})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回键
                finish();
                break;
            case R.id.R1:
                intentToFill_WorkInformationActivity("学校名称","请输入您就读的学校名称","schoolName");
                break;
            case R.id.R2:
                intentToFill_WorkInformationActivity("专业","请您在该学校就读的专业，如果没有请填“无”","professional");
                break;
            case R.id.Text_workTime1:
                TimeSelector timeSelector = new TimeSelector(AddEducationexpErienceActivity.this, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        Text_workTime1.setText(time.substring(0,10));
                        saveInfo(0,"workTime1",time.substring(0,10));
                    }
                }, "1900-01-01 00:00", "2019-12-31 24:00");
                timeSelector.setMode(TimeSelector.MODE.YMD);//只显示 年月日
                timeSelector.show();
                break;
            case R.id.Text_workTime2:
                TimeSelector timeSelector2 = new TimeSelector(AddEducationexpErienceActivity.this, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        Text_workTime2.setText(time.substring(0,10));
                        saveInfo(0,"workTime2",time.substring(0,10));
                    }
                }, "1900-01-01 00:00", "2019-12-31 24:00");
                timeSelector2.setMode(TimeSelector.MODE.YMD);//只显示 年月日
                timeSelector2.show();
                break;
            case R.id.R3:
                break;
            case R.id.R4:
                intentToFill_WorkInformationActivity("荣誉奖项","请输入您所获得的荣誉以及奖项，如果没有请填写：“无”","educationExperience");
                break;
            case R.id.R5:
                intentToFill_WorkInformationActivity("最高学历","请输入您该段学习获得的最高学历，如：本科生。","highestDegree");
                break;
            case R.id.bt_finish: //点击完成
                if(!Text_workName.getText().toString().equals("未填写")&&!Text_JobTitle.getText().toString().equals("未填写")&&!Text_workTime1.getText().toString().equals("请选择")&&
                        !Text_workTime2.getText().toString().equals("请选择")&&!Text_WorkContent.getText().toString().equals("未填写")&&!Text_WorkAchievements.getText().toString().equals("未填写")){
                    SharedPreferences.Editor editor = getSharedPreferences("ExperienceCount",MODE_PRIVATE).edit();
                    editor.putInt("educationCount",aplyCount+1);
                    editor.apply();
                    saveInfo(1,"",null);
                    //创建下一条数据
                    AVUser currentUser = AVUser.getCurrentUser();
                    if (currentUser != null) {
                        List<users> users1 = DataSupport.where("U_Id = ?", currentUser.getObjectId()).find(users.class, true);
                        if (users1.size() != 0) {
                            if (users1.get(0).getU_Id().equals(currentUser.getObjectId())) {
                                if (users1.get(0).getUsereducationexperience() != null) {
                                    List<usereducationexperience> usereducationexperienceList = users1.get(0).getUsereducationexperience();
                                    usereducationexperience usereducationexperience = new usereducationexperience();
                                    usereducationexperience.setIsApply(0); //数据库的isApply列为0，表示未提交
                                    usereducationexperience.save();
                                    usereducationexperienceList.add(usereducationexperience);
                                    users u = users1.get(0);
                                    u.setUsereducationexperience(usereducationexperienceList);
                                    u.save();
                                }
                            }
                        }
                    }
                    sendData();
                    finish();
                }else{
                    Toast.makeText(AddEducationexpErienceActivity.this,"请填写信息后提交",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void intentToFill_WorkInformationActivity(String titleText,String hintText,String dbTableIndex){
        Intent intent = new Intent(AddEducationexpErienceActivity.this,Fill_WorkInformationActivity.class);
        Bundle bundle = new Bundle();      //创建一个budle对象
        bundle.putString("TitleText", titleText);  //写入数据
        bundle.putString("HintText", hintText);  //写入数据
        bundle.putString("dbWorkExperienceTableIndex", dbTableIndex);  //写入数据
        bundle.putInt("whichTable", 3);  //写入标记值
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            List<users> users1 = DataSupport.where("U_Id = ?", currentUser.getObjectId()).find(users.class, true);
            if (users1.size() != 0) {
                if (users1.get(0).getU_Id().equals(currentUser.getObjectId())) {
                    if (users1.get(0).getUsereducationexperience() != null) {
                        if (users1.get(0).getUsereducationexperience().get(aplyCount).getIsApply() == 0){  //没有提交的话，就显示出来
                            String getSchoolName = users1.get(0).getUsereducationexperience().get(aplyCount).getSchoolName();
                            String getProfessional = users1.get(0).getUsereducationexperience().get(aplyCount).getProfessional();
                            String ProjectTime1 = users1.get(0).getUsereducationexperience().get(aplyCount).getWorkTime1();
                            String ProjectTime2 = users1.get(0).getUsereducationexperience().get(aplyCount).getWorkTime2();
                            String getEducationExperience = users1.get(0).getUsereducationexperience().get(aplyCount).getEducationExperience();
                            String getHighestDegree = users1.get(0).getUsereducationexperience().get(aplyCount).getHighestDegree();
                            if (getSchoolName != null) {
                                Text_workName.setText(getSchoolName);
                            }
                            if (getProfessional != null) {
                                Text_JobTitle.setText(getProfessional);
                            }
                            if (ProjectTime1 != null) {
                                Text_workTime1.setText(ProjectTime1);
                            }
                            if (ProjectTime2 != null) {
                                Text_workTime2.setText(ProjectTime2);
                            }
                            if (getEducationExperience != null) {
                                Text_WorkContent.setText(getEducationExperience);
                            }
                            if (getHighestDegree != null) {
                                Text_WorkAchievements.setText(getHighestDegree);
                            }
                        }
                    }
                }
            }
        }
    }

    /**保存数据*/
    public void saveInfo(int flag,String dbWorkExperienceTableIndex,String textcontent) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            if(flag == 0){
                List<users> users = DataSupport.where("U_Id = ?", currentUser.getObjectId()).find(users.class);
                if (users.size() != 0) {
                    ContentValues values = new ContentValues();
                    values.put(dbWorkExperienceTableIndex, textcontent);
                    DataSupport.updateAll(usereducationexperience.class, values, "isApply = ?", String.valueOf(0));
                }
            }
            if(flag == 1){
                List<users> users = DataSupport.where("U_Id = ?", currentUser.getObjectId()).find(users.class);
                if (users.size() != 0) {
                    int u_id = users.get(0).getId();
                    //数据库的isApply列修改为1，表示已经提交
                    ContentValues values1 = new ContentValues();
                    values1.put("isApply", 1);
                    DataSupport.updateAll(usereducationexperience.class, values1, "users_id = ?", String.valueOf(u_id));
                }
            }
        } else {
            Toast.makeText(AddEducationexpErienceActivity.this, "您还未登录，请登录后再保存。", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 向服务器上传数据
     **/
    private void sendData() {

        Log.e("2333", getUserNameData());

        HttpUtil.sendEducationExperience("http://47.106.170.241:8080/gxnuPublicity/SetEducationExperience", getUserNameData(),
                Text_workName.getText().toString(), Text_JobTitle.getText().toString(), Text_workTime1.getText().toString() +
                        "至" + Text_workTime2.getText().toString(), Text_WorkContent.getText().toString(), Text_WorkAchievements.getText().toString(),
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        showSendResult(false);
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        showSendResult(true);
                    }
                });
    }

    /**
     * 上传结果反馈
     **/
    private void showSendResult(final Boolean result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                if (!result)
                    Toast.makeText(AddEducationexpErienceActivity.this, "保存失败，请检查网络", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(AddEducationexpErienceActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * SharedPreferences获取用户名数据
     */
    public String getUserNameData() {
        SharedPreferences pref = getSharedPreferences("s_username", MODE_PRIVATE);
        return pref.getString("username", "");  //后面的参数为如果找不到对应值，就返回什么样的默认值
    }
}
