package app.example.com.reviewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
/**
 * EventBus是一款针对Android优化的发布/订阅事件总线。主要功能是替代Intent,Handler,BroadCast在Fragment，Activity，Service，线程之间传递消息.
 * 优点是开销小，代码更优雅。以及将发送者和接收者解耦。
 * 使用先加依赖：compile 'org.greenrobot:eventbus:3.0.0'
 */

/**
 * 本Model具体需求：
 * 当击btn_try按钮的时候，跳到第二个Activity，
 * 当点击第二个activity上面的First Event按钮的时候向第一个Activity发送消息，
 * 当第一个Activity收到消息后，一方面将消息Toast显示，一方面放入textView中显示。
 */

/**
 * 1.2.MainActivity.java （点击btn跳转到第二个Activity）
 * 我们是要在MainActivity中接收发过来的消息的，所以我们在MainActivity中注册消息。
 通过我们会在OnCreate()函数中注册EventBus，在OnDestroy（）函数中反注册。所以整体的注册与反注册的代码如下：
 */
public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册EventBus
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        btn = findViewById(R.id.btn_try);
        tv = findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 接收消息时，我们使用EventBus中最常用的onEventMainThread（）函数来接收消息。
     在MainActivity中重写onEventMainThread（FirstEvent event），参数就是我们自己定义的类：
     在收到Event实例后，我们将其中携带的消息取出，一方面Toast出去，一方面传到TextView中；
     * @param event
     */
    @Subscribe
    public void onEventMainThread(EventClass event){
        String msg = "onEventMainThread收到了消息" + event.getMsg();
        Log.d("onEventMainThread",msg);
        tv.setText(msg);

        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    //反注册EventBus
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
