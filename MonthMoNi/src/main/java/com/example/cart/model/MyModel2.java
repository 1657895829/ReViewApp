package com.example.cart.model;

import com.example.cart.bean.ShouyeGridBean;
import com.example.cart.okhttp.AbstractUiCallBack;
import com.example.cart.okhttp.OkhttpUtils;

public class MyModel2 {
    //model层里面调用okhttp封装类 请求数据
    public void getGrid(final ModelCallBack2 modelCallBack2) {
        String path = "http://120.27.23.105/product/getCatagory";
        OkhttpUtils.getInstance().asy(null, path, new AbstractUiCallBack<ShouyeGridBean>() {

            @Override
            public void success(ShouyeGridBean shouyeGridBean) {
                  modelCallBack2.success(shouyeGridBean);
            }

            @Override
            public void failure(Exception e) {
                modelCallBack2.failure(e);
            }
        });
    }
}
