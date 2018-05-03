package app.example.view.presenter;

import app.example.view.bean.NewsBean;
import app.example.view.model.MyModel;
import app.example.view.view.MyView;

/**
 * Presenter层，进行view层与model数据的交互
 */
public class MyPresenter {
    private MyView myView;
    private MyModel myModel;

    public MyPresenter(MyView myView) {
        this.myView = myView;
        myModel = new MyModel();
    }

    /**
     * get请求数据交互
     */
    public void get(){
       myModel.getData(new MyView() {
           @Override
           public void onSuccess(NewsBean newsBean) {
               //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onSuccess(newsBean);
                }
           }

           @Override
           public void onFailure(Exception e) {
               //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onFailure(e);
                }
           }
       });
    }

    /**
     * post请求方式
     */
    public void post(){
       myModel.postData(new MyView() {
           @Override
           public void onSuccess(NewsBean newsBean) {
               //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onSuccess(newsBean);
                }
           }

           @Override
           public void onFailure(Exception e) {
               //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onFailure(e);
                }
           }
       });
    }
}
