package com.example.ycx36.recruitment.view.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.db.usereducationexperience;
import com.example.ycx36.recruitment.model.db.userinfo;
import com.example.ycx36.recruitment.model.db.userprojectexperience;
import com.example.ycx36.recruitment.model.db.users;
import com.example.ycx36.recruitment.model.db.userworkexperience;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fill_WorkInformationActivity extends AppCompatActivity {

    @BindView(R.id.et_content) EditText et_content;
    @BindView(R.id.RemainText) TextView RemainText;
    @BindView(R.id.tv_header_centerText) TextView tv_header_centerText;
    private String dbWorkExperienceTableIndex;
    private int whichTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill__work_information);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        String titleText = bundle.getString("TitleText");
        String hintText = bundle.getString("HintText");
        dbWorkExperienceTableIndex = bundle.getString("dbWorkExperienceTableIndex");
        whichTable = bundle.getInt("whichTable");
        tv_header_centerText.setText(titleText);
        et_content.setHint(hintText);
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                RemainText.setText(s.length()+"/"+ 30);
            }
        });
    }

    @OnClick({R.id.tv_header_back,R.id.tv_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回键
                finish();
                break;
            case R.id.tv_save:   //保存
                saveInfo(whichTable);
                break;
        }
    }

    public void saveInfo(int flag_whichTable) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            List<users> users = DataSupport.where("U_Id = ?", currentUser.getObjectId()).find(users.class);
            if (users.size() != 0) {
                if (flag_whichTable==1) {
                    ContentValues values = new ContentValues();
                    values.put(dbWorkExperienceTableIndex, et_content.getText().toString());
                    DataSupport.updateAll(userworkexperience.class, values, "isApply = ?", String.valueOf(0));
                    finish();
                }
                if (flag_whichTable==2) {
                    ContentValues values = new ContentValues();
                    values.put(dbWorkExperienceTableIndex, et_content.getText().toString());
                    DataSupport.updateAll(userprojectexperience.class, values, "isApply = ?", String.valueOf(0));
                    finish();
                }
                if (flag_whichTable==3) {
                    ContentValues values = new ContentValues();
                    values.put(dbWorkExperienceTableIndex, et_content.getText().toString());
                    DataSupport.updateAll(usereducationexperience.class, values, "isApply = ?", String.valueOf(0));
                    finish();
                }
            }
        } else {
            Toast.makeText(Fill_WorkInformationActivity.this, "您还未登录，请登录后再保存。", Toast.LENGTH_SHORT).show();
        }
    }
}
