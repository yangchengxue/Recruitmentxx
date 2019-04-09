package com.example.ycx36.recruitment.view.sonFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.view.activity.activity_ChangeTitle;
import com.example.ycx36.recruitment.view.activity.activity_TalentsTreatment;
import com.example.ycx36.recruitment.view.activity.activity_gxnuRules;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OfficeWork extends Fragment {

    private View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.officework, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @OnClick({R.id.l1,R.id.l2,R.id.l3,R.id.l4,R.id.l5,R.id.l6})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.l1:
                startActivity(new Intent(getActivity(),activity_gxnuRules.class));
                break;
            case R.id.l2:
                startActivity(new Intent(getActivity(),activity_gxnuRules.class));
                break;
            case R.id.l3:
                startActivity(new Intent(getActivity(),activity_TalentsTreatment.class));
                break;
            case R.id.l4:
                intentToAnoth("http://lc-apsilusi.cn-n1.lcfile.com/4aa6ce965a828f987c73/xiaoneidiaodong.jpg","校内调动");
                break;
            case R.id.l5:
                intentToAnoth("http://lc-apsilusi.cn-n1.lcfile.com/7413147ea5229b6e2fee/zhizipeixun.jpg","师资培训");
                break;
            case R.id.l6:
                intentToAnoth("http://lc-apsilusi.cn-n1.lcfile.com/b813b1659e74a861adc0/zhigai.png","职改流程");
                break;
        }
    }

    public void intentToAnoth(String newsUri,String newsTitle){
        Intent intent = new Intent(getActivity(), activity_ChangeTitle.class);
        Bundle bundle = new Bundle();      //创建一个budle对象
        bundle.putString("newsUri",newsUri);  //写入数据
        bundle.putString("newsTitle",newsTitle);  //写入数据
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
