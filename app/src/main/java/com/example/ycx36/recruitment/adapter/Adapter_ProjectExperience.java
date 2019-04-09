package com.example.ycx36.recruitment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.dataBean.ProjectExperienceData;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

public class Adapter_ProjectExperience extends RecyclerView.Adapter<Adapter_ProjectExperience.ViewHolder>{
    private ArrayList<ProjectExperienceData> mData;   //数据

    /**适配器的构造函数，传入一组数据数组，数据类型为自定义的数据类*/
    public Adapter_ProjectExperience(ArrayList<ProjectExperienceData> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<ProjectExperienceData> data) {
        this.mData = data;
        notifyDataSetChanged();

    }

    /**这个方法用于创建ViewHolder实例，在这里将item布局加载进来，然后创建一个ViewHolder实例，
     * 并把加载进来的布局传到ViewHolder构造函数当中，最后将ViewHolder实例返回。*/
    @Override
    public Adapter_ProjectExperience.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_experience, parent, false);
        // 实例化viewholder
        Adapter_ProjectExperience.ViewHolder viewHolder = new Adapter_ProjectExperience.ViewHolder(v);
        return viewHolder;
    }

    /**这个方法是用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动屏幕内的时候执行，
     * 这里我们通过postion参数得到当前项的实例，然后将数据设置到ViewHolder的ImageView和TextView当中。*/
    @Override
    public void onBindViewHolder(final Adapter_ProjectExperience.ViewHolder holder, final int position) {
        // 绑定数据
        ProjectExperienceData ProjectExperienceData = mData.get(position); //通过数组分别创建每个数组元素的实例。
        holder.tv_projectname.setText(ProjectExperienceData.getProjectName());
        holder.tv_projecPosition.setText(ProjectExperienceData.getProjectRole());
        holder.tv_time1.setText(ProjectExperienceData.getProjectTime1());
        holder.tv_time2.setText(ProjectExperienceData.getProjectTime2());
        holder.tv_programIntroduction.setText(ProjectExperienceData.getProjectIntroduction());
        holder.tv_projectgrade.setText(ProjectExperienceData.getAchievementsInProject());
    }
    /**这个方法用于告诉RecyclerView一共有多少个项，然后直接返回长度。*/
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
    /**自己定义的一个内部类，继承自RecyclerView.ViewHolder（适配器名）*/
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_projectname,tv_projecPosition,tv_time1,tv_time2,tv_programIntroduction,tv_projectgrade;
        TagFlowLayout mFlowLayout;
        //内部类构造函数，需要传入一个View参数，这个参数就是RecyclerView子项的最外层布局
        public ViewHolder(View itemView) {
            super(itemView);
            tv_projectname = itemView.findViewById(R.id.tv_projectname);
            tv_projecPosition = itemView.findViewById(R.id.tv_projecPosition);
            tv_time1 = itemView.findViewById(R.id.tv_time1);
            tv_time2 = itemView.findViewById(R.id.tv_time2);
            tv_programIntroduction = itemView.findViewById(R.id.tv_programIntroduction);
            tv_projectgrade = itemView.findViewById(R.id.tv_projectgrade);
        }
    }
}
