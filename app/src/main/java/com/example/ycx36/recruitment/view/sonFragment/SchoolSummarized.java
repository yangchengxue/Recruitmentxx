package com.example.ycx36.recruitment.view.sonFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.view.ICommonView;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_ICommonView;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IfragmentView;
import com.example.ycx36.recruitment.view.activity.DetailIntroductionActivity;
import com.example.ycx36.recruitment.view.activity.ShowBigPhotoActivity;
import com.example.ycx36.recruitment.view.fragment.IfragmentView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

/**首页模块的学校概括模块*/
public class SchoolSummarized extends Fragment{

    @BindView( R.id.banner_guide_content ) public  BGABanner mContentBanner;
    @BindView( R.id.cardView1 ) public CardView cardView1;
    private IfragmentView ifragmentView;
    private ICommonView iCommonView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schoolsummarized, container, false);
        ButterKnife.bind( this ,view);
        ifragmentView = new ImplClass_IfragmentView(view.getContext());
        iCommonView = new ImplClass_ICommonView(view.getContext());
        iCommonView.setCardView(cardView1);
        ifragmentView.setBanner(mContentBanner);
        return view;
    }

    @OnClick({R.id.cardView1})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.cardView1:
                Intent intent = new Intent(getActivity(), DetailIntroductionActivity.class);
                startActivity(intent);
                break;

        }
    }
}
