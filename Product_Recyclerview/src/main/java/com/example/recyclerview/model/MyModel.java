package com.example.recyclerview.model;

import com.example.recyclerview.bean.ProductBean;
import com.example.recyclerview.okhttp.AbstractUiCallBack;
import com.example.recyclerview.okhttp.OkhttpUtils;
import com.example.recyclerview.view.MyView;

import java.util.HashMap;
import java.util.Map;

/**
 * model层
 */

public class MyModel {
    //请求数据的方法
    public void getData(int page, final MyView callBack){
        //调用封装好的okhttp类
        Map<String, String> map = new HashMap<>();
        map.put("uid","71");
        map.put("page",page+"");

        //String path="https://www.zhaoapi.cn/product/getOrders?uid=71&page="+page;
        String path = "https://www.zhaoapi.cn/product/getOrders";

        OkhttpUtils.getInstance().asy(map, path, new AbstractUiCallBack<ProductBean>() {
            @Override
            public void success(ProductBean productBean) {
                //成功返回数据
                callBack.success(productBean);
            }

            @Override
            public void fail(Exception e) {
                callBack.fail(e);
            }
        });
    }


}
