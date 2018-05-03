package app.example.com.myapplication.view;

import app.example.com.myapplication.bean.HomeBean;

/**
 * 影片首页数据接口
 */

public interface HomeView {
    public void onSuccess(HomeBean homeBean);
    public void onFailure(int code);
}
