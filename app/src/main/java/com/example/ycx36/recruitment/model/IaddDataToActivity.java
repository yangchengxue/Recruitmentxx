package com.example.ycx36.recruitment.model;

import com.example.ycx36.recruitment.model.dataBean.MessageDataBean;
import com.example.ycx36.recruitment.model.dataBean.PositionDataBean;
import com.example.ycx36.recruitment.model.dataBean.SchoolSceneryText;
import com.example.ycx36.recruitment.model.dataBean.SetInfoDataItemBean;
import com.example.ycx36.recruitment.model.dataBean.SurroundingPlaceData;

import java.util.ArrayList;

public interface IaddDataToActivity {
    ArrayList<SchoolSceneryText> getNameAndPhotoPath(); //获取名字和图片的地址

    ArrayList<SurroundingPlaceData> getPlaceNameAndPhotoPath();  //周边模块的水平滑动RecyclerView数据

    ArrayList<SurroundingPlaceData> getThingAndPhotoPath();  //周边模块的垂直滑动RecyclerView数据

    ArrayList<MessageDataBean> getMessageData();  //私信模块的相关数据

    ArrayList<PositionDataBean> getPositionData();  //招聘信息的相关数据

    ArrayList<SetInfoDataItemBean> getSetInfoDataItemData();  //设置用户信息的item的相关数据



    String[] getMenuTextData();

    int[] getMenuImageData();

}
