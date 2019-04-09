package com.example.ycx36.recruitment.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.db.userinfo;
import com.example.ycx36.recruitment.model.db.users;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IactivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_userName) EditText et_userName;
    @BindView(R.id.et_userPassword) EditText et_userPassword;
    @BindView(R.id.et_userPhone) EditText et_userPhone;
    private IactivityView iactivityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        iactivityView = new ImplClass_IactivityView(this);
    }

    @OnClick({R.id.tv_header_back,R.id.bt_Register})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回
                finish();
                break;
            case R.id.bt_Register:
                iactivityView.RegistrationUser(et_userName,et_userPassword,et_userPhone);
                break;
        }
    }
}
