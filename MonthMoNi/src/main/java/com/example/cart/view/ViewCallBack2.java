package com.example.cart.view;


import com.example.cart.bean.ShouyeGridBean;

/**
 * 九宫格的接口
 */

public interface ViewCallBack2 {
    public void success(ShouyeGridBean shouyeGridBean);
    public void failure(Exception e);
}
