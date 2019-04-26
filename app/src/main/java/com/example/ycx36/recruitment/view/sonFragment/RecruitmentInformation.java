package com.example.ycx36.recruitment.view.sonFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.adapter.Adapter_Message;
import com.example.ycx36.recruitment.adapter.Adapter_Position;
import com.example.ycx36.recruitment.model.dataBean.MessageDataBean;
import com.example.ycx36.recruitment.model.dataBean.PositionDataBean;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IactivityView;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IfragmentView;
import com.example.ycx36.recruitment.view.activity.activity_TalentsTreatment;
import com.example.ycx36.recruitment.view.fragment.IfragmentView;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**首页模块的招聘信息模块*/
public class RecruitmentInformation extends Fragment {

    @BindView(R.id.recyclerPosition) RecyclerView recyclerPosition;
    @BindView(R.id.refresh_layout) CircleRefreshLayout refresh_layout;

    Adapter_Position adapter_position;
    IfragmentView ifragmentView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recruitmentinformation, container, false);
        ButterKnife.bind(this, view);
        showWaterDropFresh(refresh_layout);  //模拟耗时
        ifragmentView = new ImplClass_IfragmentView(view.getContext());
        ifragmentView.showRecyclerViewToRecruitmentInformation(recyclerPosition,adapter_position);
        return view;
    }

    @OnClick({R.id.toTalentsTreatment,R.id.joblist})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.toTalentsTreatment:   //返回键
                startActivity(new Intent(getActivity(), activity_TalentsTreatment.class));
                break;
            case R.id.joblist:
                break;
        }
    }

    public void showWaterDropFresh(final CircleRefreshLayout refresh_layout) {
        refresh_layout.setOnRefreshListener(
                new CircleRefreshLayout.OnCircleRefreshListener() {
                    @Override
                    public void refreshing() {
                        new Thread(new Runnable(){
                            public void run(){
                                try {
                                    Thread.sleep(1000);
                                    refresh_layout.finishRefreshing();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }

                    @Override
                    public void completeRefresh() {
                        // do something when refresh complete
                        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(),"刷新成功",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
    }

}
