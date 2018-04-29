package app.example.com.reviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

/**
 * 发送消息是使用EventBus中的Post方法来实现发送的，发送过去的是我们新建的类的实例！
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btn_first_event = findViewById(R.id.btn_first_event);
        btn_first_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 发送过去的是我们新建的类的实例
                EventBus.getDefault().post(new EventClass("FirstEvent btn clicked"));
            }
        });
    }
}
