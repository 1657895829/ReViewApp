package example.com.sousuodome.IView;

/**
 * Created by lenovo on 2018/4/28.
 */

public interface OnNetListener<T> {
    //成功回调
    public void onSuccess(T t);
    //失败回调
    public void onFailure(Exception e);
}
