package com.example.ycx36.recruitment.view.sonFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IfragmentView;
import com.example.ycx36.recruitment.view.activity.ShowBigPhotoActivity;
import com.example.ycx36.recruitment.view.fragment.IfragmentView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YanShanMap extends Fragment {
    @BindView(R.id.yanshanimage_view) SimpleDraweeView yanshanimage_view;

    private IfragmentView ifragmentView;
    private View view;
    String path = "http://lc-apsilusi.cn-n1.lcfile.com/a7434f6f5c6ebefc95a5.jpg";
    String PhototTitle = "雁山校区地图";
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.yanshanmap, container, false);
            ButterKnife.bind(this, view);
            ifragmentView = new ImplClass_IfragmentView(view.getContext());
            ifragmentView.showSmallMapPhoto(path,yanshanimage_view);
        }
        return view;
    }

    @OnClick({R.id.yanshanimage_view})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.yanshanimage_view:   //返回键
                Intent intent = new Intent(getActivity(), ShowBigPhotoActivity.class);
                Bundle bundle = new Bundle();      //创建一个budle对象
                bundle.putString("path", path);  //写入数据
                bundle.putString("PhototTitle", PhototTitle);  //写入数据
                intent.putExtras(bundle);             //用putExtras方法将写入的数据存入intent中
                startActivity(intent);
                break;
        }
    }
}
