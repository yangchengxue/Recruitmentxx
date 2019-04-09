package com.example.ycx36.recruitment.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.ycx36.recruitment.R;
import com.example.ycx36.recruitment.googleMapUtil.BusRouteOverlay;
import com.example.ycx36.recruitment.googleMapUtil.CheckPermissionsActivity;
import com.example.ycx36.recruitment.googleMapUtil.Constants;
import com.example.ycx36.recruitment.googleMapUtil.DrivingRouteOverlay;
import com.example.ycx36.recruitment.googleMapUtil.PoiOverlay;
import com.example.ycx36.recruitment.googleMapUtil.RideRouteOverlay;
import com.example.ycx36.recruitment.googleMapUtil.ToastUtil;
import com.example.ycx36.recruitment.googleMapUtil.WalkRouteOverlay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MapActivity extends CheckPermissionsActivity implements
        AMap.OnMarkerClickListener, AMap.InfoWindowAdapter,
        PoiSearch.OnPoiSearchListener, View.OnClickListener,
        LocationSource, AMapLocationListener, RouteSearch.OnRouteSearchListener,
        AMap.OnInfoWindowClickListener {

    private LinearLayout search;
    private MapView mapView;
    private AMap aMap;
    // 定位
    private AMapLocationClient mLocationClient=null;
    private AMapLocationClientOption mLocationOption=null;
    private OnLocationChangedListener mListener=null;
    private AMapLocation myLocation=null;
    // 搜索
    private boolean isFirstLocation = true; // 首地点标记
    private PoiResult poiResult; // 搜索结果
    private int currentPage = 1;
    private PoiSearch.Query query; //查询条件类
    private PoiSearch poiSearch;
    private Marker marker; //标记点
    private TextView keyText;
    private ProgressDialog progDialog = null;// 搜索时进度条
    // 路线规划
    private RouteSearch routeSearch;
    private Marker nextmarker;
    private Button btn_bus, btn_walk, btn_drive, btn_ride, btn_clear;
    private LinearLayout Routetools;
    private boolean isRouting = false;

    private static final int ROUTE_TYPE_WALK = 0;
    private static final int ROUTE_TYPE_RIDE = 1;
    private static final int ROUTE_TYPE_DRIVE = 2;
    private static final int ROUTE_TYPE_BUS = 3;

    public static final int REQUEST_CODE = 100;
    public static final int RESULT_CODE_INPUTTIPS = 101;
    public static final int RESULT_CODE_KEYWORDS = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);

        loadView();
        init();
        mapView.onCreate(savedInstanceState);
    }

    private void loadView(){
        mapView = findViewById(R.id.mapView);
        search = findViewById(R.id.search);
        keyText = findViewById(R.id.text_search);
        btn_bus = findViewById(R.id.btn_bus);
        btn_walk = findViewById(R.id.btn_walk);
        btn_ride = findViewById(R.id.btn_ride);
        btn_drive = findViewById(R.id.btn_drive);
        btn_clear = findViewById(R.id.btn_clear);
        Routetools = findViewById(R.id.route_tools);
    }
    private void init(){
        search.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_walk.setOnClickListener(this);
        btn_ride.setOnClickListener(this);
        btn_drive.setOnClickListener(this);
        btn_bus.setOnClickListener(this);

        aMap = mapView.getMap();
        UiSettings us = aMap.getUiSettings();
        // 关闭放大缩小按钮
        us.setZoomControlsEnabled(false);
        // 开启定位按钮
        us.setMyLocationButtonEnabled(true);
        // 设置定位监听
        aMap.setLocationSource(this);
        //
        aMap.setOnInfoWindowClickListener(this);
        // 显示定位层并触发定位
        aMap.setMyLocationStyle(new MyLocationStyle().interval(2000).showMyLocation(true));
        aMap.setMyLocationEnabled(true);

        openLocation();
    }

    private void openLocation(){
        // 初始化定位
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(this);
            // 设置监听
            mLocationClient.setLocationListener(this);
            // 定位参数
            mLocationOption = new AMapLocationClientOption();
            // 定位精度-高精度
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            // 设置
            mLocationOption.setNeedAddress(true);
            // 关闭wifi强制刷新
            mLocationOption.setWifiActiveScan(false);
            mLocationOption.setOnceLocation(false);
            // 设置定位间隔
            mLocationOption.setInterval(2000);
            // 定位配置
            mLocationClient.setLocationOption(mLocationOption);
        }
        // 开启定位
        mLocationClient.startLocation();
    }

    private void openRoute() {
        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                // 开启搜索，并添加返回
                Routetools.setVisibility(View.GONE);
                startActivityForResult(new Intent(this, MapSearch.class), REQUEST_CODE);
                break;
            case R.id.btn_walk:
                resetBtn();
                getRouteResult(ROUTE_TYPE_WALK, nextmarker);
                btn_walk.setTextColor(Color.BLUE);
                break;
            case R.id.btn_drive:
                resetBtn();
                getRouteResult(ROUTE_TYPE_DRIVE, nextmarker);
                btn_drive.setTextColor(Color.BLUE);
                break;
            case R.id.btn_ride:
                resetBtn();
                getRouteResult(ROUTE_TYPE_RIDE, nextmarker);
                btn_ride.setTextColor(Color.BLUE);
                break;
            case R.id.btn_bus:
                resetBtn();
                getRouteResult(ROUTE_TYPE_BUS, nextmarker);
                btn_bus.setTextColor(Color.BLUE);
                break;
            case R.id.btn_clear:
                aMap.clear();
                Routetools.setVisibility(View.GONE);
                break;
        }
    }

    // 定位变化
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                myLocation = aMapLocation;
                aMapLocation.getLocationType(); // 获取定位来源
                aMapLocation.getLatitude(); // 获取维度
                aMapLocation.getLongitude(); // 获取经度
                aMapLocation.getAccuracy(); // 获取精度信息
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 定位时间
                SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);

                if (isFirstLocation) {
                    // 设置缩放等级
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    // 将地图移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    // 添加图钉
                    // 获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    Log.i("Location-Info", buffer.toString()+"");
                    isFirstLocation = false;
                }
            } else {
                // 输出错误信息
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    // marker信息窗口
    @Override
    public View getInfoWindow(Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.poikeywordsearch_uri,
                null);
        TextView go = view.findViewById(R.id.go);
        go.setText("去这里");
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(marker.getTitle());
        TextView snippet = (TextView) view.findViewById(R.id.snippet);
        snippet.setText(marker.getSnippet());
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    // Marker点击事件
    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();

        Log.e("HH", ""+marker.getPosition().latitude+"||"+marker.getPosition().longitude);
        return false;
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        //Toast.makeText(this, "定位自己", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    public void onPoiSearched(PoiResult Result, int i) {
        dissmissProgressDialog();// 隐藏对话框
        if (i == 1000) {
            if (Result != null && Result.getQuery() != null) {// 搜索poi的结果
                if (Result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = Result;
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息

                    if (poiItems != null && poiItems.size() > 0) {
                        aMap.clear();// 清理之前的图标
                        PoiOverlay poiOverlay = new PoiOverlay(aMap, poiItems);
                        poiOverlay.removeFromMap();
                        poiOverlay.addToMap();
                        poiOverlay.zoomToSpan();
                    } else if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                        showSuggestCity(suggestionCities);
                    } else {
                        ToastUtil.show(MapActivity.this,
                                "无返回");
                    }
                }
            } else {
                ToastUtil.show(MapActivity.this,
                        "无返回");
            }
        } else {
            ToastUtil.showerror(this, i);
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    // 监听返回
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CODE_INPUTTIPS && data
                != null) {
            aMap.clear();
            Tip tip = data.getParcelableExtra(Constants.EXTRA_TIP);
            if (tip.getPoiID() == null || tip.getPoiID().equals("")) {
                doSearchQuery(tip.getName());
            } else {
                addTipMarker(tip);
            }
            keyText.setText(tip.getName());
            if(!tip.getName().equals("")){
                //mCleanKeyWords.setVisibility(View.VISIBLE);
            }
        } else if (resultCode == RESULT_CODE_KEYWORDS && data != null) {
            aMap.clear();
            String keywords = data.getStringExtra(Constants.KEY_WORDS_NAME);
            if(keywords != null && !keywords.equals("")){
                doSearchQuery(keywords);
            }
            keyText.setText(keywords);
            if(!keywords.equals("")){
                //mCleanKeyWords.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery(String keywords) {
        showProgressDialog();// 显示进度框
        currentPage = 1;
        // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query = new PoiSearch.Query(keywords, "", Constants.DEFAULT_CITY);
        // 设置每页最多返回多少条poiitem
        query.setPageSize(10);
        // 设置查第一页
        query.setPageNum(currentPage);

        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }
    // 显示搜索进度
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        progDialog.setMessage("正在搜索:\n" + keyText);
        progDialog.show();
    }
    // 关闭
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    // 添加标记点
    private void addTipMarker(Tip tip) {
        if (tip == null) {
            return;
        }
        marker = aMap.addMarker(new MarkerOptions());
        LatLonPoint point = tip.getPoint();
        if (point != null) {
            LatLng markerPosition = new LatLng(point.getLatitude(), point.getLongitude());
            marker.setPosition(markerPosition);
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, 17));
        }
        marker.setTitle(tip.getName());
        marker.setSnippet(tip.getAddress());
    }

    // 返回推荐内容
    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        ToastUtil.show(MapActivity.this, infomation);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // 暂停绘制地图
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁地图
        mLocationClient.stopLocation();
        mLocationClient.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 重新绘制加载地图
        mapView.onResume();
    }

    // 路线规划中转
    private void getRouteResult(int type, Marker next){
        if (myLocation == null) {
            ToastUtil.show(this, "未获得自身定位！");
            return;
        }
        Routetools.setVisibility(View.VISIBLE);
        Log.i("myLocation",myLocation.getLongitude()+"||"+myLocation.getLatitude());
        LatLonPoint startPoint = new LatLonPoint(myLocation.getLatitude(), myLocation.getLongitude());
        LatLonPoint nextPoint = new LatLonPoint(next.getPosition().latitude, next.getPosition().longitude);
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(startPoint, nextPoint);
        nextmarker = next;
        aMap.clear();
        Log.i("type", ""+type);
        switch (type) {
            case ROUTE_TYPE_BUS:
                RouteSearch.BusRouteQuery busRouteQuery = new RouteSearch.BusRouteQuery(fromAndTo, RouteSearch.BUS_DEFAULT, Constants.DEFAULT_CITY, 0);
                routeSearch.calculateBusRouteAsyn(busRouteQuery);
                break;
            case ROUTE_TYPE_DRIVE:
                RouteSearch.DriveRouteQuery driveRouteQuery = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DRIVING_SINGLE_DEFAULT,
                        null, null, null);
                routeSearch.calculateDriveRouteAsyn(driveRouteQuery);
                break;
            case ROUTE_TYPE_RIDE:
                RouteSearch.RideRouteQuery rideRouteQuery = new RouteSearch.RideRouteQuery(fromAndTo, RouteSearch.RIDING_FAST);
                routeSearch.calculateRideRouteAsyn(rideRouteQuery);
                break;
            case ROUTE_TYPE_WALK:
                RouteSearch.WalkRouteQuery walkRouteQuery = new RouteSearch.WalkRouteQuery(fromAndTo, RouteSearch.WALK_DEFAULT);
                routeSearch.calculateWalkRouteAsyn(walkRouteQuery);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int error) {
        if (error == AMapException.CODE_AMAP_SUCCESS) {
            Log.i("walkRouteResult",""+busRouteResult.getPaths().size());
            if (busRouteResult != null&& busRouteResult.getPaths() != null) {
                if (busRouteResult.getPaths().size() > 0) {
                    BusRouteOverlay busRouteOverlay = new BusRouteOverlay(this,
                            aMap, busRouteResult.getPaths().get(0),
                            busRouteResult.getStartPos(), busRouteResult.getTargetPos());
                    busRouteOverlay.removeFromMap();
                    busRouteOverlay.addToMap();
                    busRouteOverlay.zoomToSpan();
                }
            }
        }else {
            Log.e("error", ""+ error);
        }
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int error) {
        if (error == AMapException.CODE_AMAP_SUCCESS) {
            Log.i("walkRouteResult",""+driveRouteResult.getPaths().size());
            if (driveRouteResult != null&& driveRouteResult.getPaths() != null) {
                if (driveRouteResult.getPaths().size() > 0) {
                    DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(this,
                            aMap, driveRouteResult.getPaths().get(0),
                            driveRouteResult.getStartPos(), driveRouteResult.getTargetPos(), null);
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();
                }
            }
        }else {
            Log.e("error", ""+ error);
        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int error) {
        if (error == AMapException.CODE_AMAP_SUCCESS) {
            Log.i("walkRouteResult",""+walkRouteResult.getPaths().size());
            if (walkRouteResult != null&& walkRouteResult.getPaths() != null) {
                if (walkRouteResult.getPaths().size() > 0) {
                    WalkRouteOverlay walkRouteOverlay = new WalkRouteOverlay(this,
                            aMap, walkRouteResult.getPaths().get(0),
                            walkRouteResult.getStartPos(), walkRouteResult.getTargetPos());
                    walkRouteOverlay.removeFromMap();
                    walkRouteOverlay.addToMap();
                    walkRouteOverlay.zoomToSpan();
                }
            }
        }else {
            Log.e("error", ""+ error);
        }
    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int error) {
        if (error == AMapException.CODE_AMAP_SUCCESS) {
            Log.i("walkRouteResult",""+rideRouteResult.getPaths().size());
            if (rideRouteResult != null&& rideRouteResult.getPaths() != null) {
                if (rideRouteResult.getPaths().size() > 0) {
                    RideRouteOverlay rideRouteOverlay  = new RideRouteOverlay(this,
                            aMap, rideRouteResult.getPaths().get(0),
                            rideRouteResult.getStartPos(), rideRouteResult.getTargetPos());
                    rideRouteOverlay.removeFromMap();
                    rideRouteOverlay.addToMap();
                    rideRouteOverlay.zoomToSpan();
                }
            }
        }else {
            Log.e("error", ""+ error);
        }
    }

    @Override
    public void onInfoWindowClick(Marker point) {
        Log.i("Marker", ""+point.getPosition().longitude+"||"+point.getPosition().latitude);
        if (!isRouting) {
            openRoute();
            getRouteResult(ROUTE_TYPE_WALK, point);
            Routetools.setVisibility(View.VISIBLE);
            isRouting=true;
        }
    }

    private void resetBtn(){
        btn_drive.setTextColor(Color.BLACK);
        btn_bus.setTextColor(Color.BLACK);
        btn_walk.setTextColor(Color.BLACK);
        btn_ride.setTextColor(Color.BLACK);
    }
}
