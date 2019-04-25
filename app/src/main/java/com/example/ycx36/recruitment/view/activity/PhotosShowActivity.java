package com.example.ycx36.recruitment.view.activity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.adapter.Adapter_PhotoText;

import com.example.ycx36.recruitment.model.dataBean.SchoolSceneryText;
import com.example.ycx36.recruitment.presenter.ActivityPresenter;

import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IactivityView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nightonke.boommenu.BoomMenuButton;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**显示学校风景的沉浸式布局*/
public class PhotosShowActivity extends AppCompatActivity{

    @BindView(R.id.Fragment_layout) public FrameLayout Fragment_layout;
    @BindView(R.id.recyclerSchoolText) RecyclerView recyclerSchoolText;
    @BindView(R.id.cancelFullScreen) View mContentView;
    @BindView(R.id.fullscreen_content_controls) View mControlsView;

    private ImplClass_IactivityView implClass_iactivityView;
    private static final boolean AUTO_HIDE = true; //是否自动隐藏
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;//恢复之后 几毫秒之后进入全屏
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_show);
        ButterKnife.bind( this);
        implClass_iactivityView = new ImplClass_IactivityView(this);
        mVisible = true;

        /**设置用户交互以手动显示或隐藏系统UI*/
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
        implClass_iactivityView.showRecyclerViewToPhotoShowActivity(recyclerSchoolText);

    }

    @Override
    /**进入该Activity后几毫秒进入全屏*/
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(200);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    /**延迟取消状态和导航栏*/
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    /**UI元素延迟显示*/
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    /**
     * 触摸监听器用于布局UI控件，以延迟隐藏系统UI。这是为了防止在与活动UI交互时，控件的不协调行为消失。
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };


}
