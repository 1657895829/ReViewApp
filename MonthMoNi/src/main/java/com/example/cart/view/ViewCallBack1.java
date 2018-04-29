package com.example.cart.view;

import com.example.cart.bean.ShouyeLunBoBean;

/**
 * 轮播图的接口
 */
public interface ViewCallBack1 {

    public void success(ShouyeLunBoBean shouyeLunBoBean);
    public void failure(Exception e);
}
