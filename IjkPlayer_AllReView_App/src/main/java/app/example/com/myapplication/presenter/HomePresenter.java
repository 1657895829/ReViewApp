package app.example.com.myapplication.presenter;

import app.example.com.myapplication.bean.HomeBean;
import app.example.com.myapplication.model.HomeModel;
import app.example.com.myapplication.view.HomeView;

/**
 * 影片首页p层
 */
public class HomePresenter {
    private HomeModel homeModel;
    private HomeView homeView;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        homeModel = new HomeModel();
    }

    //获取影片首页数据
    public void success(){
        homeModel.success(new HomeView() {
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

    //关闭p层，避免内存泄露
    public void detach(){
        this.homeView = null;
    }
}
