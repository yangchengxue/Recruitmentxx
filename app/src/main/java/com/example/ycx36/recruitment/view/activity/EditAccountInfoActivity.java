package com.example.ycx36.recruitment.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.adapter.SurroundindPlaceAdapter;
import com.example.ycx36.recruitment.okhttp.HttpUtil;
import com.example.ycx36.recruitment.util.LearnCloudSubclass_User;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 编辑向其他用户展示的资料
 */
public class EditAccountInfoActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.userDrawee)
    SimpleDraweeView userDrawee;
    @BindView(R.id.CK_boy)
    CheckBox CK_boy;
    @BindView(R.id.CK_girl)
    CheckBox CK_girl;
    @BindView(R.id.tv_userName)
    TextView tv_userName;
    @BindView(R.id.tv_header_right)
    TextView tv_header_right;
    @BindView(R.id.tv_hint)
    TextView tv_hint;
    @BindView(R.id.tv_email)
    EditText tv_email;
    @BindView(R.id.tv_mobilephone)
    EditText tv_mobilephone;

    private AVUser currentUser = AVUser.getCurrentUser();
    //用户头像地址
    private String headURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_info);
        ButterKnife.bind(this);
        init();
    }

    @OnClick({R.id.lin_header_back2, R.id.tv_header_right, R.id.RL_1, R.id.RL_2, R.id.RL_3, R.id.RL_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_header_back2:
                finish();
                break;
            case R.id.tv_header_right:  //保存
                saveInfo();
                sendData();
                break;
            case R.id.RL_1:
                Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
                // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
                intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/png");
                startActivityForResult(intentToPickPic, 1);
                break;
            case R.id.RL_2:
                break;
            case R.id.RL_3:
                break;
            case R.id.RL_4:
                break;
        }
    }


    public void init() {
        if (currentUser != null) {
            tv_hint.setVisibility(View.INVISIBLE);
            /*通过子类化的方式获取云端图片链接*/
            AVQuery<LearnCloudSubclass_User> query = AVObject.getQuery(LearnCloudSubclass_User.class);
            query.whereEqualTo("username", currentUser.getUsername());
            query.findInBackground(new FindCallback<LearnCloudSubclass_User>() {
                @Override
                public void done(List<LearnCloudSubclass_User> results, AVException e) {
                    if (results.size() != 0) {
                        for (final LearnCloudSubclass_User a : results) {
                            if (a.getuserPhotoUri() != null) {
                                String userPhotoUri = a.getuserPhotoUri();
                                Uri uri = Uri.parse(userPhotoUri);
                                userDrawee.setImageURI(uri);
                            }
                            if (tv_email.getText().toString().equals("Null")) {
                                if (a.getEmail() != null) {
                                    String email = a.getEmail();
                                    tv_email.setText(email);
                                } else tv_email.setText("");
                            } else tv_email.setText(getUserEmailData());

                        }
                    }
                }
            });
            //这种方法获取云端图片链接不可用（未知原因）
//            String userPhotoUri = (String) currentUser.get("userPhotoUri");
//            if (userPhotoUri != null) {
//                Uri uri = Uri.parse(userPhotoUri);
//                userDrawee.setImageURI(uri);
//            }
            if (getSexData().equals("男")) CK_boy.setChecked(true);
            if (getSexData().equals("女")) CK_girl.setChecked(true);

            if (currentUser.get("userSex") != null) {
                String x_sex = (String) currentUser.get("userSex");
                if (getSexData().equals("")) {
                    if (x_sex.equals("男")) CK_boy.setChecked(true);
                    if (x_sex.equals("女")) CK_girl.setChecked(true);
                }
            }
            tv_userName.setText(getUserNameData());
//            if (tv_email.getText().toString().equals("Null")) {
//                if (currentUser.getEmail() != null) {
//                    tv_email.setText(currentUser.getEmail());
//                } else tv_email.setText("");
//            } else tv_email.setText(getUserEmailData());

            if (tv_mobilephone.getText().toString().equals("Null")) {
                tv_mobilephone.setText(currentUser.getMobilePhoneNumber());
            } else tv_mobilephone.setText(getUserPhoneData());

        } else {
            tv_header_right.setVisibility(View.INVISIBLE); //如果用户未登录，隐藏保存按钮
        }

        CK_boy.setOnCheckedChangeListener(this);
        CK_girl.setOnCheckedChangeListener(this);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    finish();
                    break;
            }
        }
    };


    public void saveInfo() {
        if (currentUser != null) {
            AVQuery<LearnCloudSubclass_User> query = AVObject.getQuery(LearnCloudSubclass_User.class);
            query.whereEqualTo("username", currentUser.getUsername());
            query.findInBackground(new FindCallback<LearnCloudSubclass_User>() {
                @Override
                public void done(List<LearnCloudSubclass_User> results, AVException e) {
                    if (results.size() != 0) {
                        for (final LearnCloudSubclass_User a : results) {
//                            a.setuserProvince("河北");
                            if (CK_boy.isChecked()) {
                                a.setuserSex("男");
                                saveSexData("男");
                            } else if (CK_girl.isChecked()) {
                                a.setuserSex("女");
                                saveSexData("女");
                            }
                            a.setEmail(tv_email.getText().toString());
                            a.setMobilePhoneNumber(tv_mobilephone.getText().toString());
                            a.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(AVException e) {
                                    if (e != null) {
                                        if (e.getCode() == 125)
                                            Toast.makeText(EditAccountInfoActivity.this, "无效的邮箱地址,保存失败", Toast.LENGTH_SHORT).show();
                                        if (e.getCode() == 127)
                                            Toast.makeText(EditAccountInfoActivity.this, "无效的手机号码,保存失败", Toast.LENGTH_SHORT).show();
                                        if (e.getCode() == 214)
                                            Toast.makeText(EditAccountInfoActivity.this, "该手机号已经被使用,保存失败", Toast.LENGTH_SHORT).show();

                                    } else {
                                        SharedPreferences.Editor editor1 = getSharedPreferences("s_username", MODE_PRIVATE).edit();
                                        editor1.putString("userphone", tv_mobilephone.getText().toString());
                                        editor1.putString("useremail", tv_email.getText().toString());
                                        editor1.apply();
                                        Message message = new Message();  //创建一个message对象。并将它的what字段的值指定为UPDATA_TEXT
                                        message.what = 1;
                                        handler.sendMessage(message);     //handler去发送消息
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }

    }

    private Uri imageUri;

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
                                    //图片上传至云端
                                    final AVFile file;
                                    try {
                                        file = AVFile.withAbsoluteLocalPath("LeanCloud.png", getRealPathFromUri(EditAccountInfoActivity.this, imageUri));
                                        file.saveInBackground(new SaveCallback() {
                                            @Override
                                            public void done(AVException e) {
//                                                Log.d("tupian111111111", file.getUrl());//返回一个唯一的 Url 地址
                                                AVUser.getCurrentUser().put("userPhotoUri", file.getUrl());  //将上传至云端的地址 存入_User表
                                                AVUser.getCurrentUser().saveInBackground();
                                            }
                                        });
                                    } catch (FileNotFoundException e1) {
                                        e1.printStackTrace();
                                    }

                                    //将本地图片途径存入云端
//                                    AVUser.getCurrentUser().put("userPhotoUri", imageUri.toString());
//                                    AVUser.getCurrentUser().saveInBackground();
                                }
                            });
                            userDrawee.setImageURI(imageUri);
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == CK_boy && isChecked) {
            CK_girl.setChecked(false);
            CK_boy.setChecked(true);
        }
        if (buttonView == CK_girl && isChecked) {
            CK_boy.setChecked(false);
            CK_girl.setChecked(true);
        }
    }

    /**
     * 向服务器上传数据
     **/
    private void sendData() {
        Log.e("2333", headURI + getUserNameData() + getSexData() + getUserEmailData() + getUserPhoneData());
        HttpUtil.sendPersonalMessage("http://47.106.170.241:8080/gxnuPublicity/SetPersonalMessage", headURI, getUserNameData(), getSexData(), getUserEmailData(),
                getUserPhoneData(), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        showSendResult(false);
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        showSendResult(true);
                    }
                });
    }

    /**
     * 上传结果反馈
     **/
    private void showSendResult(final Boolean result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                if (!result)
                    Toast.makeText(EditAccountInfoActivity.this, "保存失败，请检查网络", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(EditAccountInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * SharedPreferences保存性别数据
     */
    public void saveSexData(String s) {
        SharedPreferences.Editor editor2 = getSharedPreferences("s_sex", MODE_PRIVATE).edit();
        editor2.putString("sex", s);
        editor2.apply();
    }

    /**
     * SharedPreferences获取性别数据
     */
    public String getSexData() {
        SharedPreferences pref = getSharedPreferences("s_sex", MODE_PRIVATE);
        return pref.getString("sex", "");  //后面的参数为如果找不到对应值，就返回什么样的默认值
    }

    /**
     * SharedPreferences获取用户名数据
     */
    public String getUserNameData() {
        SharedPreferences pref = getSharedPreferences("s_username", MODE_PRIVATE);
        return pref.getString("username", "");  //后面的参数为如果找不到对应值，就返回什么样的默认值
    }

    /**
     * SharedPreferences获取手机号数据
     */
    public String getUserPhoneData() {
        SharedPreferences pref = getSharedPreferences("s_username", MODE_PRIVATE);
        return pref.getString("userphone", "");  //后面的参数为如果找不到对应值，就返回什么样的默认值
    }

    /**
     * SharedPreferences获取手机号数据
     */
    public String getUserEmailData() {
        SharedPreferences pref = getSharedPreferences("s_username", MODE_PRIVATE);
        return pref.getString("useremail", "");  //后面的参数为如果找不到对应值，就返回什么样的默认值
    }

    /**
     * 将相对路径转化为绝对路径
     */
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


}
