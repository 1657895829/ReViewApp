package com.example.cart.model;

import com.example.cart.bean.ShouyeLunBoBean;

/**
 * model层轮播图的接口
 */

public interface ModelCallBack1 {

    public void success(ShouyeLunBoBean shouyeLunBoBean);
    public void failure(Exception e);
}
