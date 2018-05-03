package app.example.com.myapplication.model;

import app.example.com.myapplication.bean.HomeBean;
import app.example.com.myapplication.util.APIFactory;
import app.example.com.myapplication.util.AbstractObserver;
import app.example.com.myapplication.view.HomeView;

/**
 * 影片首页model层
 */
public class HomeModel {

    //获取影片数据
    public void success(final HomeView homeView){
        APIFactory.getInstance().get("front/homePageApi/homePage.do", new AbstractObserver<HomeBean>() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                homeView.onSuccess(homeBean);
            }

            @Override
            public void onFailure(int code) {
                homeView.onFailure(code);
            }
        });
    }
}
