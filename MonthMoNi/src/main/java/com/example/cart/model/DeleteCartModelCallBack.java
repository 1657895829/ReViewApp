package com.example.cart.model;

import com.example.cart.bean.DeleteBean;

public interface DeleteCartModelCallBack {
    public void success(DeleteBean deleteBean);
    public void failure();
}
