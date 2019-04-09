package com.example.ycx36.recruitment.model;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.model.dataBean.Gson_PoiDataBean;
import com.example.ycx36.recruitment.model.dataBean.MessageDataBean;
import com.example.ycx36.recruitment.model.dataBean.PositionDataBean;
import com.example.ycx36.recruitment.model.dataBean.SchoolSceneryText;
import com.example.ycx36.recruitment.model.dataBean.SetInfoDataItemBean;
import com.example.ycx36.recruitment.model.dataBean.SurroundingPlaceData;
import com.example.ycx36.recruitment.util.GetPoiRequest_Interface;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class AddDataToActivityImpl implements IaddDataToActivity {


    private ArrayList<SchoolSceneryText> datalist = new ArrayList<>();

    private ArrayList<SurroundingPlaceData> datalistToDiscover2 = new ArrayList<>();
    private ArrayList<MessageDataBean> datalistToMessage = new ArrayList<>();

    @Override
    public ArrayList<SchoolSceneryText> getNameAndPhotoPath() {
        SchoolSceneryText ycLibrary = new SchoolSceneryText("育才校区图书馆","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view1.jpg");
        datalist.add(ycLibrary);
        SchoolSceneryText ycJXL = new SchoolSceneryText("育才校区教学楼","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view4.jpg");
        datalist.add(ycJXL);
        SchoolSceneryText ycRXD = new SchoolSceneryText("育才校区人行道","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view7.jpg");
        datalist.add(ycRXD);
        SchoolSceneryText qnhua = new SchoolSceneryText("育才校区人行道","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view19.jpg");
        datalist.add(qnhua);
        SchoolSceneryText qhdx = new SchoolSceneryText("育才校区人行道","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view18.jpg");
        datalist.add(qhdx);
        return datalist;
    }


//    private Handler handler;
//    @SuppressLint("HandlerLeak")
//    public int[] xxxxx(){
//
////        final ArrayList<SurroundingPlaceData>[] datalistToDiscoverTest = {new ArrayList<>()};
//
//        handler = new Handler(){
//            public void handleMessage(Message msg){
//                switch (msg.what){
//                    case 1:
//                        int[] x = new int[1];
//                        //在这里可以进行UI操作
//                        Log.d("请求结束2    ", Thread.currentThread().getName() + "   " + datalistToDiscover.size());
////                        datalistToDiscoverTest = datalistToDiscover;
//                        x[0] = datalistToDiscover.size();
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
//        return x;
//    }

    private ArrayList<SurroundingPlaceData> datalistToDiscover = new ArrayList<>();
    @Override
    public ArrayList<SurroundingPlaceData> getPlaceNameAndPhotoPath() {
        SurroundingPlaceData ycLibrary = new SurroundingPlaceData("鑫海国际电影院","约1.9km","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view1.jpg");
        datalistToDiscover.add(ycLibrary);
        SurroundingPlaceData ycJXL = new SurroundingPlaceData("十字街","约1.4km","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view4.jpg");
        datalistToDiscover.add(ycJXL);
        SurroundingPlaceData ycRXD = new SurroundingPlaceData("东西巷","约1.5km","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view7.jpg");
        datalistToDiscover.add(ycRXD);
        SurroundingPlaceData qnhua = new SurroundingPlaceData("万达广场","约2.5km","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view19.jpg");
        datalistToDiscover.add(qnhua);
        SurroundingPlaceData qhdx = new SurroundingPlaceData("育才校区人行道","约0.5km","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view18.jpg");
        datalistToDiscover.add(qhdx);
        return datalistToDiscover;
    }

    @Override
    public ArrayList<SurroundingPlaceData> getThingAndPhotoPath() {
        SurroundingPlaceData ycLibrary = new SurroundingPlaceData("鑫海国际电影院","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view1.jpg");
        datalistToDiscover2.add(ycLibrary);
        SurroundingPlaceData ycJXL = new SurroundingPlaceData("十字街","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view4.jpg");
        datalistToDiscover2.add(ycJXL);
        SurroundingPlaceData ycRXD = new SurroundingPlaceData("东西巷","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view7.jpg");
        datalistToDiscover2.add(ycRXD);
        SurroundingPlaceData qnhua = new SurroundingPlaceData("万达广场","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view19.jpg");
        datalistToDiscover2.add(qnhua);
        SurroundingPlaceData qhdx = new SurroundingPlaceData("育才校区人行道","http://www.tsinghua.edu.cn/publish/newthu/newthu_cnt/intothu/picture/view18.jpg");
        datalistToDiscover2.add(qhdx);
        return datalistToDiscover2;
    }


    @Override
    public ArrayList<MessageDataBean> getMessageData() {
        MessageDataBean data1 = new MessageDataBean("ycx","我今天迷路了","7-10","http://www.suda.edu.cn/uploadfiles/201107/20110712153128232.png");
        datalistToMessage.add(data1);
        MessageDataBean data2 = new MessageDataBean("yyx","hell，你好吃饭了吗？","7-10","http://www.suda.edu.cn/uploadfiles/201107/20110712152815997.jpg");
        datalistToMessage.add(data2);
        MessageDataBean data3 = new MessageDataBean("Tom","一招致命","7-10","http://www.suda.edu.cn/uploadfiles/201107/20110712152749774.png");
        datalistToMessage.add(data3);
        MessageDataBean data4 = new MessageDataBean("Jerry","不用谢。。","7-10","http://www.suda.edu.cn/uploadfiles/201107/20110712152723891.png");
        datalistToMessage.add(data4);
        MessageDataBean data5 = new MessageDataBean("莱昂纳多","今天拿了三双","7-10","http://www.suda.edu.cn/uploadfiles/201107/20110712152630895.png");
        datalistToMessage.add(data5);
        MessageDataBean data6 = new MessageDataBean("阿尔德里奇","http://www.suda.edu.cn/uploadfiles/201107/20110712152447693.jpg","7-10","http://www.suda.edu.cn/uploadfiles/201107/20110712152541819.png");
        datalistToMessage.add(data6);
        MessageDataBean data7 = new MessageDataBean("乔丹","明天加油动起来","7-10","http://www.suda.edu.cn/uploadfiles/201107/20110712152447693.jpg");
        datalistToMessage.add(data7);
        return datalistToMessage;
    }

    private ArrayList<PositionDataBean> datalistToPosition = new ArrayList<>();
    @Override
    public ArrayList<PositionDataBean> getPositionData() {
        PositionDataBean d1 = new PositionDataBean("英语教师",5,"育才校区","电子工程学院");
        datalistToPosition.add(d1);
        PositionDataBean d2 = new PositionDataBean("语文教师",2,"雁山校区","马克思主义学院");
        datalistToPosition.add(d2);
        PositionDataBean d3 = new PositionDataBean("数学教师",3,"王城校区","旅游与管理学院");
        datalistToPosition.add(d3);
        PositionDataBean d4 = new PositionDataBean("C语言教师",5,"育才校区","电子工程学院");
        datalistToPosition.add(d4);
        PositionDataBean d5 = new PositionDataBean("Java教师",2,"雁山校区","电子工程学院");
        datalistToPosition.add(d5);
        PositionDataBean d6 = new PositionDataBean("单片机教师",3,"王城校区","电子工程学院");
        datalistToPosition.add(d6);
        PositionDataBean d7 = new PositionDataBean("英语教师",5,"育才校区","电子工程学院");
        datalistToPosition.add(d7);
        PositionDataBean d8 = new PositionDataBean("语文教师",2,"雁山校区","马克思主义学院");
        datalistToPosition.add(d8);
        PositionDataBean d9 = new PositionDataBean("数学教师",3,"王城校区","旅游与管理学院");
        datalistToPosition.add(d9);
        return datalistToPosition;
    }

    private ArrayList<SetInfoDataItemBean> datalistToSetInfoDataItem = new ArrayList<>();
    @Override
    public ArrayList<SetInfoDataItemBean> getSetInfoDataItemData() {
        SetInfoDataItemBean d1 = new SetInfoDataItemBean("真实姓名","未填写");
        datalistToSetInfoDataItem.add(d1);
        SetInfoDataItemBean d2 = new SetInfoDataItemBean("出生年月","未填写");
        datalistToSetInfoDataItem.add(d2);
        SetInfoDataItemBean d3 = new SetInfoDataItemBean("民族","未填写");
        datalistToSetInfoDataItem.add(d3);
        SetInfoDataItemBean d4 = new SetInfoDataItemBean("性别","未填写");
        datalistToSetInfoDataItem.add(d4);
        SetInfoDataItemBean d5 = new SetInfoDataItemBean("职称","未填写");
        datalistToSetInfoDataItem.add(d5);
        SetInfoDataItemBean d6 = new SetInfoDataItemBean("籍贯","未填写");
        datalistToSetInfoDataItem.add(d6);
        SetInfoDataItemBean d7 = new SetInfoDataItemBean("最高学历","未填写");
        datalistToSetInfoDataItem.add(d7);
        SetInfoDataItemBean d8 = new SetInfoDataItemBean("最高学位","未填写");
        datalistToSetInfoDataItem.add(d8);
        SetInfoDataItemBean d9 = new SetInfoDataItemBean("政治面貌","未填写");
        datalistToSetInfoDataItem.add(d9);
        SetInfoDataItemBean d10 = new SetInfoDataItemBean("毕业院校","未填写");
        datalistToSetInfoDataItem.add(d10);
        SetInfoDataItemBean d11 = new SetInfoDataItemBean("婚姻状况","未填写");
        datalistToSetInfoDataItem.add(d11);
        SetInfoDataItemBean d12 = new SetInfoDataItemBean("联系电话","未填写");
        datalistToSetInfoDataItem.add(d12);
        SetInfoDataItemBean d13 = new SetInfoDataItemBean("有无既往病史","未填写");
        datalistToSetInfoDataItem.add(d13);
        SetInfoDataItemBean d14 = new SetInfoDataItemBean("通讯地址","未填写");
        datalistToSetInfoDataItem.add(d14);
        SetInfoDataItemBean d15 = new SetInfoDataItemBean("邮政编码","未填写");
        datalistToSetInfoDataItem.add(d15);
        return datalistToSetInfoDataItem;
    }


    String[] menuText;
    @Override
    public String[] getMenuTextData() {
        menuText = new String[]{"师大风光","师大简介","师大地图","人才招聘","办公办事","简历投递","关于APP","联系我们","反馈留言"};
        return menuText;
    }

    int[] menuImage;
    @Override
    public int[] getMenuImageData() {
        menuImage = new int[]{R.drawable.schoolsign,R.drawable.schooljianjie,R.drawable.schoolmap,R.drawable.person,R.drawable.makework,R.drawable.jianlitoudi,R.drawable.aboutapp,R.drawable.communityus,R.drawable.fankuiliuyan};
        return menuImage;
    }
}
