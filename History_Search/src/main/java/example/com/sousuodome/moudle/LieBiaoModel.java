package example.com.sousuodome.moudle;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import example.com.sousuodome.utils.Api;
import example.com.sousuodome.utils.HttpUtils;
import example.com.sousuodome.Bean.LieBiaoBean;
import example.com.sousuodome.IView.LieBiaoJieKou;
import example.com.sousuodome.IView.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/4/28.
 */

public class LieBiaoModel implements LieBiaoJieKou.ILieBiaosModel{

    protected Handler handler = new Handler(Looper.getMainLooper());
    private LieBiaoBean detailsBean;

    @Override
    public void getInfoDetails(Context context, Map<String, String> params, final OnNetListener<LieBiaoBean> onNetListener) {
        HttpUtils.getHttpUtils(context).doPost(Api.PRODUCT_CATAGORY_LIST, params, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onFailure(e);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                detailsBean = new Gson().fromJson(str, LieBiaoBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(detailsBean);
                    }
                });
            }
        });
    }

    @Override
    public void getOrders(Context context, String keywords, String sort, String page, final OnNetListener<LieBiaoBean> onNetWorkListener) {
        Map<String, String> params = new HashMap<>();
        params.put("keywords", keywords);
        if (!sort.equals("")){
            params.put("sort", sort);
        }else if (!page.equals("")){
            params.put("page", page);
        }
        HttpUtils.getHttpUtils(context).doPost(Api.SEARCHPRODUCTS, params, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetWorkListener.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final LieBiaoBean lieBiaoBean = new Gson().fromJson(string, LieBiaoBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetWorkListener.onSuccess(lieBiaoBean);
                    }
                });
            }
        });
    }
}
