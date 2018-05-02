package com.example.gaodemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

/**
    key：5ec5dacc95ae78790ecd0165eb114f51

    地图图层有3种：
    MAP_TYPE_NAVI   导航地图
    MAP_TYPE_NIGHT  夜景地图
    MAP_TYPE_NORMAL 白昼地图（即普通地图）
    MAP_TYPE_SATELLITE  卫星图
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        AMap aMap = mapView.getMap();
    }
}
