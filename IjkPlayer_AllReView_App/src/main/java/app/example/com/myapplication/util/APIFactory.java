package app.example.com.myapplication.util;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class APIFactory {

    private APIFactory(){}
    private  static APIFactory factory = null ;

    public static APIFactory getInstance(){

        if(factory == null){
            synchronized (APIFactory.class){
                if(factory == null){
                    factory = new APIFactory();
                }
            }
        }
        return factory;
    }



    public void get(String url, Map<String,String> map, Observer<String> observer){
        RetrofitUtils.getInstance().get(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void get(String url,Observer<String> observer){
        RetrofitUtils.getInstance().get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void post(String url,Map<String,String> map,Observer<String> observer){

        RetrofitUtils.getInstance().post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
