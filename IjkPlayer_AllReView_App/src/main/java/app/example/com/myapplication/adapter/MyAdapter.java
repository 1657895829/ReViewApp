package app.example.com.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import app.example.com.myapplication.R;
import app.example.com.myapplication.SecondActivity;
import app.example.com.myapplication.bean.EventBusBean;
import app.example.com.myapplication.bean.HomeBean;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView展示数据的适配器
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<HomeBean.RetBean.ListBean.ChildListBean> list;

    public MyAdapter(Context context, List<HomeBean.RetBean.ListBean.ChildListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.adapter, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //加载布局
        holder.mIv.setImageURI(list.get(position).getPic());
        holder.mTv.setText(list.get(position).getTitle());

        //参数的点击事件
        holder.mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用EventBus发送粘性事件传递数据消息
                EventBus.getDefault().postSticky(new EventBusBean(list.get(position).getDataId(),list.get(position).getTitle()));
                context.startActivity(new Intent(context, SecondActivity.class));
            }
        });
        holder.mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用EventBus发送粘性事件传递数据消息
                EventBus.getDefault().postSticky(new EventBusBean(list.get(position).getDataId(),list.get(position).getTitle()));
                context.startActivity(new Intent(context, SecondActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        SimpleDraweeView mIv;
        @BindView(R.id.tv)
        TextView mTv;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
