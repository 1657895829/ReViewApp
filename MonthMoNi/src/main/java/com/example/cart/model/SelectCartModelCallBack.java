package com.example.cart.model;

import com.example.cart.bean.SelectCartBean;

public interface SelectCartModelCallBack {
    public void success(SelectCartBean selectCartBean);
    public void failure();

}
