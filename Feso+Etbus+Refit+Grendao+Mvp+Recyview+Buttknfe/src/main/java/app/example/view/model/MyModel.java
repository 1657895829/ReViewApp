package app.example.view.model;

import app.example.view.app.MyApplication;
import app.example.view.bean.NewsBean;
import app.example.view.view.MyView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * model接口实现类
 */
public class MyModel {

    /**
     * get请求数据
     * @param myView
     */
    public void getData(final MyView myView){
        //设置接口请求的key值
        Call<NewsBean> call = MyApplication.request.get("c4479ad58f41e7f78a8fa073d0b1f1b5");

        //发起异步请求网络数据
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                //获取响应的数据，保存在数据库中
                NewsBean bean = response.body();
                MyApplication.session.getDataBeanDao().insertInTx(bean.getResult().getData());

                //请求成功时返回数据
                myView.onSuccess(bean);
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {
                //请求失败时返回数据
                myView.onFailure(new Exception(""));
            }
        });
    }

    /**
     * post请求
     * @param myView
     */
    public void postData(final MyView myView){
        //设置接口请求的key值
        Call<NewsBean> call = MyApplication.request.post("c4479ad58f41e7f78a8fa073d0b1f1b5");

        //发起异步请求网络数据
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                //获取响应的数据，保存在数据库中
                NewsBean bean = response.body();
                MyApplication.session.getDataBeanDao().insertInTx(bean.getResult().getData());

                //请求成功时返回数据
                myView.onSuccess(bean);
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {
                //请求失败时返回错误信息
                myView.onFailure(new Exception(""));
            }
        });
    }
}
