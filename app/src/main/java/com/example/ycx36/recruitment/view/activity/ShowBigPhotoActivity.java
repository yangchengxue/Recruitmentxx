package com.example.ycx36.recruitment.view.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ycx36.recruitment.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.photodraweeview.PhotoDraweeView;

public class ShowBigPhotoActivity extends AppCompatActivity {

    @BindView(R.id.photo_drawee_view) PhotoDraweeView photo_drawee_view;
    @BindView(R.id.ShowBigPhotoActivityTitle) RelativeLayout ShowBigPhotoActivityTitle;
    @BindView(R.id.tv_header_centerText) TextView tv_header_centerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_show_big_photo);
        ButterKnife.bind(this);
        ShowBigPhotoActivityTitle.bringToFront();
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        String path = bundle.getString("path");   //根据键名读取数据
        String PhototTitle = bundle.getString("PhototTitle");   //根据键名读取数据
        tv_header_centerText.setText(PhototTitle);
        if (path != null && PhototTitle != null) photo_drawee_view.setPhotoUri(Uri.parse(path));

    }
}
