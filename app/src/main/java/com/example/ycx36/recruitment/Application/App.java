package com.example.ycx36.recruitment.Application;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.ycx36.recruitment.model.db.usereducationexperience;
import com.example.ycx36.recruitment.model.db.userinfo;
import com.example.ycx36.recruitment.model.db.userprojectexperience;
import com.example.ycx36.recruitment.model.db.users;
import com.example.ycx36.recruitment.model.db.userworkexperience;
import com.example.ycx36.recruitment.ui.CustomUserProvider;
import com.example.ycx36.recruitment.util.LearnCloudSubclass_User;
import com.example.ycx36.recruitment.util.learnCloudtest;
import com.facebook.drawee.backends.pipeline.Fresco;
import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKit;
public class App extends LitePalApplication {

    String AppId = "aPSIlusI3gBWJgHpooRt5hck-gzGzoHsz";
    String AppKey = "HDS0JYECcHCUiOTjVmYQcoyT";
    AVUser currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        AVObject.registerSubclass(LearnCloudSubclass_User.class);
        AVObject.registerSubclass(learnCloudtest.class);
        AVOSCloud.initialize(this,AppId,AppKey);//初始化LeanCloud
        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        LCChatKit.getInstance().init(getApplicationContext(), AppId, AppKey);
        LitePal.initialize(this);
        AVOSCloud.setDebugLogEnabled(true);
        AVIMClient.setAutoOpen(true);
        currentUser = AVUser.getCurrentUser();
        update();
        initDB();
    }


    /**登录后—初始化聊天列表*/
    public void update(){
        if (currentUser != null) {
            LCChatKit.getInstance().open(currentUser.getUsername(), new AVIMClientCallback() {
                @Override
                public void done(AVIMClient avimClient, AVIMException e) {

                    if (null != e) {
                        Toast.makeText(getApplicationContext(), "聊天列表刷新失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**初始化数据库表，避免无表或空表异常*/
    private void initDB(){
        int amount = 0;
        SQLiteDatabase db;
        db = openOrCreateDatabase("UserInfoDB.db", Context.MODE_PRIVATE, null);  //数据库名称
        try{
            Cursor c = db.rawQuery("select * from users", null);    //表名称
            amount = c.getCount();
        } catch (Exception e) {
            if (currentUser != null) {
                userinfo userinfo = new userinfo();
                userinfo.save();
                userworkexperience userworkexperience = new userworkexperience();
                userworkexperience.save();
                List<userworkexperience> userworkexperienceList = new ArrayList<>();
                userworkexperienceList.add(userworkexperience);
                userprojectexperience userprojectexperience = new userprojectexperience();
                userprojectexperience.save();
                List<userprojectexperience> userprojectexperienceList = new ArrayList<>();
                userprojectexperienceList.add(userprojectexperience);
                usereducationexperience usereducationexperience = new usereducationexperience();
                usereducationexperience.save();
                List<usereducationexperience> usereducationexperienceList = new ArrayList<>();
                usereducationexperienceList.add(usereducationexperience);
                users u = new users();
                u.setUserinfo(userinfo);
                u.setUserworkexperience(userworkexperienceList);
                u.setUserprojectexperience(userprojectexperienceList);
                u.setUsereducationexperience(usereducationexperienceList);
                u.setU_Id(currentUser.getObjectId());
                u.save();  //将用户id存入本地数据库
            }
        }
    }


}
