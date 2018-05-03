package app.example.com.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import app.example.com.myapplication.adapter.MyFragmentPagerAdapter;
import app.example.com.myapplication.bean.EventBusBean;
import app.example.com.myapplication.bean.VideoBean;
import app.example.com.myapplication.presenter.VideoPresenter;
import app.example.com.myapplication.view.VideoView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity implements VideoView {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.title_top)
    TextView titleTop;
    private VideoPresenter presenter;
    private String id = "";
    private PlayerView player;
    private TabLayout.Tab two;
    private TabLayout.Tab one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        //注册EventBus
        EventBus.getDefault().register(this);

        //实例化 p 层，获取数据
        presenter = new VideoPresenter(this);
        presenter.success(id);

        //tab与viewpager与fragment
        initView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    //接收传递的粘性事件信息
    @Subscribe(sticky = true)
    public void receiveMsg(EventBusBean busBean) {
        id = busBean.getPosition();

        //设置传递的影片名称
        titleTop.setText(busBean.getText());
        Toast.makeText(SecondActivity.this, busBean.getText() + busBean.getPosition(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(VideoBean videoBean) {
        //使用PlayerView类 获取 视频url 进行播放
        String url = videoBean.getRet().getSmoothURL();
        player = new PlayerView(this)
                .setTitle("标题")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(url);
        player.startPlay();
    }

    @Override
    public void onFailure(int code) {
        System.out.println("错误码：" + code);
    }

    @OnClick(R.id.back)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

            // 点击返回按钮，返回至上一页
            case R.id.back:
                finish();
                break;
        }
    }

    //设置viewpager的选项卡
    private void initView() {
        //使用适配器将ViewPager与Fragment绑定在一起
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //将 TabLayout 与 ViewPager 绑定在一起
        tabLayout.setupWithViewPager(viewPager);

        //指定tab选项卡的位置
        one = tabLayout.getTabAt(0);
        two = tabLayout.getTabAt(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁EventBus
        EventBus.getDefault().unregister(this);
    }
}
