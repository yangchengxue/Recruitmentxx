package com.example.ycx36.recruitment.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.adapter.Adapter_SetUserInfo;
import com.example.ycx36.recruitment.model.dataBean.SetInfoDataItemBean;
import com.example.ycx36.recruitment.model.db.userinfo;
import com.example.ycx36.recruitment.model.db.users;
import com.example.ycx36.recruitment.okhttp.HttpUtil;
import com.example.ycx36.recruitment.presenter.ActivityPresenter;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IactivityView;
import com.example.ycx36.recruitment.view.fragment.IfragmentView;

import org.feezu.liuli.timeselector.TimeSelector;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.OptionPicker;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserInfoActivity extends AppCompatActivity {

    @BindView(R.id.userInfoRecycler)
    RecyclerView userInfoRecycler;
    IactivityView iactivityView;
    ActivityPresenter activityPresenter = new ActivityPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        iactivityView = new ImplClass_IactivityView(this);
//        iactivityView.showRecyclerViewToUserInfoActivity(userInfoRecycler);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); //3为指定布局的列数，VERTICAL为排列方式
        userInfoRecycler.setLayoutManager(layoutManager);  //将布局管理器设置到recyclerView中
        userInfoRecycler.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        userInfoRecycler.setNestedScrollingEnabled(false);  //recyclerView设置禁止嵌套滑动
        adapter = new Adapter_SetUserInfo(activityPresenter.getShowSetUsrInfoItemData(itemlist1)); //获取适配器实例
        userInfoRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new Adapter_SetUserInfo.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                final AVUser currentUser = AVUser.getCurrentUser();
                switch (position) {
                    case 0:
                        IntentToFill_InformationActivity("真实姓名", "userName", R.string.ETname);
                        break;
                    case 1:
                        TimeSelector timeSelector = new TimeSelector(UserInfoActivity.this, new TimeSelector.ResultHandler() {
                            @Override
                            public void handle(String time) {
                                adapter.ChangeData(1, "出生年月", time.substring(0, 10));
                                saveInfo(currentUser, "userBirthday", time.substring(0, 10));
                            }
                        }, "1900-01-01 00:00", "2019-12-31 24:00");
                        timeSelector.setMode(TimeSelector.MODE.YMD);//只显示 年月日
                        timeSelector.show();
                        break;
                    case 2:
                        IntentToFill_InformationActivity("民族", "userNation", R.string.ETnation);
                        break;
                    case 3:
                        showSelecter(adapter, currentUser, 3, new String[]{"男", "女"}, "性别", "userSex");
                        break;
                    case 4:
                        IntentToFill_InformationActivity("职称", "userZhiCheng", R.string.ETjob);
                        break;
                    case 5:
                        IntentToFill_InformationActivity("籍贯", "userPlaceOfBirth", R.string.ETPlaceOfBirth);
                        break;
                    case 6:
                        IntentToFill_InformationActivity("最高学历", "userHighestEducation", R.string.ETHighestEducation);
                        break;
                    case 7:
                        IntentToFill_InformationActivity("最高学位", "userHighestOffering", R.string.ETHighestOffering);
                        break;
                    case 8:
                        showSelecter(adapter, currentUser, 8, new String[]{"中共党员", "中共预备党员", "共青团员", "群众", "民革党员", "民盟盟员", "民建会员", "民进会员", "农工党党员", "致公党党员", "九三学社社员", "台盟盟员", " 无党派人士"}, "政治面貌", "userPoliticsStatus");
                        break;
                    case 9:
                        IntentToFill_InformationActivity("毕业院校", "userJobCandidates", R.string.ETJobCandidates);
                        break;
                    case 10://婚姻状况
                        showSelecter(adapter, currentUser, 10, new String[]{"已婚", "未婚"}, "婚姻状况", "userMarriageStatus");
                        break;
                    case 11:
                        IntentToFill_InformationActivity("联系电话", "userTelephone", R.string.ETTelephone);
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

    public void saveInfo(AVUser currentUser, String dbTableIndex, String s) {
        if (currentUser != null) {
            List<users> users = DataSupport.where("U_Id = ?", currentUser.getObjectId()).find(users.class);
            if (users != null) {
                int u_id = users.get(0).getId();
                ContentValues values = new ContentValues();
                values.put(dbTableIndex, s);
                DataSupport.updateAll(userinfo.class, values, "users_id = ?", String.valueOf(u_id));
            }
        } else
            Toast.makeText(UserInfoActivity.this, "您还未登录，请登录后再保存。", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.tv_header_back, R.id.tv_header_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_header_back:   //返回键
                finish();
                break;
            case R.id.tv_header_right:  //顶部栏右边的保存
                sendUserInfo();
                finish();
                //Toast.makeText(UserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    Adapter_SetUserInfo adapter;
    private ArrayList<SetInfoDataItemBean> itemlist1 = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();

        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
//        users users = DataSupport.find(users.class, 1, true);
            List<users> users1 = DataSupport.where("U_Id = ?", currentUser.getObjectId()).find(users.class, true);
            if (users1.size() != 0) {
                if (users1.get(0).getU_Id().equals(currentUser.getObjectId())) {
                    if (users1.get(0).getUserinfo() != null) {
                        if (users1.get(0).getUserinfo().getUserName() != null) {
                            String uName = users1.get(0).getUserinfo().getUserName();
                            adapter.ChangeData(0, "真实姓名", uName);
                        }
                        if (users1.get(0).getUserinfo().getUserBirthday() != null) {
                            adapter.ChangeData(1, "出生年月", users1.get(0).getUserinfo().getUserBirthday());
                        }
                        if (users1.get(0).getUserinfo().getUserNation() != null) {
                            adapter.ChangeData(2, "民族", users1.get(0).getUserinfo().getUserNation());
                        }
                        if (users1.get(0).getUserinfo().getUserSex() != null) {
                            adapter.ChangeData(3, "性别", users1.get(0).getUserinfo().getUserSex());
                        }
                        if (users1.get(0).getUserinfo().getUserZhiCheng() != null) {
                            adapter.ChangeData(4, "职称", users1.get(0).getUserinfo().getUserZhiCheng());
                        }
                        if (users1.get(0).getUserinfo().getUserPlaceOfBirth() != null) {
                            adapter.ChangeData(5, "籍贯", users1.get(0).getUserinfo().getUserPlaceOfBirth());
                        }
                        if (users1.get(0).getUserinfo().getUserHighestEducation() != null) {
                            adapter.ChangeData(6, "最高学历", users1.get(0).getUserinfo().getUserHighestEducation());
                        }
                        if (users1.get(0).getUserinfo().getUserHighestOffering() != null) {
                            adapter.ChangeData(7, "最高学位", users1.get(0).getUserinfo().getUserHighestOffering());
                        }
                        if (users1.get(0).getUserinfo().getUserPoliticsStatus() != null) {
                            adapter.ChangeData(8, "政治面貌", users1.get(0).getUserinfo().getUserPoliticsStatus());
                        }
                        if (users1.get(0).getUserinfo().getUserJobCandidates() != null) {
                            adapter.ChangeData(9, "应聘岗位", users1.get(0).getUserinfo().getUserJobCandidates());
                        }
                        if (users1.get(0).getUserinfo().getUserMarriageStatus() != null) {
                            adapter.ChangeData(10, "婚姻状况", users1.get(0).getUserinfo().getUserMarriageStatus());
                        }
                        if (users1.get(0).getUserinfo().getUserTelephone() != null) {
                            adapter.ChangeData(11, "联系电话", users1.get(0).getUserinfo().getUserTelephone());
                        }
                    }
                }
            }
        }
    }

    /**
     * 跳转到 填写详细信息 界面
     */
    public void IntentToFill_InformationActivity(String title, String dbTableIndex, int hintText) {
        Intent intent = new Intent(this, Fill_InformationActivity.class);
        Bundle bundle = new Bundle();      //创建一个budle对象
        bundle.putString("title", title);  //写入数据
        bundle.putString("dbTableIndex", dbTableIndex);  //写入数据
        bundle.putInt("hintText", hintText);  //写入数据
        intent.putExtras(bundle);             //用putExtras方法将写入的数据存入intent中
        startActivity(intent);
    }


    /**
     * 显示单项选择器
     */
    public void showSelecter(final Adapter_SetUserInfo adapter, final AVUser currentUser, final int indexx, String[] items, final String title, final String dbTableIndex) {
        OptionPicker picker = new OptionPicker(this, items);
        picker.setOffset(2);
        picker.setSelectedIndex(0); //从哪一项开始索引
        picker.setTextSize(17);
        picker.setTextColor(getResources().getColor(R.color.colorPrimary));
//        picker.setCycleDisable(false);//启用循环
        picker.setLineSpaceMultiplier(3);//设置每项的高度，范围为2-4
        picker.setCanceledOnTouchOutside(false);//设置触摸弹窗外面是否自动关闭
//        picker.setDividerColor(R.color.color_red); //分割线颜色
//        picker.setShadowColor(); //设置选中项背景色
        picker.setContentPadding(5, 5);  //设置内容边距
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                adapter.ChangeData(indexx, title, item);
                saveInfo(currentUser, dbTableIndex, item);
            }
        });
        picker.show();
    }

    /**
     * 向服务器上传数据
     **/
    private void sendUserInfo() {

            Log.e("2333",getUserNameData());

        HttpUtil.sendUserInfo("http://47.106.170.241:8080/gxnuPublicity/SetUserInfo", getUserNameData(),
                adapter.getData(0).getItemRightText(), adapter.getData(1).getItemRightText(),
                adapter.getData(2).getItemRightText(), adapter.getData(3).getItemRightText(),
                adapter.getData(4).getItemRightText(), adapter.getData(5).getItemRightText(),
                adapter.getData(6).getItemRightText(), adapter.getData(7).getItemRightText(),
                adapter.getData(8).getItemRightText(), adapter.getData(9).getItemRightText(), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        showSendResult(false);
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        showSendResult(true);
                    }
                });
    }

    /**
     * 上传结果反馈
     **/
    private void showSendResult(final Boolean result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                if (!result)
                    Toast.makeText(UserInfoActivity.this, "保存失败，请检查网络", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(UserInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * SharedPreferences获取用户名数据
     */
    public String getUserNameData() {
        SharedPreferences pref = getSharedPreferences("s_username", MODE_PRIVATE);
        return pref.getString("username", "");  //后面的参数为如果找不到对应值，就返回什么样的默认值
    }
}
