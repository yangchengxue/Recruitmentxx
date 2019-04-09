package com.example.ycx36.recruitment.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IfragmentView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**消息碎片*/
public class Message extends Fragment{

    @BindView( R.id.MessageViewpager ) public ViewPager MessageViewpager;
    @BindView( R.id.MessageViewpagertab ) public SmartTabLayout MessageViewpagertab;

    private IfragmentView ifragmentView;
    private View view ;
    FragmentPagerItemAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.message_layout, container, false);
            ButterKnife.bind(this, view);
            ifragmentView = new ImplClass_IfragmentView(view.getContext());
            ifragmentView.showTopNavigation(3,getActivity(),adapter,MessageViewpager,MessageViewpagertab);
        }
        return view;
    }

}
