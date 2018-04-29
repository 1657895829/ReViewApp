package com.example.cart.model;

import com.example.cart.bean.ShouyeLunBoBean;
import com.example.cart.okhttp.AbstractUiCallBack;
import com.example.cart.okhttp.OkhttpUtils;


public class MyModel1 {
    //model层里面调用okhttp封装类 请求数据
    public void getLunBo(final ModelCallBack1 modelCallBack1) {
        String path = "http://120.27.23.105/ad/getAd";
        OkhttpUtils.getInstance().asy(null, path, new AbstractUiCallBack<ShouyeLunBoBean>() {

            @Override
            public void success(ShouyeLunBoBean shouyeLunBoBean) {
                modelCallBack1.success(shouyeLunBoBean);
            }

            @Override
            public void failure(Exception e) {
                modelCallBack1.failure(e);
            }
        });
    }
}
