package com.example.pullrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pullrecyclerview.view.HeaderListView;

public class MainActivity extends Activity {
    // 定义显示的数据
    public enum Data{
        item_1("及时雨", "宋江", R.mipmap.q),
        item_2("玉麒麟", "卢俊义", R.mipmap.q),
        item_3("军师", "吴用", R.mipmap.q),
        item_4("豹子头", "林冲", R.mipmap.q),
        item_5("花和尚", "鲁智深", R.mipmap.q),
        item_6("行者", "武松", R.mipmap.q),
        item_7("黑旋风", "李逵", R.mipmap.q),
        item_8("大刀", "关胜", R.mipmap.q),
        item_9("娘子军", "孙二娘", R.mipmap.q),
        item_10("母夜叉", "顾大嫂", R.mipmap.q),
        item_11("千里马", "戴宗", R.mipmap.q),
        item_12("夺命锁", "索超", R.mipmap.q);

        private String name;
        private String content;
        private int resId;
        private Data(String name, String content, int resId) {
            this.name = name;
            this.content = content;
            this.resId = resId;
        }

        public String getName() {
            return name;
        }

        public int getResId() {
            return resId;
        }

        public String getContent() {
            return content;
        }
    }

    private HeaderListView lv_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initView();
    }

    //使用自定义控件，往头部添加布局，设置适配器
    private void initView() {
        lv_header = (HeaderListView)findViewById(R.id.lv_header);
        initHeaderView();// 要在 adapter 前设置
        MyAdapter myAdapter = new MyAdapter(this);
        lv_header.setAdapter(myAdapter);
    }

    private void initHeaderView(){
        View headerView = getLayoutInflater().inflate(R.layout.layout_header_view, null);
        ImageView iv_header = (ImageView)headerView.findViewById(R.id.iv_header); // 传入
        lv_header.setHeaderIV(iv_header);
        lv_header.addHeaderView(headerView);
    }

    //自定义适配器
    public class MyAdapter extends BaseAdapter {
        public MyAdapter(Context context){

        }

        @Override
        public int getCount() {
            return Data.values().length;
        }

        @Override
        public Object getItem(int position) {
            return Data.values()[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.item_layout, parent, false);
                viewHolder = new ViewHolder();
                // 初始化
                viewHolder.iv = (ImageView)convertView.findViewById(R.id.iv);
                viewHolder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
                viewHolder.tv_content = (TextView)convertView.findViewById(R.id.tv_content);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)convertView.getTag();
            }
            // 设置数据
            Data data = Data.values()[position];
            viewHolder.iv.setImageResource(data.resId);
            viewHolder.tv_name.setText(data.getName());
            viewHolder.tv_content.setText(data.getContent());
            return convertView;
        }

        class ViewHolder{
            ImageView iv;
            TextView tv_name;
            TextView tv_content;
        }
    }
}