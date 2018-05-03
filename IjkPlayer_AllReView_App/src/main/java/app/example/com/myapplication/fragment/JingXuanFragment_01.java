package app.example.com.myapplication.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;
import app.example.com.myapplication.R;
import app.example.com.myapplication.adapter.MyAdapter;
import app.example.com.myapplication.bean.HomeBean;
import app.example.com.myapplication.presenter.HomePresenter;
import app.example.com.myapplication.util.BannerUtil;
import app.example.com.myapplication.view.HomeView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页 精选
 */
public class JingXuanFragment_01 extends Fragment implements HomeView {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private HomePresenter presenter;
    private View view;
    private Unbinder unbinder;
    private List<HomeBean.RetBean.ListBean.ChildListBean> list = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jingxuan_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        //实例化 p 层，获取数据
        presenter = new HomePresenter(this);
        presenter.success();
        return view;
    }

    //onMultiWindowModeChanged()方法来判断当前activity在多窗口模式的改变状态.
    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        if (isInMultiWindowMode && Build.VERSION.SDK_INT >= 19) {
            View decorView = getActivity().getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        //数据请求成功时添加进集合中
        for (int i = 0;i < homeBean.getRet().getList().size(); i++){
            int length = homeBean.getRet().getList().get(i).getChildList().size();
            for (int j = 0; j < length; j++){
                list.add(homeBean.getRet().getList().get(i).getChildList().get(j));
            }
        }

        //获取影片图片数据，进行无限轮播banner播放
        for (int i = 0; i < list.size(); i++){
            stringList.add(list.get(i).getPic());
        }

        banner.setImageLoader(new BannerUtil());
        banner.setImages(stringList);
        banner.start();

        //设置RecyclerView展示数据的布局管理器以及适配器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        MyAdapter adapter = new MyAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(int code) {
        System.out.println("错误码：" + code);
    }

    //调用p层销毁的方法，防止内存溢出
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detach();
    }
}
