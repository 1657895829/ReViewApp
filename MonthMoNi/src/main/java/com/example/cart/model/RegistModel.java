package com.example.cart.model;

import com.example.cart.bean.RegistBean;
import com.example.cart.common.APIFactory;
import com.example.cart.common.AbstractObserver;
import com.example.cart.view.RegistViewCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册model层
 */

public class RegistModel {
    public void regist(String mobile, String password, final RegistViewCallBack callBack){
        //http://120.27.23.105/user/reg?mobile=13534245633&password=55555554
        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("mobile",mobile);
        map.put("password",password);
        APIFactory.getInstance().regist("/user/reg", map, new AbstractObserver<RegistBean>() {
            @Override
            public void onSuccess(RegistBean registBean) {
                callBack.success(registBean);
            }

            @Override
            public void onFailure() {
                callBack.failure();
            }
        });
    }
}
