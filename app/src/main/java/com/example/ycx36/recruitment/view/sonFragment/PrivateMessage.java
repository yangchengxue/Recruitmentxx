package com.example.ycx36.recruitment.view.sonFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FollowCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.dataBean.MessageDataBean;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IfragmentView;
import com.example.ycx36.recruitment.view.activity.AddAttentionActivity;
import com.example.ycx36.recruitment.view.activity.ShowBigPhotoActivity;
import com.example.ycx36.recruitment.view.fragment.IfragmentView;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**消息 模块的 联系人 模块*/
public class PrivateMessage extends Fragment{
    @BindView(R.id.messageRecyclerView) public RecyclerView messageRecyclerView;
    IfragmentView ifragmentView;
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.privatemessage, container, false);
            ButterKnife.bind(this, view);
            ifragmentView = new ImplClass_IfragmentView(view.getContext());
        }
        return view;
    }

    @OnClick({R.id.cv_item})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.cv_item:   //添加关注
                startActivity(new Intent(getActivity(), AddAttentionActivity.class));
                break;
        }
    }


    private ArrayList<MessageDataBean> itemFollwerslist = new ArrayList<>();
    public void onStart() {
        super.onStart();
        itemFollwerslist.clear();
        ifragmentView.GetMyFollwersToPrivateMessage(messageRecyclerView,itemFollwerslist);
    }

}

