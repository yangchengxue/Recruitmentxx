package com.example.ycx36.recruitment.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.view.ViewImplClass.ImplClass_IactivityView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**反馈留言界面*/
public class FeedbackActivity extends AppCompatActivity {

    @BindView(R.id.et_content) EditText et_content;
    @BindView(R.id.RemainText) TextView RemainText;
    @BindView(R.id.id_flowlayout) TagFlowLayout mFlowLayout;
    private String[] mVals = new String[]{"功能", "bug", "建议", "其他"};
    private IactivityView iactivityView;

    private List<String> type = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind( this);
        iactivityView = new ImplClass_IactivityView(this);
        final LayoutInflater mInflater = LayoutInflater.from(this);
        iactivityView.EditTextSet(et_content,RemainText,R.string.ETfeedBack,"100");

//        /**标签点击事件*/
//        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
//        {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent)
//            {
////                Toast.makeText(FeedbackActivity.this, mVals[position], Toast.LENGTH_SHORT).show();
////                type.add(mVals[position]);
//                Log.d("dsadfg1","diou           "+mVals[position]);
//                return true;
//            }
//        });
        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener()
        {
            @Override
            public void onSelected(Set<Integer> selectPosSet)
            {
                Iterator<Integer> value = selectPosSet.iterator();
                type.clear();
                while(value.hasNext())//判断是否有下一个
                {
                    type.add(mVals[value.next()]);
                }

            }
        });
        /**设置标签布局*/
        mFlowLayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }

        });
    }

    @OnClick({R.id.tv_header_back,R.id.tv_header_right,R.id.bt_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回键
                finish();
                break;
            case R.id.bt_save:  //提交
                Submission();
                break;
        }
    }

    public void Submission(){
        StringBuilder sss = new StringBuilder();
        for (int i=0; i<type.size(); i++){
            sss.append("_").append(type.get(i));
        }
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null){
            AVObject todoFolder = new AVObject("FeedbackClass");// 构建对象
            todoFolder.put("userobjectid", currentUser.getObjectId());
            todoFolder.put("username", currentUser.getUsername());
            todoFolder.put("feedbacktype", sss.toString());
            todoFolder.put("feedbackcontent", et_content.getText().toString());
            todoFolder.saveInBackground();
            Toast.makeText(FeedbackActivity.this,"提交成功，感谢您的反馈^_^",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(FeedbackActivity.this,"请先登录。",Toast.LENGTH_SHORT).show();
        }

    }
}
