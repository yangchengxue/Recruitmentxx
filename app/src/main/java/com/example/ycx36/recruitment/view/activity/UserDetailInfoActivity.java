package com.example.ycx36.recruitment.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.FollowCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.util.LearnCloudSubclass_User;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;

public class UserDetailInfoActivity extends AppCompatActivity {

    @BindView(R.id.AuserDraw) SimpleDraweeView AuserDraw;
    @BindView(R.id.tv_name) TextView tv_name;
    @BindView(R.id.tv_userPhone) TextView tv_userPhone;
    @BindView(R.id.tv_userSex) TextView tv_userSex;
    @BindView(R.id.tv_userAddress1) TextView tv_userAddress1;
    @BindView(R.id.tv_userAddress2) TextView tv_userAddress2;
    @BindView(R.id.tv_header_right) TextView tv_header_right; //取消关注
    @BindView(R.id.bt_addToAttention) Button bt_addToAttention;
    private String userObjectId,userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_info);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        userObjectId = bundle.getString("userObjectId");   //根据键名读取数据
        userName = bundle.getString("userName");   //根据键名读取数据
        if (!userObjectId.equals("")) {
            bt_addToAttention.setText("添加到关注列表");
            tv_header_right.setVisibility(View.INVISIBLE);
        }
        else {
            bt_addToAttention.setText("发送消息");
            tv_header_right.setVisibility(View.VISIBLE);
        }

        AVQuery<LearnCloudSubclass_User> query = AVObject.getQuery(LearnCloudSubclass_User.class);
        query.whereEqualTo("username",userName);
        query.findInBackground(new FindCallback<LearnCloudSubclass_User>() {
            @Override
            public void done(List<LearnCloudSubclass_User> results, AVException e) {
                if (results.size() != 0) {
                    for (final LearnCloudSubclass_User a : results) {
                        String phoneNumber = a.getMobilePhoneNumber();
                        String userSex = a.getuserSex();
                        String userAddress1 = a.getuserProvince();
                        String userAddress2 = a.getuserCity();
                        tv_name.setText(userName);
                        if (a.getuserPhotoUri() != null) {
                            showUserPhoto(a.getuserPhotoUri(), AuserDraw);
                        }
                        tv_userPhone.setText(phoneNumber.substring(0,3)+"******"+phoneNumber.substring(9,11));
                        if (userSex != null) tv_userSex.setText(userSex);
                        else tv_userSex.setText("未知");
                        if (userAddress1 != null) tv_userAddress1.setText(userAddress1);
                        else tv_userAddress1.setText("未知");
                        if (userAddress2 != null) tv_userAddress2.setText(userAddress2);
                        else tv_userAddress2.setText("未知");
                    }
                }
            }
        });
    }

    public void showUserPhoto(String path, SimpleDraweeView Dview) {
        Uri uri = Uri.parse(path);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setLocalThumbnailPreviewsEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .build();
        Dview.setController(controller);
    }

    @OnClick({R.id.bt_addToAttention, R.id.lin_header_back2,R.id.tv_header_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_addToAttention:
                if (!userObjectId.equals(""))
                    FocusPerson(userObjectId);
                else {
                    IntentToChat();
                    finish();
                }
                break;
            case R.id.lin_header_back2:
                finish();
                break;
            case R.id.tv_header_right:  //取消关注

                break;
        }
    }

    /**调用该方法实现当前用户关注某人关注某人，传入需要关注的人的id*/
    public void FocusPerson(String userObjectId){
        //关注
        AVUser avUser = AVUser.getCurrentUser();
        if (avUser != null){
            avUser.followInBackground(userObjectId, new FollowCallback() {
                @Override
                public void done(AVObject object, AVException e) {
                    if (e == null) {
                        Toast.makeText(UserDetailInfoActivity.this,"关注成功",Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (e.getCode() == AVException.DUPLICATE_VALUE) {
                        Toast.makeText(UserDetailInfoActivity.this,"关注失败",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(UserDetailInfoActivity.this,"您未登录。",Toast.LENGTH_SHORT).show();
        }

    }

    /**打开聊天界面*/
    public void IntentToChat(){
        AVUser currentUser = AVUser.getCurrentUser();
            LCChatKit.getInstance().open(currentUser.getUsername(), new AVIMClientCallback() {
                @Override
                public void done(AVIMClient avimClient, AVIMException e) {
                    if (null == e) {
                        Intent intent = new Intent(UserDetailInfoActivity.this, LCIMConversationActivity.class);
                        intent.putExtra(LCIMConstants.PEER_ID, userName);
                        UserDetailInfoActivity.this.startActivity(intent);
                    } else {
                        Toast.makeText(UserDetailInfoActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}
