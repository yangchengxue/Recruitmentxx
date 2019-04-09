package com.example.ycx36.recruitment.view.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IfragmentView;
import com.example.ycx36.recruitment.view.fragment.IfragmentView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SchoolMapActivity extends AppCompatActivity {

    @BindView( R.id.SchoolMapViewpager ) public ViewPager SchoolMapViewpager;
    @BindView( R.id.SchoolMapViewpagertab ) public SmartTabLayout SchoolMapViewpagertab;

    private IfragmentView ifragmentView;
    FragmentPagerItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_map);
        ButterKnife.bind(this);
        ifragmentView = new ImplClass_IfragmentView(this);
        ifragmentView.showTopNavigation(4,this,adapter,SchoolMapViewpager,SchoolMapViewpagertab);
    }

    @OnClick({R.id.tv_header_back,R.id.tv_header_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回键
                finish();
                break;
            case R.id.tv_header_right:  //顶部栏右边的显示菜单
                break;

        }
    }
}
