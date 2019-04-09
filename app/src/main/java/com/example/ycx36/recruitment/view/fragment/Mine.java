package com.example.ycx36.recruitment.view.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.view.activity.MainActivity;
import com.example.ycx36.recruitment.view.activity.ShowBigPhotoActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**我的碎片*/
public class Mine extends Fragment {

    @BindView(R.id.image_userPhoto) SimpleDraweeView image_userPhoto;
    @BindView(R.id.tv_userName) TextView tv_userName;
    @BindView(R.id.tv_phoneNumber) TextView tv_phoneNumber;
    private Uri imageUri;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, container, false);
        ButterKnife.bind(this ,view) ;
        setUserInfo();
        return view;
    }

    public void setUserInfo(){
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            String userName = currentUser.getUsername();
            String userPhotoUri = (String) currentUser.get("userPhotoUri");
            if (userPhotoUri != null) {
                Uri uri = Uri.parse(userPhotoUri);
                image_userPhoto.setImageURI(uri);
            }
            tv_userName.setText(userName);
            String userPhoneNumber = currentUser.getMobilePhoneNumber();
            if (userPhoneNumber != null) tv_phoneNumber.setText(userPhoneNumber);
            else tv_phoneNumber.setText("您尚未绑定手机号");
        }else {
            tv_userName.setText("未登录");
            tv_phoneNumber.setText("");
        }
    }

    @OnClick({R.id.image_userPhoto})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.image_userPhoto:
                Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
                // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
                intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/png");
                startActivityForResult(intentToPickPic, 1);
                break;
        }
    }
    //当点击某涨照片完成时会回调到onActivityResult 在这里处理照片
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MainActivity.RESULT_OK) {
            switch (requestCode) {
                case 1: {
                    // 获取图片
                    try {
                        //该uri是上一个Activity返回的
                        imageUri = data.getData();
                        if (imageUri != null) {
                            AVUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                @Override
                                public void done(AVException e) {
                                    AVUser.getCurrentUser().put("userPhotoUri", imageUri.toString());
                                    AVUser.getCurrentUser().saveInBackground();
                                }
                            });
                            image_userPhoto.setImageURI(imageUri);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}

