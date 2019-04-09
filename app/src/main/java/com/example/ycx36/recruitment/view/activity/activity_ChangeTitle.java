package com.example.ycx36.recruitment.view.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ycx36.recruitment.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.photodraweeview.PhotoDraweeView;

public class activity_ChangeTitle extends AppCompatActivity {

    @BindView(R.id.photo_drawee_view) PhotoDraweeView mPhotoDraweeView;
    @BindView(R.id.tv_header_centerText) TextView tv_header_centerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__change_title);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        String newsUri = bundle.getString("newsUri");
        String newsTitle = bundle.getString("newsTitle");
        tv_header_centerText.setText(newsTitle);
        mPhotoDraweeView.setPhotoUri(Uri.parse(newsUri));
    }

    @OnClick({R.id.lin_header_back2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_header_back2:
                finish();
                break;
        }
    }

}
