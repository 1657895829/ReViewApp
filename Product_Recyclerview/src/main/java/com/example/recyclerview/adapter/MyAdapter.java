package com.example.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.recyclerview.R;
import com.example.recyclerview.bean.ProductBean;
import com.example.recyclerview.model.MyModel;
import com.example.recyclerview.view.MyView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;

    private List<ProductBean.DataBean> list;
    MyModel myModel = new MyModel();
    MyView myView;

    public MyAdapter(Context context, List<ProductBean.DataBean> list, MyView myView) {
        this.context = context;
        this.list = list;
        this.myView = myView;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.adapter_layout, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mPrice.setText("价格：" + list.get(position).getPrice() + "");
        holder.mTime.setText("创建时间：" + list.get(position).getCreatetime());
        holder.mTitle.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.status)
        TextView mStatus;
        @BindView(R.id.price)
        TextView mPrice;
        @BindView(R.id.time)
        TextView mTime;
        @BindView(R.id.quxiao)
        Button mQuxiao;
        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
