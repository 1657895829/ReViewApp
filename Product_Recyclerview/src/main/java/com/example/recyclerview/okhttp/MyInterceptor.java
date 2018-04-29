package com.example.recyclerview.okhttp;

import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//自定义拦截器类
public class MyInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
/*
        Request request = chain.request();

        //&source=android这个是get带参数
        //?source=android 这个是只能get不带传参,还有就是post
        String url = request.url().toString()+"?source=android";

        Request request1 = request.newBuilder().url(url).build();

        Response response = chain.proceed(request1);
        return response;*/

        //首先取到Request
        Request request = chain.request();
        Response response = null;
        Request requestProcess = null ;

        //判断请求网络方式
        if("GET".equals(request.method())){
            String url =  request.url().toString() + "&source=android";
            requestProcess =  request.newBuilder().url(url).build();

            response = chain.proceed(requestProcess);
        } else {
            FormBody.Builder builder = new FormBody.Builder() ;
            RequestBody requestBody =  request.body() ;
            if(requestBody instanceof FormBody){
                FormBody formBody = (FormBody)requestBody ;
                for (int i=0;i<formBody.size();i++){
                    builder.add(formBody.encodedName(i),formBody.encodedValue(i));
                }
                builder.add("source","android");
            }
            requestProcess =  request.newBuilder().url(request.url().toString()).post(builder.build()).build() ;
            response = chain.proceed(requestProcess);
        }

        return response;
    }
}

