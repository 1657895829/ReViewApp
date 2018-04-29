package com.example.recyclerview.view;

import com.example.recyclerview.bean.ProductBean;

/**
 * view层接口
 */

public interface MyView {
    public void success(ProductBean productBean);
    public void fail(Exception e);
}
