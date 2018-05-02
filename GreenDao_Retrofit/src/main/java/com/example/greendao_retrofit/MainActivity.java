package com.example.greendao_retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.greendao_retrofit.bean.NewsBean;
import com.example.greendao_retrofit.bean.NewsListBean;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 使用Retrofit封装的方法,操作数据库
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn)
    Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                //创建Retrofit对象
                Retrofit retrofit = new Retrofit.Builder()
                        // 设置 网络请求 Url
                        .baseUrl("http://api.tianapi.com")
                        //设置使用Gson解析(记得加入依赖)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //创建 网络请求接口 的实例
                GetDataInterface request = retrofit.create(GetDataInterface.class);

                //对 发送请求 进行封装
                Call<NewsBean> call = request.getData();

                //发送网络请求（异步）
                call.enqueue(new Callback<NewsBean>() {
                    @Override
                    public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                        //返回的数据
                        NewsBean bean = response.body();
                        System.out.println("新闻："+bean);

                        //通过MyApplication.session 操作数据库。//insertInIx 将集合添加到数据库
                        MyApplication.session.getNewsListBeanDao().insertInTx(bean.getNewslist());

                        //添加完查询一下  loadAll()查询数据库全部内容
                        List<NewsListBean> list = MyApplication.session.getNewsListBeanDao().loadAll();
                        for (NewsListBean listBean : list){
                            System.out.println("数据库数据：" + listBean.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsBean> call, Throwable t) {

                    }
                });


                break;
            default:
                break;
        }
    }
}
