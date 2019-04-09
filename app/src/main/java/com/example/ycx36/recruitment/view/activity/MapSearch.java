package com.example.ycx36.recruitment.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.googleMapUtil.CheckPermissionsActivity;
import com.example.ycx36.recruitment.googleMapUtil.Constants;
import com.example.ycx36.recruitment.googleMapUtil.InputTipsAdapter;
import com.example.ycx36.recruitment.googleMapUtil.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MapSearch extends CheckPermissionsActivity
        implements SearchView.OnQueryTextListener, Inputtips.InputtipsListener,
        AdapterView.OnItemClickListener, View.OnClickListener{

    private SearchView searchView;
    private ListView list_query;
    private List<Tip> list;
    private InputTipsAdapter inputTipsAdapter;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_search);
        init();
    }

    private void init(){
        searchView  = (SearchView) findViewById(R.id.keyword);
        searchView.setOnQueryTextListener(this);
        // 设置搜索默认展开
        searchView.setIconified(false);
        searchView.onActionViewExpanded();
        searchView.setIconifiedByDefault(true);
        searchView.setIconifiedByDefault(false);

        list_query = findViewById(R.id.list_query);
        list_query.setOnItemClickListener(this);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (list != null) {
            Tip tip = (Tip) adapterView.getItemAtPosition(i);

            Intent intent = new Intent();
            intent.putExtra(Constants.EXTRA_TIP, tip);
            setResult(MapActivity.RESULT_CODE_INPUTTIPS, intent);

            finish();
        }
    }

    // 按下回车或确定
    @Override
    public boolean onQueryTextSubmit(String s) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_WORDS_NAME, s);

        return false;
    }

    // 字符串改变时
    @Override
    public boolean onQueryTextChange(String s) {
        if (!IsEmptyOrNullString(s)) {
            InputtipsQuery inputtipsQuery = new InputtipsQuery(s, Constants.DEFAULT_CITY);
            Inputtips inputtips = new Inputtips(MapSearch.this, inputtipsQuery);
            inputtips.setInputtipsListener(this);
            inputtips.requestInputtipsAsyn();
        } else {
            if (inputTipsAdapter != null && list!= null){
                list.clear();
                inputTipsAdapter.notifyDataSetChanged();
            }
        }
        return false;
    }

    //
    @Override
    public void onGetInputtips(List<Tip> tlist, int error) {
        if (error == 1000) {
            list = tlist;
            List<String> stringList = new ArrayList<String>();
            for (int i=0; i<list.size(); i++) {
                stringList.add(list.get(i).getName());
            }
            inputTipsAdapter  = new InputTipsAdapter(this, list);
            //  添加适配器
            list_query.setAdapter(inputTipsAdapter);
            inputTipsAdapter.notifyDataSetChanged();
        } else {
            // 输出错误
            ToastUtil.showerror(this, error);
        }
    }

    // 判断字符串是否空
    public static boolean IsEmptyOrNullString(String s) {
        return (s == null) || (s.trim().length() == 0);
    }
}
