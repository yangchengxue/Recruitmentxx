package com.example.ycx36.recruitment.view.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.presenter.ActivityPresenter;
import com.example.ycx36.recruitment.util.BottomNavigationViewHelper;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IactivityView;
import com.example.ycx36.recruitment.view.fragment.Discover;
import com.example.ycx36.recruitment.view.fragment.HomePage;
import com.example.ycx36.recruitment.view.fragment.Message;
import com.example.ycx36.recruitment.view.fragment.Message2;
import com.example.ycx36.recruitment.view.fragment.Mine;
import com.example.ycx36.recruitment.view.fragment.Mine2;
import com.nightonke.boommenu.Animation.OrderEnum;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.TitleText) public TextView TitleText;
    @BindView(R.id.TitleMenu) public TextView TitleMenu;
    @BindView(R.id.bmb) public BoomMenuButton bmb;
    FragmentManager fragmentManager;
    private IactivityView iactivityView;
    private HomePage homePage = new HomePage();
    private Discover discover = new Discover();
    private Message message = new Message();
    private Message2 message2 = new Message2();
    private Mine2 mine = new Mine2();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case com.example.ycx36.recruitment.R.id.navigation_home:
                    iactivityView.replaceFragment(1,fragmentManager,homePage);
                    iactivityView.ChangeTitle(TitleText,"首页");
                    return true;
                case com.example.ycx36.recruitment.R.id.navigation_dashboard:
                    iactivityView.replaceFragment(1,fragmentManager,discover);
                    iactivityView.ChangeTitle(TitleText,"发现");
                    return true;
                case com.example.ycx36.recruitment.R.id.navigation_notifications:
                    iactivityView.replaceFragment(1,fragmentManager,message2);
                    iactivityView.ChangeTitle(TitleText,"消息");
                    return true;
                case com.example.ycx36.recruitment.R.id.navigation_mine:
                    iactivityView.replaceFragment(1,fragmentManager,mine);
                    iactivityView.ChangeTitle(TitleText,"我的");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ycx36.recruitment.R.layout.activity_main);
        ButterKnife.bind( this);
        iactivityView = new ImplClass_IactivityView(this);
        BottomNavigationView navigation = findViewById(com.example.ycx36.recruitment.R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);  //取消BottomNavigationView的自带动画
        fragmentManager = getSupportFragmentManager();
        iactivityView.replaceFragment(1,fragmentManager,homePage);
        iactivityView.setBoomMenuButton(bmb);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }



    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}


