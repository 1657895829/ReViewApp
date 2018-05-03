package example.com.sousuodome.present;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;

import example.com.sousuodome.Bean.LieBiaoBean;
import example.com.sousuodome.IView.LieBiaoJieKou;
import example.com.sousuodome.IView.OnNetListener;
import example.com.sousuodome.moudle.LieBiaoModel;

/**
 * Created by lenovo on 2018/4/28.
 */

public class LieBiaoPresenter {
    private LieBiaoModel lieBiaoModel;
    private LieBiaoJieKou.ILieBiao lieBiao;

    public LieBiaoPresenter(LieBiaoJieKou.ILieBiao lieBiao) {
        this.lieBiao = lieBiao;
        lieBiaoModel = new LieBiaoModel();
    }
    public void getProductDetail(final Context context, String pscid, String page, String sort){
        HashMap<String, String> params = new HashMap<>();
        params.put("pscid",pscid);
        params.put("page",page);
        params.put("sort",sort);
        params.put("source","android");
        lieBiaoModel.getInfoDetails(context,params, new OnNetListener<LieBiaoBean>() {
            @Override
            public void onSuccess(LieBiaoBean lieBiaoBean) {
                if (lieBiao != null) {
                    lieBiao.showList(lieBiaoBean.getData());
                }
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(context, "对于请求失败这事,就不劳揭穿了!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SelectGoods(final Context context, String keywords, String sort, String page) {
        lieBiaoModel.getOrders(context, keywords, sort, page, new OnNetListener<LieBiaoBean>() {
            @Override
            public void onSuccess(LieBiaoBean lieBiaoBean) {
                if (lieBiao != null) {
                    lieBiao.showSelectList(lieBiaoBean.getData());
                }
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(context, "对于请求失败这事,就不劳揭穿了!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 销毁
     */
    public void Dettach() {
        lieBiao = null;
    }
}
