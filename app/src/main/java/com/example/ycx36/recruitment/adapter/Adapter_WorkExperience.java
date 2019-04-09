package com.example.ycx36.recruitment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.dataBean.WorkExperienceData;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;


import java.util.ArrayList;

public class Adapter_WorkExperience extends RecyclerView.Adapter<Adapter_WorkExperience.ViewHolder>{
    private ArrayList<WorkExperienceData> mData;   //数据
    private String[] mVals;
    private Context mContext;

    /**适配器的构造函数，传入一组数据数组，数据类型为自定义的数据类*/
    public Adapter_WorkExperience(Context context ,ArrayList<WorkExperienceData> data,String[] mVals) {
        this.mContext = context;
        this.mData = data;
        this.mVals = mVals;
    }

    public void updateData(ArrayList<WorkExperienceData> data) {
        this.mData = data;
        notifyDataSetChanged();

    }

    /**这个方法用于创建ViewHolder实例，在这里将item布局加载进来，然后创建一个ViewHolder实例，
     * 并把加载进来的布局传到ViewHolder构造函数当中，最后将ViewHolder实例返回。*/
    @Override
    public Adapter_WorkExperience.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_experience, parent, false);
        // 实例化viewholder
        Adapter_WorkExperience.ViewHolder viewHolder = new Adapter_WorkExperience.ViewHolder(v);
        return viewHolder;
    }

    /**这个方法是用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动屏幕内的时候执行，
     * 这里我们通过postion参数得到当前项的实例，然后将数据设置到ViewHolder的ImageView和TextView当中。*/
    @Override
    public void onBindViewHolder(final Adapter_WorkExperience.ViewHolder holder, final int position) {
        // 绑定数据
        WorkExperienceData WorkExperienceData = mData.get(position); //通过数组分别创建每个数组元素的实例。
        holder.tv_workname.setText(WorkExperienceData.getWorkLocation());
        holder.tv_workPosition.setText(WorkExperienceData.getJobTitle());
        holder.tv_time1.setText(WorkExperienceData.getWorkTime1());
        holder.tv_time2.setText(WorkExperienceData.getWorkTime2());
        holder.tv_workContent.setText(WorkExperienceData.getWorkContent());
        holder.tv_workgrade.setText(WorkExperienceData.getAchievementsInWork());
        final LayoutInflater mInflater = LayoutInflater.from(mContext);
        holder.mFlowLayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        holder.mFlowLayout, false);
                tv.setText(s);
                return tv;
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
        TextView tv_workname,tv_workPosition,tv_time1,tv_time2,tv_workContent,tv_workgrade;
        TagFlowLayout mFlowLayout;
        //内部类构造函数，需要传入一个View参数，这个参数就是RecyclerView子项的最外层布局
        public ViewHolder(View itemView) {
            super(itemView);
            tv_workname = itemView.findViewById(R.id.tv_workname);
            tv_workPosition = itemView.findViewById(R.id.tv_workPosition);
            tv_time1 = itemView.findViewById(R.id.tv_time1);
            tv_time2 = itemView.findViewById(R.id.tv_time2);
            tv_workContent = itemView.findViewById(R.id.tv_workContent);
            tv_workgrade = itemView.findViewById(R.id.tv_workgrade);
            mFlowLayout = itemView.findViewById(R.id.id_flowlayout);
        }
    }
}
