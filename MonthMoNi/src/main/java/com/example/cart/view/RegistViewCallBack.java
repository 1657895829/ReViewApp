package com.example.cart.view;

import com.example.cart.bean.RegistBean;

/**
 * 注册view层接口
 */

public interface RegistViewCallBack {
    public void success(RegistBean registBean);
    public void failure();
}
