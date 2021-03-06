package com.example.cart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cart.R;
import com.example.cart.adapter.Recy03Adapter;
import com.example.cart.bean.ShouyeGridBean;
import com.example.cart.presenter.MyPresenter2;
import com.example.cart.view.ViewCallBack2;
import java.util.List;

public class Fragment_vp_2 extends Fragment implements ViewCallBack2 {
    private MyPresenter2 myPresenter2;
    private RecyclerView vp_2recy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_vp_2,container,false);
        vp_2recy = (RecyclerView) view.findViewById(R.id.vp2_recview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myPresenter2 = new MyPresenter2(this);
        myPresenter2.getGrid();
    }

    @Override
    public void success(ShouyeGridBean shouyeGridBean) {
        System.out.println(shouyeGridBean.getData().get(0).getName());
        List<ShouyeGridBean.DataBean> listGrid = shouyeGridBean.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5);
        vp_2recy.setLayoutManager(gridLayoutManager);
        Recy03Adapter recy03Adapter = new Recy03Adapter(getActivity(), listGrid);
        vp_2recy.setAdapter(recy03Adapter);
    }

    @Override
    public void failure(Exception e) {

    }
}
