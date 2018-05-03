package app.example.view.view;

import app.example.view.bean.NewsBean;

/**
 * view层接口类，请求成功与失败的方法
 */
public interface MyView {
     public void onSuccess(NewsBean newsBean);
     public void onFailure(Exception e);
}
