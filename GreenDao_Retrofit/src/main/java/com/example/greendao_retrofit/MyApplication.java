package com.example.greendao_retrofit;

import android.app.Application;
import com.example.greendao_retrofit.dao.DaoMaster;
import com.example.greendao_retrofit.dao.DaoSession;
import org.greenrobot.greendao.database.Database;
/**
 *全局配置初始化数据库
 */

public class MyApplication extends Application {
    //设置session为静态变量
    public static DaoSession session;


    @Override
    public void onCreate() {
        super.onCreate();

        //初始化数据库
        DaoMaster.DevOpenHelper database = new DaoMaster.DevOpenHelper(this, "MyDao");

        //获取数据库
        Database db = database.getWritableDb();

        //操作数据库，等于打开数据库
        session = new DaoMaster(db).newSession();
    }
}
