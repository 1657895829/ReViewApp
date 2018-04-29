package com.example.cart.presenter;

import com.example.cart.bean.DeleteBean;
import com.example.cart.model.DeleteCartModel;
import com.example.cart.model.DeleteCartModelCallBack;
import com.example.cart.view.DeleteCartListener;

/**
 * 删除购物车p层
 */
public class DeleteCartPresenter {

    DeleteCartModel deleteCartModel = new DeleteCartModel();
    DeleteCartListener deleteCartListener;
    public DeleteCartPresenter(DeleteCartListener deleteCartListener) {
        this.deleteCartListener = deleteCartListener;
    }

    public void delete(String pid) {
        deleteCartModel.delete(pid, new DeleteCartModelCallBack() {
            @Override
            public void success(DeleteBean deleteBean) {
                deleteCartListener.success(deleteBean);
            }

            @Override
            public void failure() {
                deleteCartListener.failure();
            }
        });
    }
}
