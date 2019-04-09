package com.example.ycx36.recruitment.view.sonFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ycx36.recruitment.R;

import butterknife.ButterKnife;

/**首页模块的招聘答疑模块*/
public class RecruitmentAnswer extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recruitmentanswer, container, false);
        ButterKnife.bind(this ,view) ;
        return view;
    }
}
