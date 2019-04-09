package com.example.ycx36.recruitment.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.dataBean.SetInfoDataItemBean;

import java.util.ArrayList;

public class Adapter_SetUserInfo extends RecyclerView.Adapter<Adapter_SetUserInfo.ViewHolder>{

    private ArrayList<SetInfoDataItemBean> mData;   //数据

    /**定义两个接口*/
    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }
    public interface OnLongClickListener {
        void setOnLongClickListener(View view, int position);
    }
    //
//    /**声明接口*/
    private Adapter_SetUserInfo.OnItemClickListener mOnItemClickListener;
    private Adapter_SetUserInfo.OnLongClickListener mOnLongClickListener;
    //
    //创建条目点击的方法，用变量接收一下接口对象
    public void setOnItemClickListener(Adapter_SetUserInfo.OnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;

    }
    //创建条目长按的方法，用变量接收一下接口对象
    public void setOnLongClickListener(Adapter_SetUserInfo.OnLongClickListener onLongClickListener){
        this.mOnLongClickListener=onLongClickListener;
    }

    /**适配器的构造函数，传入一组数据数组，数据类型为自定义的数据类*/
    public Adapter_SetUserInfo(ArrayList<SetInfoDataItemBean> data) {
        this.mData = data;
    }


    /**这个方法用于创建ViewHolder实例，在这里将item布局加载进来，然后创建一个ViewHolder实例，
     * 并把加载进来的布局传到ViewHolder构造函数当中，最后将ViewHolder实例返回。*/
    @Override
    public Adapter_SetUserInfo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_userinfo, parent, false);
        // 实例化viewholder
        Adapter_SetUserInfo.ViewHolder viewHolder = new Adapter_SetUserInfo.ViewHolder(v);
        return viewHolder;
    }

    /**这个方法是用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动屏幕内的时候执行，
     * 这里我们通过postion参数得到当前项的实例，然后将数据设置到ViewHolder的ImageView和TextView当中。*/
    @Override
    public void onBindViewHolder(final Adapter_SetUserInfo.ViewHolder holder, final int position) {
        // 绑定数据
        SetInfoDataItemBean setInfoDataItemBean = mData.get(position); //通过数组分别创建每个数组元素的实例。
        holder.itemName.setText(setInfoDataItemBean.getItemName());
        holder.itemRightText.setText(setInfoDataItemBean.getItemRightText());

        /**子项的监听事件*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mOnItemClickListener.setOnItemClickListener(holder.itemView,position);//控件和条目下标
            }
        });
        //holder的条目视图长按监听
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mOnLongClickListener.setOnLongClickListener(holder.itemView,position);
                return false;
            }
        });
    }

    /**这个方法用于告诉RecyclerView一共有多少个项，然后直接返回长度。*/
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
    /**自己定义的一个内部类，继承自RecyclerView.ViewHolder（适配器名）*/
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName,itemRightText;
        //内部类构造函数，需要传入一个View参数，这个参数就是RecyclerView子项的最外层布局
        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemRightText = itemView.findViewById(R.id.itemRightText);
        }
    }

    /**改变Item内数据*/
    public void ChangeData(int positon,String title,String text){
        SetInfoDataItemBean setInfoDataItemBean;
        setInfoDataItemBean = new SetInfoDataItemBean(title,text);
        mData.set(positon,setInfoDataItemBean);
        notifyDataSetChanged();
    }

    /**获取Item数据*/
    public SetInfoDataItemBean getData(int position){
        return mData.get(position);
    }
}
