package com.example.cart.model;

import com.example.cart.bean.ShouyeGridBean;

/**
 * model层九宫格的接口
 */
public interface ModelCallBack2 {

    public void success(ShouyeGridBean shouyeGridBean);
    public void failure(Exception e);
}
