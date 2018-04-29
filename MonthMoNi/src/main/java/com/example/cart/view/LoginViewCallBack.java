package com.example.cart.view;

import com.example.cart.bean.LoginBean;

/**
 * 登录view层接口
 */

public interface LoginViewCallBack {
    public void success(LoginBean loginBean);
    public void failure();
}
