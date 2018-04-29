package com.example.cart.view;


import com.example.cart.bean.DeleteBean;

public interface DeleteCartListener {
    public void success(DeleteBean deleteBean);
    public void failure();
}
