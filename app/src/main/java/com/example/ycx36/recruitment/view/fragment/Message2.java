package com.example.ycx36.recruitment.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IactivityView;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IfragmentView;
import com.example.ycx36.recruitment.view.activity.AddAttentionActivity;
import com.example.ycx36.recruitment.view.activity.IactivityView;
import com.example.ycx36.recruitment.view.activity.ShowBigPhotoActivity;
import com.example.ycx36.recruitment.view.sonFragment.ChatList;
import com.example.ycx36.recruitment.view.sonFragment.PrivateMessage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leancloud.chatkit.activity.LCIMConversationListFragment;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static android.content.Context.MODE_PRIVATE;

public class Message2 extends Fragment {

    @BindView(R.id.RelativePrivate) RelativeLayout RelativePrivate;
    @BindView(R.id.RelativeContact) RelativeLayout RelativeContact;
    @BindView(R.id.RelativeAddUser) RelativeLayout RelativeAddUser;
    @BindView(R.id.tv_1) TextView t1;
    @BindView(R.id.tv_2) TextView t2;
    @BindView(R.id.tv_3) TextView t3;

    private IactivityView iactivityView;
    private View view ;
    FragmentManager fragmentManager;
    IfragmentView ifragmentView;
    PrivateMessage privateMessage;
    ChatList chatList;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.message_layout2, container, false);
            ButterKnife.bind(this, view);
            chatList = new ChatList();
            privateMessage = new PrivateMessage();
            fragmentManager = getActivity().getSupportFragmentManager();
            iactivityView = new ImplClass_IactivityView(view.getContext());
            ifragmentView = new ImplClass_IfragmentView(view.getContext());
            ifragmentView.ChangeMessageTitleStyle(2,getActivity(),RelativePrivate,t1,RelativeContact,t2,RelativeAddUser,t3);
            iactivityView.showFragment(3,fragmentManager,chatList,privateMessage);
        }
        return view;
    }

    @OnClick({R.id.RelativePrivate,R.id.RelativeContact,R.id.RelativeAddUser})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.RelativePrivate:
                iactivityView.showFragment(1,fragmentManager,chatList,privateMessage);
                ifragmentView.ChangeMessageTitleStyle(1,getActivity(),RelativePrivate,t1,RelativeContact,t2,RelativeAddUser,t3);
                break;
            case R.id.RelativeContact:
                iactivityView.showFragment(2,fragmentManager,chatList,privateMessage);
                ifragmentView.ChangeMessageTitleStyle(2,getActivity(),RelativePrivate,t1,RelativeContact,t2,RelativeAddUser,t3);
                break;
            case R.id.RelativeAddUser:
                startActivity(new Intent(getActivity(), AddAttentionActivity.class));
                break;

        }
    }

//    Badge badge;
//    public void onResume() {
//        super.onResume();
////        Toast.makeText(getActivity(),"2222222222",Toast.LENGTH_SHORT).show();
//        SharedPreferences pref = getActivity().getSharedPreferences("ifReadMsg", MODE_PRIVATE);
//        String value = pref.getString("ifReadMsg", "");
//        if (value.equals("no")) {
//            badge = new QBadgeView(getActivity()).bindTarget(RelativePrivate).setBadgeNumber(1);
//        } else if (value.equals("yes")) {
//            badge.hide(true);
//        }
//    }
}
