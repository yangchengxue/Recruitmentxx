package com.example.ycx36.recruitment.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.dataBean.PositionDataBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**发布职位的列表*/
public class Adapter_Position extends RecyclerView.Adapter<Adapter_Position.ViewHolder>{

    private ArrayList<PositionDataBean> mData;   //数据

    /**定义两个接口*/
    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }
    public interface OnLongClickListener {
        void setOnLongClickListener(View view, int position);
    }
    //
//    /**声明接口*/
    private Adapter_Position.OnItemClickListener mOnItemClickListener;
    private Adapter_Position.OnLongClickListener mOnLongClickListener;
    //
    //创建条目点击的方法，用变量接收一下接口对象
    public void setOnItemClickListener(Adapter_Position.OnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;

    }
    //创建条目长按的方法，用变量接收一下接口对象
    public void setOnLongClickListener(Adapter_Position.OnLongClickListener onLongClickListener){
        this.mOnLongClickListener=onLongClickListener;
    }

    /**适配器的构造函数，传入一组数据数组，数据类型为自定义的数据类*/
    public Adapter_Position(ArrayList<PositionDataBean> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<PositionDataBean> data) {
        this.mData = data;
        notifyDataSetChanged();

    }

    /**这个方法用于创建ViewHolder实例，在这里将item布局加载进来，然后创建一个ViewHolder实例，
     * 并把加载进来的布局传到ViewHolder构造函数当中，最后将ViewHolder实例返回。*/
    @Override
    public Adapter_Position.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_position, parent, false);
        // 实例化viewholder
        Adapter_Position.ViewHolder viewHolder = new Adapter_Position.ViewHolder(v);
        return viewHolder;
    }

    /**这个方法是用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动屏幕内的时候执行，
     * 这里我们通过postion参数得到当前项的实例，然后将数据设置到ViewHolder的ImageView和TextView当中。*/
    @Override
    public void onBindViewHolder(final Adapter_Position.ViewHolder holder, final int position) {
        // 绑定数据
        PositionDataBean PositionDataBean = mData.get(position); //通过数组分别创建每个数组元素的实例。
        holder.tv_JobTitle.setText(PositionDataBean.getPositionName());
        holder.tv_number.setText(""+PositionDataBean.getPersonNumber());
        holder.tv_where.setText(PositionDataBean.getSite());
        holder.tv_college.setText(PositionDataBean.getCollege());

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
        TextView tv_JobTitle,tv_number,tv_where,tv_college;
        //内部类构造函数，需要传入一个View参数，这个参数就是RecyclerView子项的最外层布局
        @SuppressLint("ResourceAsColor")
        public ViewHolder(View itemView) {
            super(itemView);
            tv_JobTitle = itemView.findViewById(R.id.tv_JobTitle);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_where = itemView.findViewById(R.id.tv_where);
            tv_college = itemView.findViewById(R.id.tv_college);
        }
    }
    /**删除item的方法*/
    public void removeData(int position) {
        //保证列表有数据，并且最少有一条
        if(mData.size()<2&&mData.size()!=0){
            mData.remove(0);
            notifyDataSetChanged();
        }else if(mData.size()==0){//当列表没有数据提示用户，免得造成系统崩溃
//            Toast.makeText(context,"没数据了",Toast.LENGTH_SHORT).show();
        }else{//更新列表
            mData.remove(position);
            notifyDataSetChanged();
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,mData.size());
        }
    }
}
