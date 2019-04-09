package com.example.ycx36.recruitment.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.ycx36.recruitment.adapter.Adapter_Message;
import com.example.ycx36.recruitment.model.dataBean.MessageDataBean;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.cache.LCIMProfileCache;
import cn.leancloud.chatkit.utils.LCIMConstants;

public class CustomUserProvider implements LCChatProfileProvider {

    private static CustomUserProvider customUserProvider;
    public synchronized static CustomUserProvider getInstance() {
        if (null == customUserProvider) {
            customUserProvider = new CustomUserProvider();
        }
        return customUserProvider;
    }

    private CustomUserProvider() {
    }

    private static List<LCChatKitUser> partUsers1 = new ArrayList<LCChatKitUser>();

    // 此数据均为模拟数据，仅供参考
    static {
        GetMyFollwersToPrivateMessage();
    }

    // 根据传入的 clientId list，查找、返回用户的 Profile 信息(id、昵称、头像)
    @Override
    public void fetchProfiles(List<String> list, LCChatProfilesCallBack callBack) {
        List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
        for (String userId : list) {
            for (LCChatKitUser user : partUsers1) {
                if (user.getUserId().equals(userId)) {
                    userList.add(user);
                    break;
                }
            }
        }
        callBack.done(userList, null);
    }

    public List<LCChatKitUser> getAllUsers() {
        return partUsers1;
    }

    /**获取当前用户的关注列表*/
    public static void GetMyFollwersToPrivateMessage() {
        //avObjects 就是用户的关注用户列表
        final List<String> listUserName = new ArrayList<>();
        final List<String> listUserId = new ArrayList<>();
        final List<String> listUserPhotoUris = new ArrayList<>();
        AVUser avUser = AVUser.getCurrentUser();
        if (avUser != null){
            listUserName.add(avUser.getUsername());
            listUserId.add(avUser.getUsername());
            String currentUri = (String) avUser.get("userPhotoUri");
            listUserPhotoUris.add(currentUri);
        AVQuery<AVUser> followeeQuery = AVUser.followeeQuery(avUser.getObjectId(), AVUser.class);
        followeeQuery.findInBackground(new FindCallback<AVUser>() {
            @Override
            public void done(final List<AVUser> avObjects, AVException avException) {

                int i;
                for (i=0; i<avObjects.size(); i++){
                    AVObject todo = AVObject.createWithoutData("_User", avObjects.get(i).getObjectId());
                    todo.fetchInBackground(new GetCallback<AVObject>() {
                        @Override
                        public void done(AVObject avObject, AVException e) {
                            if (avObject != null){
                                listUserName.add(avObject.getString("username"));
                                listUserId.add(avObject.getString("username"));
                                String s = avObject.getString("userPhotoUri");
                                listUserPhotoUris.add(s);
                                if(listUserName.size() == avObjects.size()){
                                    for (int k = 0; k<listUserName.size(); k++) {
                                        LCChatKitUser user = new LCChatKitUser(listUserId.get(k), listUserName.get(k), listUserPhotoUris.get(k));
                                        partUsers1.add(user);
//                                    LCIMProfileCache.getInstance().cacheUser(user);
                                        Log.d("tsttt",listUserId.get(k)+"    "+listUserName.get(k));
                                        Log.d("tsttt111",partUsers1.get(k).getUserName()+"    "+partUsers1.get(k).getAvatarUrl()+"     "+partUsers1.size());
                                    }
                                }
                            }
                        }

                    });
                }
            }
        });
        }
    }

}


