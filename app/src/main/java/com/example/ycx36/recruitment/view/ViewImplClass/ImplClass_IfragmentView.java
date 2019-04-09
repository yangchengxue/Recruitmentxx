package com.example.ycx36.recruitment.view.ViewImplClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.adapter.Adapter_Message;
import com.example.ycx36.recruitment.adapter.Adapter_Position;
import com.example.ycx36.recruitment.adapter.SurroundindPlaceAdapter;
import com.example.ycx36.recruitment.adapter.SurroundingThingAdapter;
import com.example.ycx36.recruitment.model.dataBean.Gson_BDPhotosBean;
import com.example.ycx36.recruitment.model.dataBean.Gson_NewsDataBean;
import com.example.ycx36.recruitment.model.dataBean.Gson_OnShowMoviesData;
import com.example.ycx36.recruitment.model.dataBean.Gson_PoiDataBean;
import com.example.ycx36.recruitment.model.dataBean.MessageDataBean;
import com.example.ycx36.recruitment.model.dataBean.PositionDataBean;
import com.example.ycx36.recruitment.model.dataBean.SurroundingPlaceData;
import com.example.ycx36.recruitment.presenter.ActivityPresenter;
import com.example.ycx36.recruitment.presenter.FragmentPresenter;
import com.example.ycx36.recruitment.util.GetBDPhotosRequest_Interface;
import com.example.ycx36.recruitment.util.GetNewsRequest_Interface;
import com.example.ycx36.recruitment.util.GetOnShowMoviesRequest_Interface;
import com.example.ycx36.recruitment.view.activity.MovieDetailsActivity;
import com.example.ycx36.recruitment.view.activity.NewsDetailActivity;
import com.example.ycx36.recruitment.view.activity.PhotosShowActivity;
import com.example.ycx36.recruitment.view.activity.UserDetailInfoActivity;
import com.example.ycx36.recruitment.view.fragment.IfragmentView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;
import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ImplClass_IfragmentView implements IfragmentView {

    private ActivityPresenter activityPresenter = new ActivityPresenter(this);//中间者
    private FragmentPresenter fragmentPresenter = new FragmentPresenter(this); //中间者
    private Context context;

    public ImplClass_IfragmentView(Context context){
        this.context = context;
    }


    private ArrayList<SurroundingPlaceData> itemlist1 = new ArrayList<>();
    private ArrayList<SurroundingPlaceData> itemlist2 = new ArrayList<>();
    private SurroundindPlaceAdapter adapter1;
    private SurroundingThingAdapter adapter2;
    private ArrayList<String> MovieUrls = new ArrayList<>();
    private ArrayList<String> MovieName = new ArrayList<>();
    private ArrayList<String> newsUri = new ArrayList<>();
    private ArrayList<String> newsTitle = new ArrayList<>();
    @Override
    public void showRecyclerViewToSurroundingThingsTop(final RecyclerView recycler1, final RecyclerView recycler2, final AVLoadingIndicatorView avi,final AVLoadingIndicatorView avi2) {

        avi.show();
        avi2.show();
        @SuppressLint("HandlerLeak") final Handler handler1 = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 1:
                        /*获取一个线性布局管理器（设置为竖直模式显示的时候用这个管理器）*/
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                        //设置为水平滚动
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recycler1.setLayoutManager(linearLayoutManager);  //将布局管理器设置到recyclerView中
                        adapter1 = new SurroundindPlaceAdapter(itemlist1); //获取适配器实例
                        recycler1.setAdapter(adapter1);
                        /**条目点击事件*/
                        adapter1.setOnItemClickListener(new SurroundindPlaceAdapter.OnItemClickListener() {
                            @Override
                            public void setOnItemClickListener(View view, int position) {
                                Intent intent = new Intent(context, MovieDetailsActivity.class);
                                Bundle bundle = new Bundle();      //创建一个budle对象
                                bundle.putString("MovieName", MovieName.get(position));  //写入数据
                                bundle.putString("MovieUri", MovieUrls.get(position));  //写入数据
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                            }
                        });
                        /**条目长按事件*/
                        adapter1.setOnLongClickListener(new SurroundindPlaceAdapter.OnLongClickListener() {
                            @Override
                            public void setOnLongClickListener(View view, int position) {
                            }
                        });
                        break;
                }
                avi2.hide();
            }
        };

        @SuppressLint("HandlerLeak") final Handler handler2 = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 2:
                        /*获取一个网格布局管理器（设置为瀑布流模式显示的时候用这个管理器）*/
                        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL); //3为指定布局的列数，VERTICAL为排列方式
                        recycler2.setLayoutManager(layoutManager);  //将布局管理器设置到recyclerView中
                        recycler2.setNestedScrollingEnabled(false);  //recyclerView设置禁止嵌套滑动
                        adapter2 = new SurroundingThingAdapter(itemlist2); //获取适配器实例
                        recycler2.setAdapter(adapter2);
                        /**条目点击事件*/
                        adapter2.setOnItemClickListener(new SurroundingThingAdapter.OnItemClickListener() {
                            @Override
                            public void setOnItemClickListener(View view, int position) {
                                Intent intent = new Intent(context, NewsDetailActivity.class);
                                Bundle bundle = new Bundle();      //创建一个budle对象
                                bundle.putString("newsUri", newsUri.get(position));  //写入数据
                                bundle.putString("newsTitle", newsTitle.get(position));  //写入数据
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                            }
                        });
                        /**条目长按事件*/
                        adapter2.setOnLongClickListener(new SurroundingThingAdapter.OnLongClickListener() {
                            @Override
                            public void setOnLongClickListener(View view, int position) {
                            }
                        });
                        break;
                    default:
                        break;
                }
                avi.hide();
            }
        };

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())  //Gson解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://api.douban.com")
                .build();
        GetOnShowMoviesRequest_Interface getOnShowMoviesRequest_interface = retrofit.create(GetOnShowMoviesRequest_Interface.class);

        getOnShowMoviesRequest_interface.getOnShowMoviesData()
                .subscribeOn(Schedulers.io())  //观察者切换新线程,subscribe只能调用一次。
                .doOnNext(new Action1<Gson_OnShowMoviesData>() {    //请求结束后调用 doOnNext(),并获得data数据
                    @Override
                    public void call(Gson_OnShowMoviesData datas) {
                        for (int i=0; i<datas.getSubjects().size() ;i++){
                            String s0 = datas.getSubjects().get(i).getTitle();
                            MovieName.add(s0);
                            if (s0.length()>5){
                                s0 = s0.substring(0,5)+"…";
                            }
                            MovieUrls.add(datas.getSubjects().get(i).getAlt());
                            SurroundingPlaceData surroundingPlaceData = new SurroundingPlaceData(s0, "豆瓣评分："+String.valueOf(datas.getSubjects().get(i).getRating().getAverage()), GetDraweeControler(datas.getSubjects().get(i).getImages().getMedium()));
                            itemlist1.add(surroundingPlaceData);
                        }
                        Message message = new Message();  //创建一个message对象。并将它的what字段的值指定为UPDATA_TEXT
                        message.what = 1;
                        handler1.sendMessage(message);     //handler去发送消息
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())   //被观察者切换到主线程
                .subscribe(new Action1<Gson_OnShowMoviesData>() {    //观察者监听到datas数据,主线程中执行
                    @Override
                    public void call(Gson_OnShowMoviesData datas) {

                    }
                });


        Retrofit retrofit2 = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())  //Gson解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://p.techroom.com.cn")
                .build();
        GetNewsRequest_Interface getNewsRequest_interface = retrofit2.create(GetNewsRequest_Interface.class);

        getNewsRequest_interface.getNewsData()
                .subscribeOn(Schedulers.newThread())  //观察者切换新线程,subscribe只能调用一次。
                .doOnNext(new Action1<Gson_NewsDataBean>() {    //请求结束后调用 doOnNext(),并获得data数据
                    @Override
                    public void call(Gson_NewsDataBean datas) {
                        for (int i=0; i <datas.getData().size() ;i++){
                            String s01 = datas.getData().get(i).getTitle();
                            if (s01.length()>5){
                                s01 = s01.substring(0,5)+"…";
                                newsTitle.add(s01);
                            }
                            newsUri.add(datas.getData().get(i).getUrl());
                            SurroundingPlaceData surroundingPlaceData = new SurroundingPlaceData(datas.getData().get(i).getTitle(), GetDraweeControler(datas.getData().get(i).getPic()));
                            itemlist2.add(surroundingPlaceData);
                        }
                        Message message = new Message();  //创建一个message对象。并将它的what字段的值指定为UPDATA_TEXT
                        message.what = 2;
                        handler2.sendMessage(message);     //handler去发送消息
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())   //被观察者切换到主线程
                .subscribe(new Action1<Gson_NewsDataBean>() {    //观察者监听到datas数据,主线程中执行
                    @Override
                    public void call(Gson_NewsDataBean datas) {

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



    private ArrayList<MessageDataBean> datalist3 = new ArrayList<>();
    @Override
    public void showRecyclerViewToPrivateMessage(RecyclerView recycler, Adapter_Message adapter) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL); //3为指定布局的列数，VERTICAL为排列方式
        recycler.setLayoutManager(layoutManager);  //将布局管理器设置到recyclerView中
        recycler.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        adapter = new Adapter_Message(activityPresenter.getShowMessageData(datalist3)); //获取适配器实例
        recycler.setAdapter(adapter);

        /**条目点击事件*/
        adapter.setOnItemClickListener(new Adapter_Message.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, final int position) {
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null) {
                    String userName = currentUser.getUsername();
                    final ArrayList<MessageDataBean> datalistToPrivateMessage = activityPresenter.getShowMessageData(datalist3);
                    LCChatKit.getInstance().open(userName, new AVIMClientCallback() {
                        @Override
                        public void done(AVIMClient avimClient, AVIMException e) {
                            if (null == e) {
                                Intent intent = new Intent(context, LCIMConversationActivity.class);
                                intent.putExtra(LCIMConstants.PEER_ID, datalistToPrivateMessage.get(position).getUserName());
                                context.startActivity(intent);
                            } else {
                                Toast.makeText(context, "" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(context, "您还没有登录，请登录。", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**条目长按事件*/
        adapter.setOnLongClickListener(new Adapter_Message.OnLongClickListener() {
            @Override
            public void setOnLongClickListener(View view, final int position) {
                /**删除条目的方法*/
//                adapter.removeData(position);
                //Toast.makeText(getActivity(),"这是条目"+position+1,Toast.LENGTH_SHORT).show();

            }
        });

    }

    private ArrayList<PositionDataBean> datalist4 = new ArrayList<>();
    @Override
    public void showRecyclerViewToRecruitmentInformation(RecyclerView recycler, Adapter_Position adapter) {
        /**获取一个网格布局管理器（设置为瀑布流模式显示的时候用这个管理器）*/
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL); //3为指定布局的列数，VERTICAL为排列方式
        recycler.setLayoutManager(layoutManager);  //将布局管理器设置到recyclerView中
        recycler.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        adapter = new Adapter_Position(activityPresenter.getShowPositionData(datalist4)); //获取适配器实例
        recycler.setAdapter(adapter);
        recycler.setNestedScrollingEnabled(false);  //recyclerView设置禁止嵌套滑动
        /**条目点击事件*/
        adapter.setOnItemClickListener(new Adapter_Position.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {

            }
        });
        /**条目长按事件*/
        adapter.setOnLongClickListener(new Adapter_Position.OnLongClickListener() {
            @Override
            public void setOnLongClickListener(View view, int position) {
                /**删除条目的方法*/
//                adapter.removeData(position);
                //Toast.makeText(getActivity(),"这是条目"+position+1,Toast.LENGTH_SHORT).show();
            }
        });
    }



    /**获取当前用户的关注列表*/

    @Override
    public void GetMyFollwersToPrivateMessage(final RecyclerView recycler, final ArrayList<MessageDataBean> itemFollwerslist) {
//        itemFollwerslist.clear();
        //查询关注者
        AVUser avUser = AVUser.getCurrentUser();
        if (avUser != null) {
            AVQuery<AVUser> followeeQuery = AVUser.followeeQuery(AVUser.getCurrentUser().getObjectId(), AVUser.class);
            followeeQuery.findInBackground(new FindCallback<AVUser>() {
                @Override
                public void done(final List<AVUser> avObjects, AVException avException) {
                    //avObjects 就是用户的关注用户列表
                    final List<String> listName = new ArrayList<>();
                    final List<String> listUserPhotoUris = new ArrayList<>();
//                    final List<String> listUserobjectID = new ArrayList<>();
                    int i;
                    for (i = 0; i < avObjects.size(); i++) {
                        AVObject todo = AVObject.createWithoutData("_User", avObjects.get(i).getObjectId());
                        todo.fetchInBackground(new GetCallback<AVObject>() {
                            @Override
                            public void done(AVObject avObject, AVException e) {
                                Adapter_Message adapter1;
                                listName.add(avObject.getString("username"));
                                listUserPhotoUris.add(avObject.getString("userPhotoUri"));
//                                listUserobjectID.add(avObject.getString("objectId"));
                                if (listName.size() == avObjects.size()) {
                                    for (int k = 0; k < listName.size(); k++) {
                                        MessageDataBean x1 = new MessageDataBean(listName.get(k), "", "", GetDraweeControler(listUserPhotoUris.get(k)));
                                        itemFollwerslist.add(x1);
                                    }
                                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                                    recycler.setLayoutManager(layoutManager);  //将布局管理器设置到recyclerView中
                                    recycler.setNestedScrollingEnabled(false);  //recyclerView设置禁止嵌套滑动
                                    adapter1 = new Adapter_Message(itemFollwerslist); //获取适配器实例
                                    recycler.setAdapter(adapter1);

                                    /**条目点击事件*/
                                    adapter1.setOnItemClickListener(new Adapter_Message.OnItemClickListener() {
                                        @Override
                                        public void setOnItemClickListener(View view,int position) {
                                            Intent intent = new Intent(context,UserDetailInfoActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("userObjectId","");
                                            bundle.putString("userName",listName.get(position));
                                            intent.putExtras(bundle);
                                            context.startActivity(intent);
                                        }
                                    });
                                    /**条目长按事件*/
                                    adapter1.setOnLongClickListener(new Adapter_Message.OnLongClickListener() {
                                        @Override
                                        public void setOnLongClickListener(View view, final int position) {
                                            /**删除条目的方法*/
//                adapter.removeData(position);
                                            //Toast.makeText(getActivity(),"这是条目"+position+1,Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }
                            }

                        });
                    }
                }
            });
        }
    }

    @Override
    public void setBanner(BGABanner mContentBanner) {
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        // 设置数据源
        mContentBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.drawable.gxnu_wc,
                R.drawable.gxnu_library,
                R.drawable.gxnu_ys,
                R.drawable.gxnu1);
        //监听item 的单击事件
        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
//                Toast.makeText(banner.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PhotosShowActivity.class);
                context.startActivity(intent);
            }
        });
    }


    private int[] titleHome = fragmentPresenter.getHomePageTitle();
    private Class<? extends Fragment>[] clazzHome = fragmentPresenter.getHomePageClazz();

    private int[] titleDiscover = fragmentPresenter.getDiscoverTitle();
    private Class<? extends Fragment>[] clazzDiscover = fragmentPresenter.getDiscoverClazz();

    private int[] titleMessage = fragmentPresenter.getMessageTitle();
    private Class<? extends Fragment>[] clazzMessage = fragmentPresenter.getMessageClazz();

    private int[] titleMap = fragmentPresenter.getMapTitle();
    private Class<? extends Fragment>[] clazzMap = fragmentPresenter.getMapClazz();
    @Override
    public void showTopNavigation(int Flag,FragmentActivity fragmentActivity,FragmentPagerItemAdapter adapter, ViewPager DiscoverViewpager, SmartTabLayout DiscoverViewpagertab) {
        if (Flag == 1) {
            adapter = new FragmentPagerItemAdapter(
                    fragmentActivity.getSupportFragmentManager(), FragmentPagerItems.with(context)
                    .add(titleHome[0], clazzHome[0])   //添加 学校概括 碎片
                    .add(titleHome[1], clazzHome[1])  //添加 招聘信息 碎片
//                    .add(titleHome[2], clazzHome[2])  //添加 招聘答疑 碎片
                    .create());
            DiscoverViewpager.setAdapter(adapter);
            DiscoverViewpagertab.setViewPager(DiscoverViewpager);
        }
        else if(Flag == 2){
            adapter = new FragmentPagerItemAdapter(
                    fragmentActivity.getSupportFragmentManager(), FragmentPagerItems.with(context)
                    .add(titleDiscover[0], clazzDiscover[0])   //添加 周边 碎片
                    .add(titleDiscover[1], clazzDiscover[1])  //添加 办公办事 碎片
                    .create());
            DiscoverViewpager.setAdapter(adapter);
            DiscoverViewpagertab.setViewPager(DiscoverViewpager);
        }
        else if(Flag == 3){
            adapter = new FragmentPagerItemAdapter(
                    fragmentActivity.getSupportFragmentManager(), FragmentPagerItems.with(context)
                    .add(titleMessage[1], clazzMessage[1])   //添加 私信 碎片
                    .add(titleMessage[0], clazzMessage[0])  //添加 通知 碎片
                    .create());
            DiscoverViewpager.setAdapter(adapter);
            DiscoverViewpagertab.setViewPager(DiscoverViewpager);
        }
        else if (Flag == 4){
            adapter = new FragmentPagerItemAdapter(
                    fragmentActivity.getSupportFragmentManager(), FragmentPagerItems.with(context)
                    .add(titleMap[0], clazzMap[0])   //添加 雁山校区地图 碎片
                    .add(titleMap[1], clazzMap[1])  //添加 育才校区地图 碎片
                    .add(titleMap[2], clazzMap[2])  //添加 王城校区地图 碎片
                    .create());
            DiscoverViewpager.setAdapter(adapter);
            DiscoverViewpagertab.setViewPager(DiscoverViewpager);
        }
    }

    @Override
    public void initUI(final IfragmentView ifragmentView, final RecyclerView re1, final RecyclerView re2, final AVLoadingIndicatorView avi,final AVLoadingIndicatorView avi2) {
        ifragmentView.showRecyclerViewToSurroundingThingsTop(re1, re2, avi,avi2);
    }


    @Override
    public void showSmallMapPhoto(String path, SimpleDraweeView Dview) {
        Uri uri = Uri.parse(path);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setLocalThumbnailPreviewsEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .build();
        Dview.setController(controller);
    }

    @Override
    public void ChangeMessageTitleStyle(int Flag,Context context, RelativeLayout relativeLayout1, TextView textView1, RelativeLayout relativeLayout2, TextView textView2, RelativeLayout relativeLayout3, TextView textView3) {
        if(Flag == 1) {
            relativeLayout1.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary2));
            textView1.setTextColor(context.getResources().getColor(R.color.gxnuColor));
            relativeLayout2.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            textView2.setTextColor(context.getResources().getColor(R.color.black));
            relativeLayout3.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            textView3.setTextColor(context.getResources().getColor(R.color.black));
        }
        else if (Flag == 2){
            relativeLayout1.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            textView1.setTextColor(context.getResources().getColor(R.color.black));
            relativeLayout2.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary2));
            textView2.setTextColor(context.getResources().getColor(R.color.gxnuColor));
            relativeLayout3.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            textView3.setTextColor(context.getResources().getColor(R.color.black));
        }
        else if (Flag == 3){
            relativeLayout1.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            textView1.setTextColor(context.getResources().getColor(R.color.black));
            relativeLayout2.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            textView2.setTextColor(context.getResources().getColor(R.color.black));
            relativeLayout3.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary2));
            textView3.setTextColor(context.getResources().getColor(R.color.gxnuColor));
        }
    }
}
