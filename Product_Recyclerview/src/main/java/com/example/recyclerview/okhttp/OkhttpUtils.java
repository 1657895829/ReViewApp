package com.example.recyclerview.okhttp;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

//OKhttp单例封装类
public class OkhttpUtils {

    private static OkhttpUtils okhttpUtils = null;

    private OkhttpUtils(){

    }

    public static OkHttpClient client;
    public static OkhttpUtils getInstance(){
        if(okhttpUtils==null){
            okhttpUtils = new OkhttpUtils();
            client = new OkHttpClient.Builder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20,TimeUnit.SECONDS)
                    .connectTimeout(20,TimeUnit.SECONDS)
                    .addInterceptor(new MyInterceptor())
                    .build();
        }
        return okhttpUtils;
    }

    public void asy(Map<String,String> params,String url,AbstractUiCallBack callBack){
        Request request = null;

        if(params!=null){
            FormBody.Builder builder = new FormBody.Builder();
            for(Map.Entry<String,String> entry : params.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }

            FormBody body = builder.build();
            request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
        }else{
            request = new Request.Builder()
                    .url(url)
                    .build();
        }

        client.newCall(request).enqueue(callBack);
    }
}
