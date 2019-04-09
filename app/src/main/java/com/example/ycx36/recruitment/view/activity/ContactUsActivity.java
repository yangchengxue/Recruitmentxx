package com.example.ycx36.recruitment.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ycx36.recruitment.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lin_header_back2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.lin_header_back2:   //返回键
                finish();
                break;
        }
    }
}
