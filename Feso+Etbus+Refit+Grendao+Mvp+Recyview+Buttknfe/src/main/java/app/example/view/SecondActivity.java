package app.example.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import app.example.view.bean.EventBusBean;
import butterknife.BindView;
import butterknife.ButterKnife;

//EventBus的传值通信接收页
public class SecondActivity extends AppCompatActivity {
    @BindView(R.id.draweeView)
    SimpleDraweeView mDraweeView;
    @BindView(R.id.title)
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        //初始化EvEntBus，注册EventBus，订阅粘性事件
        EventBus.getDefault().register(this);
    }

    //主线程UI,注册粘性订阅事件，粘性事件处理函数
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)  //这种写法能达到粘性的目的
    public void receiveSticky(EventBusBean busBean){
        mDraweeView.setImageURI(busBean.getUrl());
        mTitle.setText(busBean.getTitle());
    }

    /**
     * 在onDestory（）方法中取消EventBus的订阅；防止内存溢出
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
}
