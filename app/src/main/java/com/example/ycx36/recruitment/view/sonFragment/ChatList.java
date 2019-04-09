package com.example.ycx36.recruitment.view.sonFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationListFragment;
import cn.leancloud.chatkit.adapter.LCIMCommonListAdapter;
import cn.leancloud.chatkit.cache.LCIMConversationItemCache;
import cn.leancloud.chatkit.view.LCIMDividerItemDecoration;
import cn.leancloud.chatkit.viewholder.LCIMConversationItemHolder;
import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ChatList extends LCIMConversationListFragment {

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(cn.leancloud.chatkit.R.layout.lcim_conversation_list_fragment, container, false);
        this.refreshLayout = (SwipeRefreshLayout)view.findViewById(cn.leancloud.chatkit.R.id.fragment_conversation_srl_pullrefresh);
        this.recyclerView = (RecyclerView)view.findViewById(cn.leancloud.chatkit.R.id.fragment_conversation_srl_view);
        this.refreshLayout.setEnabled(false);
        this.layoutManager = new LinearLayoutManager(this.getActivity());
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.addItemDecoration(new LCIMDividerItemDecoration(this.getActivity()));
        this.itemAdapter = new LCIMCommonListAdapter(LCIMConversationItemHolder.class);
        this.recyclerView.setAdapter(this.itemAdapter);
        EventBus.getDefault().register(this);
        return view;
    }

}
