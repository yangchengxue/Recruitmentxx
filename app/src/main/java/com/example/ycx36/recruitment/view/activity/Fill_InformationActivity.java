package com.example.ycx36.recruitment.view.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.ycx36.recruitment.model.EventBean.MessageEvent;
import com.example.ycx36.recruitment.model.db.userinfo;
import com.example.ycx36.recruitment.model.db.users;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IactivityView;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fill_InformationActivity extends AppCompatActivity {

    @BindView(R.id.tv_header_centerText) TextView tv_header_centerText;
    @BindView(R.id.et_content) EditText et_content;
    @BindView(R.id.RemainText) TextView RemainText;
    private String dbTableIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill__information);
        ButterKnife.bind(this);
        IactivityView iactivityView = new ImplClass_IactivityView(this);
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        String name = bundle.getString("title");
        dbTableIndex = bundle.getString("dbTableIndex");
        int hintText = bundle.getInt("hintText");
        tv_header_centerText.setText(name);
        iactivityView.EditTextSet(et_content,RemainText, hintText,"50");
    }


    @OnClick({R.id.tv_header_back,R.id.tv_header_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回键
                finish();
                break;
            case R.id.tv_header_right:  //顶部栏右边的保存
                if (!et_content.getText().toString().equals("")) {
                    saveInfo();
                }else
                    Toast.makeText(Fill_InformationActivity.this, "请输入信息后再保存", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void saveInfo() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            List<users> users = DataSupport.where("U_Id = ?", currentUser.getObjectId()).find(users.class);
            if (users.size() != 0) {
                int u_id = users.get(0).getId();
                ContentValues values = new ContentValues();
                values.put(dbTableIndex, et_content.getText().toString());
                DataSupport.updateAll(userinfo.class, values, "users_id = ?", String.valueOf(u_id));
                finish();
            }
        } else {
            Toast.makeText(Fill_InformationActivity.this, "您还未登录，请登录后再保存。", Toast.LENGTH_SHORT).show();
            InputMethodManager m = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
