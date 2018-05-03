package app.example.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import app.example.view.adapter.MyAdapter;
import app.example.view.bean.NewsBean;
import app.example.view.presenter.MyPresenter;
import app.example.view.view.MyView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MyView{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MyPresenter presenter;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //创建presenter层实例，与view层交互数据
        presenter = new MyPresenter(this);

        //get请求方式
        presenter.get();

        //post请求方式
        presenter.post();

        //设置布局管理器以及数据适配器
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new MyAdapter(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSuccess(NewsBean newsBean) {
        //请求成功时添加数据
        adapter.addData(newsBean);
    }

    @Override
    public void onFailure(Exception e) {
        System.out.println("数据出错：" + e);
    }
}
