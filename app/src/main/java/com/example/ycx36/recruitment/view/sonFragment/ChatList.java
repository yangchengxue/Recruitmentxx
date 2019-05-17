package com.example.ycx36.recruitment.view.sonFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.view.activity.ShowBigPhotoActivity;
import com.example.ycx36.recruitment.view.activity.activity_SystemMsg;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationListFragment;
import cn.leancloud.chatkit.adapter.LCIMCommonListAdapter;
import cn.leancloud.chatkit.cache.LCIMConversationItemCache;
import cn.leancloud.chatkit.view.LCIMDividerItemDecoration;
import cn.leancloud.chatkit.viewholder.LCIMConversationItemHolder;
import de.greenrobot.event.EventBus;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

public class ChatList extends LCIMConversationListFragment {

    @BindView(R.id.messageItemDraw) SimpleDraweeView messageItemDraw;
    @BindView(R.id.tv_name)
    TextView tv_name;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lcim_conversation_list_fragment2, container, false);
        ButterKnife.bind(this, view);
        this.refreshLayout = view.findViewById(cn.leancloud.chatkit.R.id.fragment_conversation_srl_pullrefresh);
        this.recyclerView = view.findViewById(cn.leancloud.chatkit.R.id.fragment_conversation_srl_view);
        this.refreshLayout.setEnabled(false);
        this.layoutManager = new LinearLayoutManager(this.getActivity());
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.addItemDecoration(new LCIMDividerItemDecoration(this.getActivity()));
        this.itemAdapter = new LCIMCommonListAdapter(LCIMConversationItemHolder.class);
        this.recyclerView.setAdapter(this.itemAdapter);
        EventBus.getDefault().register(this);
        return view;
    }

    @OnClick({R.id.systemMessage})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.systemMessage:  //进入系统消息
                startActivity(new Intent(getActivity(), activity_SystemMsg.class));
                break;
        }
    }

    Badge badge;
    @SuppressLint("ResourceAsColor")
    public void onResume() {
        super.onResume();
//        Toast.makeText(getActivity(),"2222222222",Toast.LENGTH_SHORT).show();
        SharedPreferences pref = getActivity().getSharedPreferences("ifReadMsg",MODE_PRIVATE);
        String value = pref.getString("ifReadMsg","");
        if (value.equals("no")){
            badge  = new QBadgeView(getActivity()).bindTarget(messageItemDraw).setBadgeNumber(1);
        }else if (value.equals("yes")){
            badge.hide(true);
            tv_name.setText("系统消息(暂无未读消息)");
            tv_name.setTextColor(R.color.black);
        }
    }
}
