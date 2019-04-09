package com.example.ycx36.recruitment.presenter;

import android.support.v4.app.Fragment;

import com.example.ycx36.recruitment.model.FragmentModelImpl;
import com.example.ycx36.recruitment.model.IaddDataToFragment;
import com.example.ycx36.recruitment.view.fragment.IfragmentView;

public class FragmentPresenter {
    //持有两个接口
    IaddDataToFragment iaddDataToFragment;
    IfragmentView ifragmentView;

    public FragmentPresenter(IfragmentView ifragmentView){
        this.ifragmentView = ifragmentView;
        this.iaddDataToFragment = new FragmentModelImpl();
    }

    /**执行以下两个方法，获取Discover碎片标题数据*/
    public int[] getDiscoverTitle(){
        return FragmentModelImpl.DiscoverTitle;
    }
    public Class<? extends Fragment>[] getDiscoverClazz(){
        return FragmentModelImpl.DiscoverClazz;
    }

    /**获取HomePage碎片标题数据*/
    public int[] getHomePageTitle(){
        return FragmentModelImpl.HomePageTitle;
    }
    public Class<? extends Fragment>[] getHomePageClazz(){
        return FragmentModelImpl.HomePageClazz;
    }

    /**获取Message碎片标题数据*/
    public int[] getMessageTitle(){
        return FragmentModelImpl.MessageTitle;
    }
    public Class<? extends Fragment>[] getMessageClazz(){
        return FragmentModelImpl.MessageClazz;
    }
    /**获取SchoolMap碎片标题数据*/
    public int[] getMapTitle(){
        return FragmentModelImpl.MapTitle;
    }
    public Class<? extends Fragment>[] getMapClazz(){
        return FragmentModelImpl.MapClazz;
    }

}
