package example.com.sousuodome.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by lenovo on 2018/4/28.
 */

public class HttpUtils {
    private static volatile HttpUtils httpUtils;
    private OkHttpClient okHttpClient;
    private static Context context;

    private HttpUtils(Context context) {
        this.context = context;
        //创建OKhttpClient和拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }
    //单例模式
    public static HttpUtils getHttpUtils(Context context){
        if (httpUtils == null){
            synchronized (HttpUtils.class){
                if (httpUtils == null){
                    httpUtils = new HttpUtils(context);
                }
            }
        }
        return httpUtils;
    }
    //Get
    public void doGet(String url, Callback callback){
        //此处应该放置判断网络
        if (!NetworkUtils.isAvailable(context)) {
            Toast.makeText(context,"网络连接超时!!",Toast.LENGTH_SHORT).show();
            return;
        }
        Request request = new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    //post
    public void doPost(String url, Map<String,String> params, Callback callback){
        //此处应该放置判断网络
        if (!NetworkUtils.isAvailable(context)) {
            Toast.makeText(context,"网络连接超时!!",Toast.LENGTH_SHORT).show();
            return;
        }
        //判断参数
        if (params == null || params.size() == 0) {
            throw new RuntimeException("params is null！！！");
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
