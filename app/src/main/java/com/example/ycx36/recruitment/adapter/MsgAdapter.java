package com.example.ycx36.recruitment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.dataBean.Msg;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{

    private List<Msg> mMsgList;

    static class ViewHolder extends Adapter_Position.ViewHolder{

        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        SimpleDraweeView image_oppositePerson,image_my;


        public ViewHolder (View view){
            super(view);
            leftLayout = view.findViewById(R.id.left_layout);
            rightLayout = view.findViewById(R.id.right_layout);
            leftMsg = view.findViewById(R.id.left_msg);
            rightMsg = view.findViewById(R.id.right_msg);
            image_oppositePerson = view.findViewById(R.id.image_oppositePerson);
            image_my = view.findViewById(R.id.image_my);
        }
    }

    public MsgAdapter(List<Msg> msgList) {
        mMsgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Msg msg = mMsgList.get(position);
        /**如果这条消息是收到的，则显示左边的消息布局，如果这条消息是发出的，则显示右边的消息布局*/
        if (msg.getType() == Msg.TYPE_RECEIVED){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.image_my.setVisibility(View.GONE); //我的头像不显示
            holder.leftMsg.setText(msg.getContent());
        }else if(msg.getType() == Msg.TYPE_SEND){
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.image_oppositePerson.setVisibility(View.GONE);  //设置客服头像不显示。
            holder.rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

}
