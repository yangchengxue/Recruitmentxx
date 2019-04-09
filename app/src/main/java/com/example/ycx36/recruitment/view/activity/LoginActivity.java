package com.example.ycx36.recruitment.view.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.db.usereducationexperience;
import com.example.ycx36.recruitment.model.db.userinfo;
import com.example.ycx36.recruitment.model.db.userprojectexperience;
import com.example.ycx36.recruitment.model.db.users;
import com.example.ycx36.recruitment.model.db.userworkexperience;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.at_userName) AutoCompleteTextView at_userName;
    @BindView(R.id.at_userPassword) EditText at_userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_sign,R.id.register,R.id.changePassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sign:
                login();
                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.changePassword:

                break;
        }
    }

    public void login() {
        if ("".equals(at_userName.getText().toString()) && "".equals(at_userPassword.getText().toString())){
            Toast.makeText(LoginActivity.this, "请输入登录信息", Toast.LENGTH_SHORT).show();
        }else {
            AVUser.logInInBackground(at_userName.getText().toString(), at_userPassword.getText().toString(), new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser user, AVException e) {
                    if (null != e) {
                        switch (e.getCode()){
                            case 210:
                                Toast.makeText(LoginActivity.this, "登录失败,找不到该用户", Toast.LENGTH_SHORT).show();
                                break;
                            case 211:
                                Toast.makeText(LoginActivity.this, "登录失败,用户名和密码不匹配", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return;
                    }

                    // 与服务器连接
                    AVIMClient client = AVIMClient.getInstance(user);
                    client.open(new AVIMClientCallback() {
                        @Override
                        public void done(final AVIMClient avimClient, AVIMException e) {
                            if (e == null) {
                                Toast.makeText(LoginActivity.this, "登录成功  " + "欢迎您,  "+at_userName.getText().toString(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else Toast.makeText(LoginActivity.this, "登录失败，错误码为： " + e.getCode(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    /**每次登录，都使用一次SharedPreferences保存空数据，避免与上次登录的用户的数据混淆*/
                    SharedPreferences.Editor editor1 = getSharedPreferences("s_username",MODE_PRIVATE).edit();
                    editor1.putString("username",at_userName.getText().toString());
                    editor1.putString("userphone","");
                    editor1.putString("useremail","");
                    editor1.apply();
                    SharedPreferences.Editor editor2 = getSharedPreferences("s_sex",MODE_PRIVATE).edit();
                    editor2.putString("sex","");
                    editor2.apply();

                    /**登录成功，检查users表是否存在数据，如果不存在，则添加，避免空数据异常*/
                    List<users> users = DataSupport.where("U_Id = ?", user.getObjectId()).find(users.class);
                    if (users.size() == 0) {
                        userinfo userinfo = new userinfo();
                        userinfo.save();
                        userworkexperience userworkexperience = new userworkexperience();
                        userworkexperience.save();
                        List<userworkexperience> userworkexperienceList = new ArrayList<>();
                        userworkexperienceList.add(userworkexperience);
                        userprojectexperience userprojectexperience = new userprojectexperience();
                        userprojectexperience.save();
                        List<userprojectexperience> userprojectexperienceList = new ArrayList<>();
                        userprojectexperienceList.add(userprojectexperience);
                        usereducationexperience usereducationexperience = new usereducationexperience();
                        usereducationexperience.save();
                        List<usereducationexperience> usereducationexperienceList = new ArrayList<>();
                        usereducationexperienceList.add(usereducationexperience);
                        users u = new users();
                        u.setUserinfo(userinfo);
                        u.setUserworkexperience(userworkexperienceList);
                        u.setUserprojectexperience(userprojectexperienceList);
                        u.setUsereducationexperience(usereducationexperienceList);
                        u.setU_Id(user.getObjectId());
                        u.save();  //将用户id以及关联数据表存入本地数据库
                    }
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        at_userName.setText("");
        at_userPassword.setText("");
    }

}
