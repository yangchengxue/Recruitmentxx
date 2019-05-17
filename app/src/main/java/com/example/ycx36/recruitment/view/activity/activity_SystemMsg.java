package com.example.ycx36.recruitment.view.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.adapter.MsgAdapter;
import com.example.ycx36.recruitment.model.dataBean.Msg;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class activity_SystemMsg extends AppCompatActivity {

    @BindView(R.id.Msg_editText)
    EditText Msg_editText;
    @BindView(R.id.bt_send_msg)
    Button bt_send_msg;
    @BindView(R.id.msg_recycler_view)
    RecyclerView msg_recycler_view;

    private List<Msg> msgList = new ArrayList<>();
    private MsgAdapter msgAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__system_msg);
        ButterKnife.bind(this);
//        init();
        AVQuery<AVObject> query = new AVQuery<>("User_System_Class");
        query.whereEqualTo("username",AVUser.getCurrentUser().getUsername());
        // 如果这样写，第二个条件将覆盖第一个条件，查询只会返回 priority = 1 的结果
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                for (int i=0; i<list.size(); i++){
                    init(String.valueOf(list.get(i).get("userMsg")),String.valueOf(list.get(i).get("systemAnswer")));
                }
                Message message = new Message();  //创建一个message对象。并将它的what字段的值指定为UPDATA_TEXT
                message.what = 1;
                handler.sendMessage(message);     //handler去发送消息
            }
        });

        SharedPreferences.Editor editor = getSharedPreferences("ifReadMsg",MODE_PRIVATE).edit();
        editor.putString("ifReadMsg","yes");
        editor.apply();

    }
    @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity_SystemMsg.this);
                msg_recycler_view.setLayoutManager(linearLayoutManager);
                msgAdapt = new MsgAdapter(msgList);
                msg_recycler_view.setAdapter(msgAdapt);
            }
        }
    };

    private void init(String u,String s){
        Msg msg = new Msg(u,s,Msg.TYPE_SEND);
        msgList.add(msg);
    }

    @OnClick({R.id.bt_send_msg,R.id.lin_header_back2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_send_msg:
                sendMsgTo("");
                break;
            case R.id.lin_header_back2:
                finish();
                break;
        }
    }

    /**一个发送消息的方法*/
    public void sendMsgTo(String answer){
        /**如果内容不为null则创建一个新的Msg对象，并把它添加到msglist列表中
         * 之后又调用了适配器的notifyItemInserted()方法，用于通知列表中有新的数据插入，
         * 这样新增的一条消息才能够显示，接着调用适配器的scrollToPosition()方法将显示的数据定位到最后一行，
         * 最后调用setText()方法将editText的内容清空。*/
        String content = Msg_editText.getText().toString();
        if (!"".equals(content)){
            Msg msg = new Msg(content , answer , Msg.TYPE_SEND);
            msgList.add(msg);
            //当有消息时，刷新RecyclerView中的显示
            msgAdapt.notifyItemInserted(msgList.size() - 1);
            msg_recycler_view.scrollToPosition(msgList.size() - 1);
            Submission(Msg_editText);
            Msg_editText.setText("");
        }
    }

    public void Submission(EditText Msg_editText){
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null){
            AVObject todoFolder = new AVObject("User_System_Class");// 构建对象
            todoFolder.put("userobjectid", currentUser.getObjectId());
            todoFolder.put("username", currentUser.getUsername());
            todoFolder.put("flag", 1);
            todoFolder.put("userMsg", Msg_editText.getText().toString());
            todoFolder.saveInBackground();
        }else {
            Toast.makeText(this,"请先登录。",Toast.LENGTH_SHORT).show();
        }

    }

}
