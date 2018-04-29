package com.example.recyclerview.presenter;

import com.example.recyclerview.bean.ProductBean;
import com.example.recyclerview.model.MyModel;
import com.example.recyclerview.view.MyView;

/**
 * presenter层
 */
public class MyPresenter {
    MyModel myModel = new MyModel();
    MyView myView;

    public MyPresenter(MyView myView) {
        this.myView = myView;
    }

    //调用model层去访问数据
    public void getDataFromModel(int page){
        myModel.getData(page, new MyView() {
            @Override
            public void success(ProductBean productBean) {
                myView.success(productBean);
            }

            @Override
            public void fail(Exception e) {
                myView.fail(e);
            }
        });
    }

    //防止内存泄漏的问题
    public void detach(){
        this.myView = null;
    }
}
