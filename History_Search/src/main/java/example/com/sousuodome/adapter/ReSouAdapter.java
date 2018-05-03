package example.com.sousuodome.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.com.sousuodome.Main2Activity;
import example.com.sousuodome.MainActivity;
import example.com.sousuodome.R;

/**
 * Created by lenovo on 2018/4/28.
 */

public class ReSouAdapter extends RecyclerView.Adapter<ReSouAdapter.ViewHolder>{
    private Context context;
    private List<String> list;

    public ReSouAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_sousuo, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String s = list.get(position);
        holder.tv.setText(s);

        //4.	点击效果图一上的任意搜索记录，跳转到购物车界面
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,Main2Activity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.sousuohorizontaltv);
        }
    }
}
