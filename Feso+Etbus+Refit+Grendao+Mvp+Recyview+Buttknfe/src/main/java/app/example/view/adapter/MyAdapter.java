package app.example.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
import app.example.view.R;
import app.example.view.SecondActivity;
import app.example.view.bean.DataBean;
import app.example.view.bean.EventBusBean;
import app.example.view.bean.NewsBean;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义RecyclerView适配器类，展示列表数据
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<DataBean> list;

    public MyAdapter(Context context) {
        this.context = context;
    }

    //数据不为空时请求时添加数据
    public void addData(NewsBean bean){
        if (list == null){
            list = new ArrayList<>();
        }
        list.addAll(bean.getResult().getData());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //添加布局视图
        View view = View.inflate(context, R.layout.item_layout, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mDraweeView.setImageURI(list.get(position).getThumbnail_pic_s());
        holder.mTitle.setText(list.get(position).getTitle());

        //点击跳转传值
        holder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //携带实体类数据，实现点击跳转传值，发送粘性事件
                EventBusBean bean = new EventBusBean(list.get(position).getThumbnail_pic_s(), list.get(position).getTitle());
                EventBus.getDefault().postSticky(bean);
                context.startActivity(new Intent(context, SecondActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.draweeView)
        SimpleDraweeView mDraweeView;
        @BindView(R.id.title)
        TextView mTitle;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
