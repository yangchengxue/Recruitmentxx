package com.example.ycx36.recruitment.view.ViewImplClass;

import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.ycx36.recruitment.view.ICommonView;

public class ImplClass_ICommonView implements ICommonView{

    private Context context;

    public ImplClass_ICommonView(Context context){
        this.context = context;
    }
    @Override
    public void setCardView(CardView cardView) {
        cardView.setRadius(20);//设置图片圆角的半径大小
        cardView.setCardElevation(20);//设置阴影部分大小
        cardView.setContentPadding(5,5,5,5);//设置图片距离阴影大小
    }

    @Override
    public void returnMsg(String msgRecevied) {

    }
}
