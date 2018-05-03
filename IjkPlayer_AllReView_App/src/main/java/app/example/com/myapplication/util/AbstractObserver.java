package app.example.com.myapplication.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class AbstractObserver<T> implements Observer<String> {

    public abstract void onSuccess(T t);

    /**
     * 1  当前 网络异常
     * 2  json 解析异常
     * 2  未知异常
     * @param code
     */
    public abstract void onFailure(int code);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String result) {


        try {
            Type type =  getClass().getGenericSuperclass() ;
            Type [] types =  ((ParameterizedType)type).getActualTypeArguments() ;
            Class clazz = (Class) types[0] ;
            Gson gson = new Gson();
            T t = (T)  gson.fromJson(result,clazz);
            onSuccess(t);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            onFailure(2);
        }catch (Exception e){
            e.printStackTrace();
            onFailure(3);
        }

    }

    @Override
    public void onError(Throwable e) {
        onFailure(1);
        System.out.println(e
        );
    }

    @Override
    public void onComplete() {

    }
}
