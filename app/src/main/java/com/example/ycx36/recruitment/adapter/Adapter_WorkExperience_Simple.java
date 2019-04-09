package com.example.ycx36.recruitment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.dataBean.WorkExperience_Simple_Data;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

public class Adapter_WorkExperience_Simple extends RecyclerView.Adapter<Adapter_WorkExperience_Simple.ViewHolder>{
    private ArrayList<WorkExperience_Simple_Data> mData;   //数据

    /**适配器的构造函数，传入一组数据数组，数据类型为自定义的数据类*/
    public Adapter_WorkExperience_Simple(ArrayList<WorkExperience_Simple_Data> data) {
        this.mData = data;
    }


    /**这个方法用于创建ViewHolder实例，在这里将item布局加载进来，然后创建一个ViewHolder实例，
     * 并把加载进来的布局传到ViewHolder构造函数当中，最后将ViewHolder实例返回。*/
    @Override
    public Adapter_WorkExperience_Simple.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addworkexperience, parent, false);
        // 实例化viewholder
        Adapter_WorkExperience_Simple.ViewHolder viewHolder = new Adapter_WorkExperience_Simple.ViewHolder(v);
        return viewHolder;
    }

    /**这个方法是用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动屏幕内的时候执行，
     * 这里我们通过postion参数得到当前项的实例，然后将数据设置到ViewHolder的ImageView和TextView当中。*/
    @Override
    public void onBindViewHolder(final Adapter_WorkExperience_Simple.ViewHolder holder, final int position) {
        // 绑定数据
        WorkExperience_Simple_Data WorkExperience_Simple_Data = mData.get(position); //通过数组分别创建每个数组元素的实例。
        holder.workname.setText(WorkExperience_Simple_Data.getWorkName());
        holder.tv_time1.setText(WorkExperience_Simple_Data.getTime1());
        holder.tv_time2.setText(WorkExperience_Simple_Data.getTime2());
    }
    /**这个方法用于告诉RecyclerView一共有多少个项，然后直接返回长度。*/
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
    /**自己定义的一个内部类，继承自RecyclerView.ViewHolder（适配器名）*/
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView workname,tv_time1,tv_time2;
        TagFlowLayout mFlowLayout;
        //内部类构造函数，需要传入一个View参数，这个参数就是RecyclerView子项的最外层布局
        public ViewHolder(View itemView) {
            super(itemView);
            workname = itemView.findViewById(R.id.workname);
            tv_time1 = itemView.findViewById(R.id.tv_time1);
            tv_time2 = itemView.findViewById(R.id.tv_time2);
        }
    }
}
