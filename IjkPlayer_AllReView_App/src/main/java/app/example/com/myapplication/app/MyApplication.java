package app.example.com.myapplication.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 全局配置初始化 Fresco 类
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
