package app.example.com.myapplication.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import app.example.com.myapplication.R;
import app.example.com.myapplication.SecondActivity;
import app.example.com.myapplication.adapter.MyAdapter;
import app.example.com.myapplication.adapter.MyAdapter2;
import app.example.com.myapplication.bean.EventBusBean;
import app.example.com.myapplication.bean.HomeBean;
import app.example.com.myapplication.bean.VideoBean;
import app.example.com.myapplication.presenter.HomePresenter;
import app.example.com.myapplication.presenter.VideoPresenter;
import app.example.com.myapplication.view.HomeView;
import app.example.com.myapplication.view.VideoView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 简介页面
 */

public class Frag_tab1 extends Fragment implements HomeView, VideoView {
    List<HomeBean.RetBean.ListBean.ChildListBean> listBeans = new ArrayList<HomeBean.RetBean.ListBean.ChildListBean>();
    @BindView(R.id.daoyuan)
    TextView daoYan;
    @BindView(R.id.zhuyan)
    TextView zhuYan;
    @BindView(R.id.recylerview)
    RecyclerView recylerView;
    private View view;
    private Unbinder unbinder;
    private HomePresenter homePresenter;
    private VideoPresenter videoPresenter;
    private String id = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.tab_layout1, null);
        unbinder = ButterKnife.bind(this, view);

        //注册EventBus
        EventBus.getDefault().register(this);

        //实例化P层，获取首页数据
        homePresenter = new HomePresenter(this);
        homePresenter.success();

        //实例化P层，获取详情数据
        videoPresenter = new VideoPresenter(this);
        videoPresenter.success(id);

        return view;
    }

    //接收传递的粘性事件信息
    @Subscribe(sticky = true)
    public void receiveMsg(EventBusBean busBean) {
        id = busBean.getPosition();
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        //添加数据
        for (int i = 0; i < homeBean.getRet().getList().size(); i++) {
            int len = homeBean.getRet().getList().get(i).getChildList().size();
            for (int j = 0; j < len; j++) {
                listBeans.add(homeBean.getRet().getList().get(i).getChildList().get(j));
            }
        }

        //条目的点击回调事件
        MyAdapter2 adapter2 = new MyAdapter2(getActivity(), listBeans);
        adapter2.setOnItemClickListener(new MyAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //设置布局管理器以及数据适配器
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        recylerView.setLayoutManager(manager);
        recylerView.setAdapter(adapter2);
    }

    @Override
    public void onFailure(int code) {
        System.out.println("错误码：" + code);
    }

    @Override
    public void onSuccess(VideoBean videoBean) {
        //设置影片详情
        daoYan.setText("导演：" + videoBean.getRet().getDirector());
        zhuYan.setText("主演：" + videoBean.getRet().getActors());
        daoYan.setTextColor(Color.WHITE);
        zhuYan.setTextColor(Color.WHITE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁EventBus
        EventBus.getDefault().unregister(this);
    }
}
