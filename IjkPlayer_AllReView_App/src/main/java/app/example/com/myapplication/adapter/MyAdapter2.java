package app.example.com.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import app.example.com.myapplication.R;
import app.example.com.myapplication.bean.HomeBean;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 第二页面 详情评论页  适配器
 */
public class MyAdapter2 extends RecyclerView.Adapter implements View.OnClickListener{
    Context context;
    List<HomeBean.RetBean.ListBean.ChildListBean> listBeans;
    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public MyAdapter2(Context context, List<HomeBean.RetBean.ListBean.ChildListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.adapter_item, null);
        MyHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder holder1 = (MyHolder) holder;
        holder1.simp.setImageURI(listBeans.get(position).getPic());
        holder1.text.setText(listBeans.get(position).getTitle());
        holder1.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (listBeans != null) {
            return listBeans.size();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.simp)
        SimpleDraweeView simp;
        @BindView(R.id.text)
        TextView text;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
