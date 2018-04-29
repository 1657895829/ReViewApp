package com.example.cart.retrofit;

import com.example.cart.bean.AddCartBean;
import com.example.cart.bean.DeleteBean;
import com.example.cart.bean.SearchBean;
import com.example.cart.bean.SelectCartBean;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 网络接口数据的请求类
 */
public interface GetDataInterface {
    //搜索 笔记本 手机的接口,集合传参
    //https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
    @GET("/product/searchProducts")
    Call<SearchBean> getEdit(@QueryMap Map<String, String> map);

     /*
     * 加入购物车
     *  https://www.zhaoapi.cn/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
     *  uid": 1650
     *  "token": "2FC3EF31EA25696D2715A971ADE38DE1"
     */
    @GET("product/addCart")
    Call<AddCartBean> addCart(@QueryMap Map<String, String> map);

    //https://www.zhaoapi.cn/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
    //uid": 1650,
    // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
    @GET("product/getCarts")
    Call<SelectCartBean> selectCart(@QueryMap Map<String, String> map);

    //删除
    //https://www.zhaoapi.cn/product/deleteCart?uid=1650&pid=58
    @GET("/product/deleteCart")
    Call<DeleteBean> deleteCart(@QueryMap Map<String, String> map);

}
