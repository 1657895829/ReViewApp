package com.example.gaodemap_threebutton;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
    可参考博客：http://blog.csdn.net/it666dhw/article/details/78535067
    key：5ec5dacc95ae78790ecd0165eb114f51
    地图图层有3种：
    MAP_TYPE_NAVI   导航地图
    MAP_TYPE_NIGHT  夜景地图
    MAP_TYPE_NORMAL 白昼地图（即普通地图）
    MAP_TYPE_SATELLITE  卫星图
 */
public class MainActivity extends AppCompatActivity implements LocationSource, AMapLocationListener {
    //AMap是地图对象
    private AMap aMap;
    private MapView mapView;
    //声明AMapLocationClient类对象，定位发起端
    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //声明mListener对象，定位监听器
    private OnLocationChangedListener mListener = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    private Button night;
    private Button planet;
    private Button standard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mapView = (MapView) findViewById(R.id.map);
        night = (Button)findViewById(R.id.night);
        planet = (Button)findViewById(R.id.planet);
        standard = (Button)findViewById(R.id.standard);

        //夜间模式地图
        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图，aMap是地图控制器对象。
            }
        });

        //卫星模式地图
        planet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式，aMap是地图控制器对象。
            }
        });

        //标准模式地图
        standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);//设置白昼地图（即普通地图）
            }
        });

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }
        //开始定位
        location();
    }

    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //显示实时路况图层，aMap是地图控制器对象。
        aMap.setTrafficEnabled(true);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude(); //获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy(); //获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);            //定位时间
                aMapLocation.getAddress();  //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();  //国家信息
                aMapLocation.getProvince(); //省信息
                aMapLocation.getCity();     //城市信息
                aMapLocation.getDistrict(); //城区信息
                aMapLocation.getStreet();   //街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode(); //城市编码
                aMapLocation.getAdCode();   //地区编码

                //3.实现高德地图定位功能，并在控制台打印坐标信息；
                System.out.println("定位信息 1 ："+aMapLocation.getAddress());
                System.out.println("定位信息 2 ："+aMapLocation.getCountry()+aMapLocation.getProvince()+aMapLocation.getCity()+aMapLocation.getDistrict()+aMapLocation.getStreet()+aMapLocation.getStreetNum());
                System.out.println("经度："+aMapLocation.getLongitude()+"      纬度："+aMapLocation.getLatitude());

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);

                    // 添加图钉 Marker，资源使用应用图标,设置当前地图显示为当前位置
                    //aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()), 19));
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                    markerOptions.visible(true);
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_location2));
                    markerOptions.icon(bitmapDescriptor);
                    aMap.addMarker(markerOptions);

                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                }

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }
}