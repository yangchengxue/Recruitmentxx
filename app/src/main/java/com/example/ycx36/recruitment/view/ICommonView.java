package com.example.ycx36.recruitment.view;

import android.support.v7.widget.CardView;

import com.example.ycx36.recruitment.model.dataBean.Msg;

public interface ICommonView {
    void setCardView(CardView cardView);  //设置卡片布局属性

    void returnMsg(String msgRecevied);
}
