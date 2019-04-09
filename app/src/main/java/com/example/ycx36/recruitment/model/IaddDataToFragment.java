package com.example.ycx36.recruitment.model;

import android.support.v4.app.Fragment;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.view.sonFragment.Notice;
import com.example.ycx36.recruitment.view.sonFragment.OfficeWork;
import com.example.ycx36.recruitment.view.sonFragment.PrivateMessage;
import com.example.ycx36.recruitment.view.sonFragment.RecruitmentAnswer;
import com.example.ycx36.recruitment.view.sonFragment.RecruitmentInformation;
import com.example.ycx36.recruitment.view.sonFragment.SchoolSummarized;
import com.example.ycx36.recruitment.view.sonFragment.SurroundingThings;
import com.example.ycx36.recruitment.view.sonFragment.WangChengMap;
import com.example.ycx36.recruitment.view.sonFragment.YanShanMap;
import com.example.ycx36.recruitment.view.sonFragment.YuCaiMap;

public interface IaddDataToFragment {

    int[] DiscoverTitle = {R.string.title_OfficeWork,R.string.title_SurroundingThings};
    Class<? extends Fragment>[] DiscoverClazz = new Class[]{OfficeWork.class, SurroundingThings.class};

    int[] HomePageTitle = {R.string.title_SchoolSummarized,R.string.title_RecruitmentInformation,R.string.title_RecruitmentAnswer};
    Class<? extends Fragment>[] HomePageClazz = new Class[]{SchoolSummarized.class, RecruitmentInformation.class, RecruitmentAnswer.class};

    int[] MessageTitle = {R.string.title_Notice,R.string.title_PrivateMessage};
    Class<? extends Fragment>[] MessageClazz = new Class[]{Notice.class, PrivateMessage.class};

    int[] MapTitle = {R.string.title_yanshan_Map,R.string.title_yucai_Map,R.string.title_wangcheng_Map};
    Class<? extends Fragment>[] MapClazz = new Class[]{YanShanMap.class, YuCaiMap.class , WangChengMap.class};


    void getStringData();
}
