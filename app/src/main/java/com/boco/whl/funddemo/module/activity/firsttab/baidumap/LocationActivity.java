package com.boco.whl.funddemo.module.activity.firsttab.baidumap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.boco.whl.funddemo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationActivity extends Activity {

    @BindView(R.id.location_map)
    MapView locationMap;
    @BindView(R.id.location_tv)
    TextView locationTv;
    @BindView(R.id.download_offline)
    Button downloadOffline;
    private BaiduMap mBaiduMap;
    private LocationClient locationClient;
    private PoiInfo poiInfo = new PoiInfo();
    private BitmapDescriptor mCurrentMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mBaiduMap = locationMap.getMap();
        locationMap.showZoomControls(false);
        initLocation(true);
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
                locationTv.setText("正在获取报警地点");
            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                String _str = mapStatus.toString();
                String _regex = "target lat: (.*)\ntarget lng";
                String _regex2 = "target lng: (.*)\ntarget screen x";
                String _latitude = latlng(_regex, _str);
                String _longitude = latlng(_regex2, _str);
                System.out.println(_latitude + "," + _longitude);
                LatLng latLng = new LatLng(Double.parseDouble(_latitude), Double.parseDouble(_longitude));
                GeoCoder mGeoCoder = GeoCoder.newInstance();
                mGeoCoder
                        .setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                            @Override
                            public void onGetReverseGeoCodeResult(
                                    ReverseGeoCodeResult arg0) {
                                locationTv.setText(arg0.getSematicDescription().replace("辽宁省", ""));
                            }

                            @Override
                            public void onGetGeoCodeResult(
                                    GeoCodeResult arg0) {
                            }
                        });
                mGeoCoder.reverseGeoCode(new ReverseGeoCodeOption()
                        .location(latLng));
            }
        });
    }

    /**
     * @param regexStr
     * @param str
     * @return经纬度提取
     */
    private String latlng(String regexStr, String str) {
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            str = matcher.group(1);
        }
        return str;
    }

    /**
     * @param isZoom (定位功能)
     */
    private void initLocation(final boolean isZoom) {
        mBaiduMap.setMyLocationEnabled(true);// 开启定位图层
        locationClient = new LocationClient(this);// 定位初始化
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                if (bdLocation == null || locationMap == null) {
                    return;
                }
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(100).latitude(bdLocation.getLatitude())
                        .longitude(bdLocation.getLongitude()).build();
                mBaiduMap.setMyLocationData(locData);
                poiInfo.location = new LatLng(bdLocation.getLatitude(),
                        bdLocation.getLongitude());
                GeoCoder mGeoCoder = GeoCoder.newInstance();
                mGeoCoder
                        .setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                            @Override
                            public void onGetReverseGeoCodeResult(
                                    ReverseGeoCodeResult arg0) {
                                poiInfo.name = arg0.getSematicDescription();
                                locationTv.setText(arg0.getSematicDescription().replace("辽宁省", ""));
                            }

                            @Override
                            public void onGetGeoCodeResult(GeoCodeResult arg0) {
                            }
                        });
                mGeoCoder.reverseGeoCode(new ReverseGeoCodeOption()
                        .location(poiInfo.location));
                if (isZoom) {
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(
                            new LatLng(bdLocation.getLatitude(), bdLocation
                                    .getLongitude())).zoom(18.0f);
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory
                            .newMapStatus(builder.build()));
                }
                locationClient.stop();
            }
        });

        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.location);
        mBaiduMap
                .setMyLocationConfigeration(new MyLocationConfiguration(
                        MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker
                ));
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        locationClient.setLocOption(option);
        locationClient.start();
    }

    @OnClick(R.id.download_offline)
    public void onViewClicked() {

    }
}
