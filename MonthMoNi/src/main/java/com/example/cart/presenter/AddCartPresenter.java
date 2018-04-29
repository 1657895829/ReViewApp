package com.example.cart.presenter;

import com.example.cart.bean.AddCartBean;
import com.example.cart.model.AddCartModel;
import com.example.cart.model.AddCartModelCallBack;
import com.example.cart.view.AddCartViewListener;

/**
 * 加入购物车 p 层
 */

public class AddCartPresenter {
    private AddCartViewListener viewListener;
    private AddCartModel model;

    public AddCartPresenter(AddCartViewListener viewListener) {
        this.viewListener = viewListener;
        this.model = new AddCartModel();
    }

    public void getData(String pid){
        model.getData(pid, new AddCartModelCallBack() {
            @Override
            public void success(AddCartBean addCartBean) {
                viewListener.success(addCartBean);
            }

            @Override
            public void failure(Exception e) {
                viewListener.failure(e);
            }
        });
    }
}
