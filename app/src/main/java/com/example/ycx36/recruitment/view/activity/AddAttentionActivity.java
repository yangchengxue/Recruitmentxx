package com.example.ycx36.recruitment.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.FollowCallback;
import com.avos.avoscloud.GetCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.util.LearnCloudSubclass_User;
import com.example.ycx36.recruitment.util.learnCloudtest;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

public class AddAttentionActivity extends AppCompatActivity {

    @BindView(R.id.search_view) SearchView searchView;
    @BindView(R.id.userDraw) SimpleDraweeView userDraw;
    @BindView(R.id.tv_name) TextView tv_name;
    @BindView(R.id.tv_userNull) TextView tv_userNull;
    @BindView(R.id.userRelativeLayout) RelativeLayout userRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attention);
        ButterKnife.bind(this);



        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                AVQuery<LearnCloudSubclass_User> query = AVObject.getQuery(LearnCloudSubclass_User.class);
                query.whereEqualTo("username",string);
                query.findInBackground(new FindCallback<LearnCloudSubclass_User>() {
                    @Override
                    public void done(List<LearnCloudSubclass_User> results, AVException e) {
                        if (results.size() != 0) {
                            for (final LearnCloudSubclass_User a : results) {
                                final String userName = a.getUsername();
                                tv_name.setText(userName);
                                tv_userNull.setVisibility(View.GONE);
                                userRelativeLayout.setVisibility(View.VISIBLE);
                                userDraw.setVisibility(View.VISIBLE);
                                if (a.getuserPhotoUri() != null) {
                                    showUserPhoto(a.getuserPhotoUri(), userDraw);
                                }
                                userRelativeLayout.setOnClickListener(new View.OnClickListener(){
                                    public void onClick(View view){
//                                        FocusPerson(a.getObjectId()); //点击关注
                                        Intent intent = new Intent(AddAttentionActivity.this,UserDetailInfoActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("userObjectId",a.getObjectId());
                                        bundle.putString("userName",userName);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });

                            }
                        } else {
                            tv_userNull.setVisibility(View.VISIBLE);
                            userDraw.setVisibility(View.INVISIBLE);
                            userRelativeLayout.setVisibility(View.INVISIBLE);
                        }
                    }
                });
                InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
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


}
