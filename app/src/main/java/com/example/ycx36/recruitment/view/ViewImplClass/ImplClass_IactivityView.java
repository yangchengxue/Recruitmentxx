package com.example.ycx36.recruitment.view.ViewImplClass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.adapter.Adapter_Message;
import com.example.ycx36.recruitment.adapter.Adapter_PhotoText;
import com.example.ycx36.recruitment.adapter.Adapter_SetUserInfo;
import com.example.ycx36.recruitment.model.dataBean.Gson_BDPhotosBean;
import com.example.ycx36.recruitment.model.dataBean.SchoolSceneryText;
import com.example.ycx36.recruitment.model.dataBean.SetInfoDataItemBean;
import com.example.ycx36.recruitment.model.dataBean.SurroundingPlaceData;
import com.example.ycx36.recruitment.model.db.usereducationexperience;
import com.example.ycx36.recruitment.model.db.userinfo;
import com.example.ycx36.recruitment.model.db.userprojectexperience;
import com.example.ycx36.recruitment.model.db.users;
import com.example.ycx36.recruitment.model.db.userworkexperience;
import com.example.ycx36.recruitment.presenter.ActivityPresenter;
import com.example.ycx36.recruitment.util.GetBDPhotosRequest_Interface;
import com.example.ycx36.recruitment.view.activity.ContactUsActivity;
import com.example.ycx36.recruitment.view.activity.DetailIntroductionActivity;
import com.example.ycx36.recruitment.view.activity.FeedbackActivity;
import com.example.ycx36.recruitment.view.activity.FillResmeActivity;
import com.example.ycx36.recruitment.view.activity.Fill_InformationActivity;
import com.example.ycx36.recruitment.view.activity.IactivityView;
import com.example.ycx36.recruitment.view.activity.LoginActivity;
import com.example.ycx36.recruitment.view.activity.MainActivity;
import com.example.ycx36.recruitment.view.activity.MovieDetailsActivity;
import com.example.ycx36.recruitment.view.activity.MyResmeActivity;
import com.example.ycx36.recruitment.view.activity.PhotosShowActivity;
import com.example.ycx36.recruitment.view.activity.RegisterActivity;
import com.example.ycx36.recruitment.view.activity.SchoolMapActivity;
import com.example.ycx36.recruitment.view.activity.ShowBigPhotoActivity;
import com.example.ycx36.recruitment.view.activity.UserInfoActivity;
import com.example.ycx36.recruitment.view.activity.activity_aboutApp;
import com.example.ycx36.recruitment.view.sonFragment.ChatList;
import com.example.ycx36.recruitment.view.sonFragment.PrivateMessage;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.nightonke.boommenu.Animation.BoomEnum;
import com.nightonke.boommenu.Animation.OrderEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import org.feezu.liuli.timeselector.TimeSelector;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

public class ImplClass_IactivityView implements IactivityView{

    private ActivityPresenter activityPresenter = new ActivityPresenter(this);//中间者
    private Context context;

    public ImplClass_IactivityView(Context context){
        this.context = context;
    }


    private ArrayList<SchoolSceneryText> schoolSceneryTexts = new ArrayList<>();
    private Adapter_PhotoText adapter_photoText;
    private ArrayList<String> PhotoUrls = new ArrayList<>();
    @Override
    public void showRecyclerViewToPhotoShowActivity(final RecyclerView recycler) {
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        /*获取一个网格布局管理器（设置为瀑布流模式显示的时候用这个管理器）*/
                        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL); //3为指定布局的列数，VERTICAL为排列方式
                        recycler.setLayoutManager(layoutManager);  //将布局管理器设置到recyclerView中
                        recycler.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
                        adapter_photoText = new Adapter_PhotoText(schoolSceneryTexts); //获取适配器实例
                        recycler.setAdapter(adapter_photoText);
                        adapter_photoText.setOnItemClickListener(new Adapter_PhotoText.OnItemClickListener() {
                            @Override
                            public void setOnItemClickListener(View view, int position) {
                                Intent intent = new Intent(context, ShowBigPhotoActivity.class);
                                Bundle bundle = new Bundle();      //创建一个budle对象
                                bundle.putString("PhototTitle", adapter_photoText.getCurrentNameData(position));  //写入数据
                                bundle.putString("path", PhotoUrls.get(position));  //写入数据
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                            }
                        });

                        /**长按事件*/
                        adapter_photoText.setOnLongClickListener(new Adapter_PhotoText.OnLongClickListener() {
                            @Override
                            public void setOnLongClickListener(View view, final int position) {
                            }
                        });
                        break;
                }
            }
        };
        Retrofit retrofit2 = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())  //Gson解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://image.baidu.com")
                .build();
        GetBDPhotosRequest_Interface getBDPhotosRequest_interface = retrofit2.create(GetBDPhotosRequest_Interface.class);

        getBDPhotosRequest_interface.getBDPhotosData("0", "50", "壁纸", "全部", "utf8")
                .subscribeOn(Schedulers.io())  //观察者切换新线程,subscribe只能调用一次。
                .doOnNext(new Action1<Gson_BDPhotosBean>() {    //请求结束后调用 doOnNext(),并获得data数据
                    @Override
                    public void call(Gson_BDPhotosBean datas) {
                        for (int i=0; i <datas.getData().size()-1 ;i++){
                            String s0 = datas.getData().get(i).getAbs();
                            if (s0.length()>9){
                                s0 = s0.substring(0,9)+"…";
                            }
                            PhotoUrls.add(datas.getData().get(i).getImage_url());
                            SchoolSceneryText schoolSceneryText = new SchoolSceneryText(s0, GetDraweeControler(datas.getData().get(i).getImage_url()));
                            schoolSceneryTexts.add(schoolSceneryText);
                        }
                        Message message = new Message();  //创建一个message对象。并将它的what字段的值指定为UPDATA_TEXT
                        message.what = 1;
                        handler.sendMessage(message);     //handler去发送消息
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())   //被观察者切换到主线程
                .subscribe(new Action1<Gson_BDPhotosBean>() {    //观察者监听到datas数据,主线程中执行
                    @Override
                    public void call(Gson_BDPhotosBean datas) {

                    }
                });
    }

    /**返回一个Drawee控制器，用于加载图片*/
    public DraweeController GetDraweeControler(final String path){
        if (path != null ) {
            Uri uri = Uri.parse(path);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .build();
            DraweeController controller;
            controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .build();
            return controller;
        }else return null;

    }

    private ArrayList<SetInfoDataItemBean> itemlist1 = new ArrayList<>();
    @Override

    /**目前已弃用该方法，
     *
     * 仅暂时保留*/
    public void showRecyclerViewToUserInfoActivity(RecyclerView recycler) {
        final Adapter_SetUserInfo adapter;
        /**获取一个网格布局管理器（设置为瀑布流模式显示的时候用这个管理器）*/
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL); //3为指定布局的列数，VERTICAL为排列方式
        recycler.setLayoutManager(layoutManager);  //将布局管理器设置到recyclerView中
        recycler.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        recycler.setNestedScrollingEnabled(false);  //recyclerView设置禁止嵌套滑动
        adapter = new Adapter_SetUserInfo(activityPresenter.getShowSetUsrInfoItemData(itemlist1)); //获取适配器实例
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter_SetUserInfo.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                switch (position){
                    case 0:
                        IntentToFill_InformationActivity("真实姓名",R.string.ETname);
                        SharedPreferences pref = context.getSharedPreferences("userinfo",MODE_PRIVATE);
                        String value1 = pref.getString("uinfo","");  //后面的参数为如果找不到对应值，就返回什么样的默认值

                        break;
                    case 1:
                        TimeSelector timeSelector = new TimeSelector(context, new TimeSelector.ResultHandler() {
                            @Override
                            public void handle(String time) {
                                adapter.ChangeData(1,"出生年月",time.substring(0,10));
                            }
                        }, "1900-01-01 00:00", "2019-12-31 24:00");
                        timeSelector.setMode(TimeSelector.MODE.YMD);//只显示 年月日
                        timeSelector.show();
                        break;
                    case 2:
                        IntentToFill_InformationActivity("民族",R.string.ETnation);
                        break;
                    case 3:
                        showSelecter(adapter,3,new String[]{"男", "女"},"性别");
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10://婚姻状况
                        showSelecter(adapter,10,new String[]{"已婚", "未婚"},"婚姻状况");
                        break;
                }
            }
        });

        /**长按事件*/
        adapter.setOnLongClickListener(new Adapter_SetUserInfo.OnLongClickListener() {
            @Override
            public void setOnLongClickListener(View view, final int position) {
            }
        });
    }


    /**跳转到 填写详细信息 界面*/
    public void IntentToFill_InformationActivity(String title,int hintText){
        Intent intent = new Intent(context, Fill_InformationActivity.class);
        Bundle bundle = new Bundle();      //创建一个budle对象
        bundle.putString("title", title);  //写入数据
        intent.putExtras(bundle);             //用putExtras方法将写入的数据存入intent中
        bundle.putInt("hintText", hintText);  //写入数据
        intent.putExtras(bundle);             //用putExtras方法将写入的数据存入intent中
        context.startActivity(intent);
    }


    /**显示单项选择器*/
    public void showSelecter(final Adapter_SetUserInfo adapter, final int indexx, String[] items, final String title){
        OptionPicker picker = new OptionPicker((Activity) context,items);
        picker.setOffset(2);
        picker.setSelectedIndex(0); //从哪一项开始索引
        picker.setTextSize(17);
        picker.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//        picker.setCycleDisable(false);//启用循环
        picker.setLineSpaceMultiplier(3);//设置每项的高度，范围为2-4
        picker.setCanceledOnTouchOutside(false);//设置触摸弹窗外面是否自动关闭
//        picker.setDividerColor(R.color.color_red); //分割线颜色
//        picker.setShadowColor(); //设置选中项背景色
        picker.setContentPadding(5,5);  //设置内容边距
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                adapter.ChangeData(indexx,title,item);
            }
        });
        picker.show();
    }

    @Override
    public void setBoomMenuButton(BoomMenuButton bmb) {
        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);
        bmb.setBoomEnum(BoomEnum.HORIZONTAL_THROW_1);
        bmb.setOrderEnum(OrderEnum.RANDOM);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(activityPresenter.getMenuimagedata()[i])
                    .normalText(activityPresenter.getMenutextdata()[i])
                    .highlightedTextColor(R.color.gxnuColor)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index){
                                case 0:
                                    Intent intent0 = new Intent(context,PhotosShowActivity.class);
                                    context.startActivity(intent0);
                                    break;
                                case 1:
                                    Intent intent1 = new Intent(context,DetailIntroductionActivity.class);
                                    context.startActivity(intent1);
                                    break;
                                case 2:
                                    Intent intent2 = new Intent(context, SchoolMapActivity.class);
                                    context.startActivity(intent2);
                                    break;
                                case 3:
                                    Intent intent3 = new Intent(context, MyResmeActivity.class);
                                    context.startActivity(intent3);
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    Intent intent5 = new Intent(context, FillResmeActivity.class);
                                    context.startActivity(intent5);
                                    break;
                                case 6: //关于APP
                                    context.startActivity(new Intent(context,activity_aboutApp.class));
                                    break;
                                case 7:
                                    Intent intent7 = new Intent(context, ContactUsActivity.class);
                                    context.startActivity(intent7);
                                    break;
                                case 8:
                                    Intent intent8 = new Intent(context, FeedbackActivity.class);
                                    context.startActivity(intent8);
                                    break;
                            }
                        }
                    });
            bmb.addBuilder(builder);
        }
    }

    /**水滴状下拉刷新*/
    @Override
    public void showWaterDropFresh(final CircleRefreshLayout refresh_layout) {
        refresh_layout.setOnRefreshListener(
                new CircleRefreshLayout.OnCircleRefreshListener() {
                    @Override
                    public void refreshing() {
                        // do something when refresh starts
//                        adapter = new Adapter_PhotoText(activityPresenter.getShowPhotoAndText()); //获取适配器实例
//                        recyclerSchoolText.setAdapter(adapter);
                        new Thread(new Runnable(){
                            public void run(){
                                try {
                                    Thread.sleep(1000);
                                    refresh_layout.finishRefreshing();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }

                    @Override
                    public void completeRefresh() {
                        // do something when refresh complete
                    }
                });
    }

    @Override
    public void ChangeTitle(final TextView textView, String title) {
        Observable.just(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        textView.setText(s);
                    }
                });
    }

    @Override
    public void replaceFragment(int Flag, FragmentManager fragmentManager,Fragment fragment) {
        if (Flag == 1) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.mainFragment_layout, fragment);
            transaction.commit();
        }
        else if (Flag == 2){
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.messageFragment_layout, fragment);
            transaction.commit();
        }
    }

    /**替换fragment的方法，且不会销毁被替换的fragment的现有实例*/
    @Override
    public void showFragment(int index,FragmentManager fragmentManager,Fragment fragment1,Fragment fragment2) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
//                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);//添加动画
        switch (index) {
            case 1:
                if (!fragment1.isAdded()) {
                    ft.add(R.id.messageFragment_layout, fragment1).commit();
                } else {
                    ft.hide(fragment2).show(fragment1).commit();
                }
                break;
            case 2:
                if (!fragment2.isAdded()) {
                    ft.hide(fragment1).add(R.id.messageFragment_layout, fragment2).commit();
                } else {
                    ft.hide(fragment1).show(fragment2).commit();
                }
                break;
        }
    }



    @Override
    public void EditTextSet(EditText editText, final TextView RemainText, int hint, final String Remain) {
        editText.setHint(hint);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                RemainText.setText(s.length()+"/"+Remain);
            }
        });
    }

    @Override
    public void RegistrationUser(EditText et_userName,EditText et_userPassword,EditText et_userPhone) {
        final AVUser user = new AVUser();// 新建 AVUser 对象实例
        user.setUsername(et_userName.getText().toString());// 设置用户名
        user.setPassword(et_userPassword.getText().toString());// 设置密码
        user.setMobilePhoneNumber(et_userPhone.getText().toString());// 设置手机号
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    // 注册成功
                    userinfo userinfo = new userinfo();
                    userinfo.save();
                    userworkexperience userworkexperience = new userworkexperience();
                    userworkexperience.save();
                    List<userworkexperience> userworkexperienceList = new ArrayList<>();
                    userworkexperienceList.add(userworkexperience);
                    userprojectexperience userprojectexperience = new userprojectexperience();
                    userprojectexperience.save();
                    List<userprojectexperience> userprojectexperienceList = new ArrayList<>();
                    userprojectexperienceList.add(userprojectexperience);
                    usereducationexperience usereducationexperience = new usereducationexperience();
                    usereducationexperience.save();
                    List<usereducationexperience> usereducationexperienceList = new ArrayList<>();
                    usereducationexperienceList.add(usereducationexperience);
                    users u = new users();
                    u.setUserinfo(userinfo);
                    u.setUserworkexperience(userworkexperienceList);
                    u.setUserprojectexperience(userprojectexperienceList);
                    u.setUsereducationexperience(usereducationexperienceList);
                    u.setU_Id(user.getObjectId());
                    u.save();  //将用户id以及关联数据表存入本地数据库
                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    // 失败的原因
                    switch (e.getCode()){
                        case 202:
                            Toast.makeText(context, "注册失败：该昵称已被注册", Toast.LENGTH_SHORT).show();
                            break;
                        case 203:
                            Toast.makeText(context, "注册失败，该邮箱已被注册", Toast.LENGTH_SHORT).show();
                            break;
                        case 214:
                            Toast.makeText(context, "注册失败，该手机号已被注册", Toast.LENGTH_SHORT).show();
                            break;
                        case 218:
                            Toast.makeText(context, "注册失败，密码不能为空", Toast.LENGTH_SHORT).show();
                            break;
                        case 217:
                            Toast.makeText(context, "注册失败，用户昵称不能为空", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        });
    }
}
