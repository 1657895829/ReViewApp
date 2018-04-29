package com.example.cart.view;


import com.example.cart.bean.SelectCartBean;

public interface SelectCartViewListener {
    public void success(SelectCartBean selectCartBean);
    public void failure();

}
