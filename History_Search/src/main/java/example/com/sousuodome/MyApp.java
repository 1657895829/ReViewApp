package example.com.sousuodome;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.greendao.database.Database;

import example.com.sousuodome.greendao.DaoMaster;
import example.com.sousuodome.greendao.DaoSession;

/**
 * Created by lenovo on 2018/4/28.
 */

public class MyApp extends Application{
    public static final boolean ENCRYPTED = true;
    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        /*PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                System.out.println("deviceToken = " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
        PlatformConfig.setWeixin("","");
        PlatformConfig.setQQZone("1106526118","uVPEG59tvi9d3IyG");
        PlatformConfig.setSinaWeibo("","","");*/


        Fresco.initialize(this);
        DaoMaster.DevOpenHelper helper = new  DaoMaster.DevOpenHelper(this, ENCRYPTED ? "users_select" : "users_select-db");
        Database db =  helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
    /**
     * 分割 Dex 支持
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }
}
