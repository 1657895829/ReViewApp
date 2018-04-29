package com.example.cart.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cart.R;
import com.example.cart.adapter.ViewPagerAdapter;
import com.example.cart.bean.AddCartBean;
import com.example.cart.presenter.AddCartPresenter;
import com.example.cart.view.AddCartViewListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//详情页面 添加购物车 https://www.zhaoapi.cn/product/addCart
public class XiangQingActivity extends AppCompatActivity implements AddCartViewListener {

    @BindView(R.id.custom_fanhui)
    ImageView custom_fanhui;
    @BindView(R.id.custom_xq_viewpager)
    ViewPager mCustomXqViewpager;
    @BindView(R.id.custom_xq_title)
    TextView customXqTitle;
    @BindView(R.id.custom_xq_bargin_price)
    TextView customXqBarginPrice;
    @BindView(R.id.custom_xq_price)
    TextView customXqPrice;
    @BindView(R.id.jiagou_btn)
    TextView mJiagouBtn;
    private AddCartPresenter presenter;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        ButterKnife.bind(this);

        //拿到传来的参数  images,pid,bargainPrice,title,price
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        String images = intent.getStringExtra("images");
        String bargainPrice = intent.getStringExtra("bargainPrice");
        String title = intent.getStringExtra("title");
        String price = intent.getStringExtra("price");

        //原价设置删除线
        customXqPrice.setText("¥" + price + "");
        customXqTitle.setText(title + "");
        customXqBarginPrice.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        customXqBarginPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
        customXqBarginPrice.getPaint().setAntiAlias(true);// 抗锯齿
        customXqBarginPrice.setText("¥" + bargainPrice + "");

        //图片的集合
        List<String> listImage = new ArrayList<>();
        if (images.contains("|")) {
            //如果需要拆分
            String[] split = images.split("\\|");
            for (int i = 0; i < split.length; i++) {
                listImage.add(split[0]);
            }
        } else {
            listImage.add(images);
        }

        //设置适配器
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.addData(listImage);
        mCustomXqViewpager.setAdapter(viewPagerAdapter);

        //实例化p层
        presenter = new AddCartPresenter(this);
    }

    @Override
    public void success(AddCartBean addCartBean) {
        Toast.makeText(this, "结果：" + addCartBean.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure(Exception e) {
        Toast.makeText(this, "错误:" + e, Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.custom_fanhui, R.id.jiagou_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.custom_fanhui:     //返回按钮，返回上一页面
//                Intent intent = new Intent(XiangQingActivity.this, HomeActivity.class);
//                startActivity(intent);
                finish();
                break;
            case R.id.jiagou_btn:
                presenter.getData(pid);
                break;
        }
    }
}
