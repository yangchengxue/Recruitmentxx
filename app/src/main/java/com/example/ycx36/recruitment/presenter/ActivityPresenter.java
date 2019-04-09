package com.example.ycx36.recruitment.presenter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.ycx36.recruitment.adapter.SurroundingThingAdapter;
import com.example.ycx36.recruitment.model.AddDataToActivityImpl;
import com.example.ycx36.recruitment.model.IaddDataToActivity;
import com.example.ycx36.recruitment.model.dataBean.Gson_PoiDataBean;
import com.example.ycx36.recruitment.model.dataBean.MessageDataBean;
import com.example.ycx36.recruitment.model.dataBean.PositionDataBean;
import com.example.ycx36.recruitment.model.dataBean.SchoolSceneryText;
import com.example.ycx36.recruitment.model.dataBean.SetInfoDataItemBean;
import com.example.ycx36.recruitment.model.dataBean.SurroundingPlaceData;

import com.example.ycx36.recruitment.util.GetPoiRequest_Interface;
import com.example.ycx36.recruitment.view.activity.IactivityView;
import com.example.ycx36.recruitment.view.fragment.IfragmentView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ActivityPresenter {

    private ArrayList<SchoolSceneryText> datalist;

    private ArrayList<SurroundingPlaceData> datalist3;
    private ArrayList<MessageDataBean> datalist4;
    private IaddDataToActivity iaddDataToActivity;
    private IactivityView iactivityView;
    private IfragmentView ifragmentView;

    //构造函数1-通过activity的View接口进行构造
    public ActivityPresenter(IactivityView view){
        iactivityView = view;
        iaddDataToActivity = new AddDataToActivityImpl();
    }

    //构造函数2-通过fragment的View接口进行构造
    public ActivityPresenter(IfragmentView view){
        ifragmentView = view;
        iaddDataToActivity = new AddDataToActivityImpl();
    }

    //构造函数3
    public ActivityPresenter(){
        iaddDataToActivity = new AddDataToActivityImpl();
    }


    private String[] menutextData;
    /**获取菜单按钮的文本数据*/
    public String[] getMenutextdata(){
        menutextData = iaddDataToActivity.getMenuTextData();
        return menutextData;
    }
    private int[] menuimageData;
    /**获取菜单按钮的图片数据*/
    public int[] getMenuimagedata(){
        menuimageData = iaddDataToActivity.getMenuImageData();
        return menuimageData;
    }

    /**获取学校风景的图片和名字数据*/
    public ArrayList<SchoolSceneryText> getShowPhotoAndText(ArrayList<SchoolSceneryText> itemlist){
        datalist = iaddDataToActivity.getNameAndPhotoPath();
        SchoolSceneryText ycLibrary = new SchoolSceneryText(datalist.get(0).getName(),GetDraweeControler(datalist.get(0).getimageUri()));
        itemlist.add(ycLibrary);
        SchoolSceneryText ycJXL = new SchoolSceneryText(datalist.get(1).getName(),GetDraweeControler(datalist.get(1).getimageUri()));
        itemlist.add(ycJXL);
        SchoolSceneryText ycRXD = new SchoolSceneryText(datalist.get(2).getName(),GetDraweeControler(datalist.get(2).getimageUri()));
        itemlist.add(ycRXD);
        SchoolSceneryText qh1 = new SchoolSceneryText(datalist.get(3).getName(),GetDraweeControler(datalist.get(3).getimageUri()));
        itemlist.add(qh1);
        SchoolSceneryText qh2 = new SchoolSceneryText(datalist.get(4).getName(),GetDraweeControler(datalist.get(4).getimageUri()));
        itemlist.add(qh2);
        return itemlist;
    }


    private ArrayList<SetInfoDataItemBean> datalist6;
    /**获取设置用户信息界面的item 的显示数据*/
    public ArrayList<SetInfoDataItemBean> getShowSetUsrInfoItemData(ArrayList<SetInfoDataItemBean> itemlist){
        datalist6 = iaddDataToActivity.getSetInfoDataItemData();
        SetInfoDataItemBean d1 = new SetInfoDataItemBean(datalist6.get(0).getItemName(),datalist6.get(0).getItemRightText());
        itemlist.add(d1);
        SetInfoDataItemBean d2 = new SetInfoDataItemBean(datalist6.get(1).getItemName(),datalist6.get(1).getItemRightText());
        itemlist.add(d2);
        SetInfoDataItemBean d3 = new SetInfoDataItemBean(datalist6.get(2).getItemName(),datalist6.get(2).getItemRightText());
        itemlist.add(d3);
        SetInfoDataItemBean d4 = new SetInfoDataItemBean(datalist6.get(3).getItemName(),datalist6.get(3).getItemRightText());
        itemlist.add(d4);
        SetInfoDataItemBean d5 = new SetInfoDataItemBean(datalist6.get(4).getItemName(),datalist6.get(4).getItemRightText());
        itemlist.add(d5);
        SetInfoDataItemBean d6 = new SetInfoDataItemBean(datalist6.get(5).getItemName(),datalist6.get(5).getItemRightText());
        itemlist.add(d6);
        SetInfoDataItemBean d7 = new SetInfoDataItemBean(datalist6.get(6).getItemName(),datalist6.get(6).getItemRightText());
        itemlist.add(d7);
        SetInfoDataItemBean d8 = new SetInfoDataItemBean(datalist6.get(7).getItemName(),datalist6.get(7).getItemRightText());
        itemlist.add(d8);
        SetInfoDataItemBean d9 = new SetInfoDataItemBean(datalist6.get(8).getItemName(),datalist6.get(8).getItemRightText());
        itemlist.add(d9);
        SetInfoDataItemBean d10 = new SetInfoDataItemBean(datalist6.get(9).getItemName(),datalist6.get(9).getItemRightText());
        itemlist.add(d10);
        SetInfoDataItemBean d11 = new SetInfoDataItemBean(datalist6.get(10).getItemName(),datalist6.get(10).getItemRightText());
        itemlist.add(d11);
        SetInfoDataItemBean d12 = new SetInfoDataItemBean(datalist6.get(11).getItemName(),datalist6.get(11).getItemRightText());
        itemlist.add(d12);
        return itemlist;
    }


    /**获取附近地地点的图片和名字数据和距离数据*/
    public ArrayList<SurroundingPlaceData> getShowPlaceAndPhoto(final ArrayList<SurroundingPlaceData> itemlist){
        final ArrayList<SurroundingPlaceData> datalist2 = new ArrayList<>();
//        datalist2 = iaddDataToActivity.getPlaceNameAndPhotoPath();
//        SurroundingPlaceData x1 = new SurroundingPlaceData(datalist2.get(0).getName(),datalist2.get(0).getDistance(),GetDraweeControler(datalist2.get(0).getImageUri()));
//        itemlist.add(x1);
//        SurroundingPlaceData x2 = new SurroundingPlaceData(datalist2.get(1).getName(),datalist2.get(1).getDistance(),GetDraweeControler(datalist2.get(1).getImageUri()));
//        itemlist.add(x2);
//        SurroundingPlaceData x3 = new SurroundingPlaceData(datalist2.get(2).getName(),datalist2.get(2).getDistance(),GetDraweeControler(datalist2.get(2).getImageUri()));
//        itemlist.add(x3);
//        SurroundingPlaceData x4 = new SurroundingPlaceData(datalist2.get(3).getName(),datalist2.get(3).getDistance(),GetDraweeControler(datalist2.get(3).getImageUri()));
//        itemlist.add(x4);
//        SurroundingPlaceData x5 = new SurroundingPlaceData(datalist2.get(4).getName(),datalist2.get(4).getDistance(),GetDraweeControler(datalist2.get(4).getImageUri()));
//        itemlist.add(x5);

        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 1:
                        for (int i = 0; i<datalist2.size() ;i++){
                            SurroundingPlaceData x1 = new SurroundingPlaceData(datalist2.get(i).getName(),datalist2.get(i).getDistance(),GetDraweeControler(datalist2.get(i).getImageUri()));
                            itemlist.add(x1);
                            Log.d("请求结束2    ", Thread.currentThread().getName() + "   " + datalist2.size());
                        }
                        break;
                    default:
                        break;
                }
            }
        };


        return itemlist;
    }

    /**获取周边事件的图片和描述数据*/
    public ArrayList<SurroundingPlaceData> getShowThingAndPhoto(ArrayList<SurroundingPlaceData> itemlist){
        datalist3 = iaddDataToActivity.getThingAndPhotoPath();
        SurroundingPlaceData x1 = new SurroundingPlaceData(datalist3.get(0).getName(),GetDraweeControler(datalist3.get(0).getImageUri()));
        itemlist.add(x1);
        SurroundingPlaceData x2 = new SurroundingPlaceData(datalist3.get(1).getName(),GetDraweeControler(datalist3.get(1).getImageUri()));
        itemlist.add(x2);
        SurroundingPlaceData x3 = new SurroundingPlaceData(datalist3.get(2).getName(),GetDraweeControler(datalist3.get(2).getImageUri()));
        itemlist.add(x3);
        SurroundingPlaceData x4 = new SurroundingPlaceData(datalist3.get(3).getName(),GetDraweeControler(datalist3.get(3).getImageUri()));
        itemlist.add(x4);
        SurroundingPlaceData x5 = new SurroundingPlaceData(datalist3.get(4).getName(),GetDraweeControler(datalist3.get(4).getImageUri()));
        itemlist.add(x5);
        return itemlist;
    }

    /**获取私信模块数据*/
    public ArrayList<MessageDataBean> getShowMessageData(ArrayList<MessageDataBean> itemlist){
        datalist4 = iaddDataToActivity.getMessageData();
        MessageDataBean x1 = new MessageDataBean(datalist4.get(0).getUserName(),datalist4.get(0).getMessage(),datalist4.get(0).getMessageDate(),GetDraweeControler(datalist4.get(0).getImageUri()));
        itemlist.add(x1);
        MessageDataBean x2 = new MessageDataBean(datalist4.get(1).getUserName(),datalist4.get(1).getMessage(),datalist4.get(1).getMessageDate(),GetDraweeControler(datalist4.get(1).getImageUri()));
        itemlist.add(x2);
        MessageDataBean x3 = new MessageDataBean(datalist4.get(2).getUserName(),datalist4.get(2).getMessage(),datalist4.get(2).getMessageDate(),GetDraweeControler(datalist4.get(2).getImageUri()));
        itemlist.add(x3);
        MessageDataBean x4 = new MessageDataBean(datalist4.get(3).getUserName(),datalist4.get(3).getMessage(),datalist4.get(3).getMessageDate(),GetDraweeControler(datalist4.get(3).getImageUri()));
        itemlist.add(x4);
        MessageDataBean x5 = new MessageDataBean(datalist4.get(4).getUserName(),datalist4.get(4).getMessage(),datalist4.get(4).getMessageDate(),GetDraweeControler(datalist4.get(4).getImageUri()));
        itemlist.add(x5);
        MessageDataBean x6 = new MessageDataBean(datalist4.get(5).getUserName(),datalist4.get(5).getMessage(),datalist4.get(5).getMessageDate(),GetDraweeControler(datalist4.get(5).getImageUri()));
        itemlist.add(x6);
        MessageDataBean x7 = new MessageDataBean(datalist4.get(6).getUserName(),datalist4.get(6).getMessage(),datalist4.get(6).getMessageDate(),GetDraweeControler(datalist4.get(6).getImageUri()));
        itemlist.add(x7);
        return itemlist;
    }

    private ArrayList<PositionDataBean> datalist5;
    public ArrayList<PositionDataBean> getShowPositionData(ArrayList<PositionDataBean> itemlist){
        datalist5 = iaddDataToActivity.getPositionData();
        PositionDataBean d1 = new PositionDataBean(datalist5.get(0).getPositionName(),datalist5.get(0).getPersonNumber(),datalist5.get(0).getSite(),datalist5.get(0).getCollege());
        itemlist.add(d1);
        PositionDataBean d2 = new PositionDataBean(datalist5.get(1).getPositionName(),datalist5.get(1).getPersonNumber(),datalist5.get(1).getSite(),datalist5.get(1).getCollege());
        itemlist.add(d2);
        PositionDataBean d3 = new PositionDataBean(datalist5.get(2).getPositionName(),datalist5.get(2).getPersonNumber(),datalist5.get(2).getSite(),datalist5.get(2).getCollege());
        itemlist.add(d3);
        PositionDataBean d4 = new PositionDataBean(datalist5.get(3).getPositionName(),datalist5.get(3).getPersonNumber(),datalist5.get(3).getSite(),datalist5.get(3).getCollege());
        itemlist.add(d4);
        PositionDataBean d5 = new PositionDataBean(datalist5.get(4).getPositionName(),datalist5.get(4).getPersonNumber(),datalist5.get(4).getSite(),datalist5.get(4).getCollege());
        itemlist.add(d5);
        PositionDataBean d6 = new PositionDataBean(datalist5.get(5).getPositionName(),datalist5.get(5).getPersonNumber(),datalist5.get(5).getSite(),datalist5.get(5).getCollege());
        itemlist.add(d6);
        PositionDataBean d7 = new PositionDataBean(datalist5.get(6).getPositionName(),datalist5.get(6).getPersonNumber(),datalist5.get(6).getSite(),datalist5.get(6).getCollege());
        itemlist.add(d7);
        PositionDataBean d8 = new PositionDataBean(datalist5.get(7).getPositionName(),datalist5.get(7).getPersonNumber(),datalist5.get(7).getSite(),datalist5.get(7).getCollege());
        itemlist.add(d8);
        PositionDataBean d9 = new PositionDataBean(datalist5.get(8).getPositionName(),datalist5.get(8).getPersonNumber(),datalist5.get(8).getSite(),datalist5.get(8).getCollege());
        itemlist.add(d9);
        return itemlist;
    }


    private Uri uri;
    private ImageRequest request;
    private DraweeController controller;
    /**返回一个Drawee控制器，用于加载图片*/
    public DraweeController GetDraweeControler(final String path){
        uri = Uri.parse(path);
        request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setLocalThumbnailPreviewsEnabled(true)
                .build();
        controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .build();
        return controller;
    }

}
