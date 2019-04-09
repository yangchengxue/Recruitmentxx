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

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.adapter.Adapter_Message;
import com.example.ycx36.recruitment.adapter.Adapter_Position;
import com.example.ycx36.recruitment.model.dataBean.MessageDataBean;
import com.example.ycx36.recruitment.model.dataBean.PositionDataBean;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IfragmentView;
import com.example.ycx36.recruitment.view.fragment.IfragmentView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**首页模块的招聘信息模块*/
public class RecruitmentInformation extends Fragment {

    @BindView(R.id.recyclerPosition) RecyclerView recyclerPosition;

    Adapter_Position adapter_position;
    IfragmentView ifragmentView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recruitmentinformation, container, false);
        ButterKnife.bind(this, view);
        ifragmentView = new ImplClass_IfragmentView(view.getContext());
        ifragmentView.showRecyclerViewToRecruitmentInformation(recyclerPosition,adapter_position);
        return view;
    }

}
