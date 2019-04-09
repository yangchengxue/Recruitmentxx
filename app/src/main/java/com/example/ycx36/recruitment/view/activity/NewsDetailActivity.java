package com.example.ycx36.recruitment.view.activity;

import android.annotation.SuppressLint;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ycx36.recruitment.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_header_centerText)
    TextView tv_header_centerText;
    @BindView(R.id.tbsContent) com.tencent.smtt.sdk.WebView tbsContent;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        String newsUri = bundle.getString("newsUri");
        String newsTitle = bundle.getString("newsTitle");
        tv_header_centerText.setText(newsTitle);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView(newsUri);
    }

    /**
     * 初始化
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initView(String path) {
        avi.show();
        Log.d("ssssss1",""+Thread.currentThread().getName());
        tbsContent.loadUrl(path);   //加载网址
        WebSettings webSettings = tbsContent.getSettings();    //获取设置
        webSettings.setJavaScriptEnabled(true);
        tbsContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        avi.hide();
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
