package com.example.ycx36.recruitment.view.activity;

import android.annotation.SuppressLint;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ycx36.recruitment.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MovieDetailsActivity extends AppCompatActivity {
    @BindView(R.id.tv_header_centerText) TextView tv_header_centerText;
    @BindView(R.id.tbsContent) com.tencent.smtt.sdk.WebView tbsContent;
    @BindView(R.id.avi) AVLoadingIndicatorView avi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        String movieName = bundle.getString("MovieName");
        String MovieUri = bundle.getString("MovieUri");
        tv_header_centerText.setText(movieName);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView(MovieUri);
    }

    /**
     * 初始化
     */
    int i = 0;
    @SuppressLint("SetJavaScriptEnabled")
    private void initView(String path) {
        avi.show();

        tbsContent.loadUrl(path);   //加载网址
        WebSettings webSettings = tbsContent.getSettings();    //获取设置
        webSettings.setJavaScriptEnabled(true);
        tbsContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                i = i + 1;
                if (i == 3){
                    avi.hide();
                }
                return true;
            }
        });
    }


    /**监听返回键*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && tbsContent.canGoBack()) {
            tbsContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
