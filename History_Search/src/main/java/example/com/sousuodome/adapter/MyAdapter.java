package example.com.sousuodome.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import example.com.sousuodome.Bean.LieBiaoBean;
import example.com.sousuodome.R;

/**
 * Created by lenovo on 2018/4/28.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<LieBiaoBean.DataBean> list;

    public MyAdapter(Context context, List<LieBiaoBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_liebiao,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final LieBiaoBean.DataBean dataBean = list.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        String[] split = dataBean.getImages().split("\\|");
        Glide.with(context).load(split[0]).into(myViewHolder.iv);
        myViewHolder.title.setText(dataBean.getTitle());
        myViewHolder.pricelod.setText(dataBean.getSalenum()+"");
        myViewHolder.pricenew.setText(dataBean.getPrice()+"");
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页面
                /*Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("pid",dataBean.getPid()+"");
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView title;
        private final TextView pricelod;
        private final TextView pricenew;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.rv_item_iv);
            title = itemView.findViewById(R.id.rv_item_title);
            pricelod = itemView.findViewById(R.id.rv_item_pricelod);
            pricelod.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            pricenew = itemView.findViewById(R.id.rv_item_pricenew);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
