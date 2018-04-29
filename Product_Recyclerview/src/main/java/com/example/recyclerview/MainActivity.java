package com.example.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerview.adapter.MyAdapter;
import com.example.recyclerview.bean.ProductBean;
import com.example.recyclerview.presenter.MyPresenter;
import com.example.recyclerview.view.MyView;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MyView {
    int page = 1;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.springView)
    SpringView springView;
    private MyPresenter presenter;
    private List<ProductBean.DataBean> productList;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //new出P层对象
        presenter = new MyPresenter(this);

        //调用P层的方法
        presenter.getDataFromModel(page);

        //设置布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        //springview的设置,实现下拉刷新展示第一页数据、上拉加载更多实现分页功能
        springView.setHeader(new DefaultHeader(this));
        springView.setFooter(new DefaultFooter(this));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataFromModel(page);
                springView.onFinishFreshAndLoad();

                Toast.makeText(MainActivity.this,"下拉刷新数据成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadmore() {
                page ++ ;
                presenter.getDataFromModel(page);
                springView.onFinishFreshAndLoad();
                Toast.makeText(MainActivity.this,"上拉加载数据成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.recyclerView, R.id.springView})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.recyclerView:
                break;
            case R.id.springView:
                break;
        }
    }

    @Override
    public void success(ProductBean productBean) {
        if (productList == null){
            productList = new ArrayList<>();
        }

        productList.addAll(productBean.getData());

        //设置适配器,实现RecyclerView局部刷新机制，实时刷新订单列表部分数据
        if (adapter == null){
            adapter = new MyAdapter(MainActivity.this, productList, new MyView() {
                @Override
                public void success(final ProductBean productBean) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, productBean.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void fail(Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"网络慢",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void fail(Exception e) {
        System.out.println("异常 : "+e);
    }

    //避免MVP内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
