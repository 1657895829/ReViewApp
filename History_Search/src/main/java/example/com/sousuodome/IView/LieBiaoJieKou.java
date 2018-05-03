package example.com.sousuodome.IView;

import android.content.Context;

import java.util.List;
import java.util.Map;

import example.com.sousuodome.Bean.LieBiaoBean;

/**
 * Created by lenovo on 2018/4/28.
 */

public interface LieBiaoJieKou {

    interface ILieBiao {
        void showList(List<LieBiaoBean.DataBean> dataBeanList);
        void showSelectList(List<LieBiaoBean.DataBean> dataBeanList);

    }

    interface ILieBiaosModel {
        void getInfoDetails(Context context, Map<String,String> params, OnNetListener<LieBiaoBean> onNetListener);
        void getOrders(Context context, String keywords, String status, String page, OnNetListener<LieBiaoBean> onNetWorkListener);
    }
}
