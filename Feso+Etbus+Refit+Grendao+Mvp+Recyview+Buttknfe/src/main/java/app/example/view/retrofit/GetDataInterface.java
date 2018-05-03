package app.example.view.retrofit;

import app.example.view.bean.NewsBean;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 创建网络请求接口实例:http://v.juhe.cn/toutiao/index?type=toutiao&key=c4479ad58f41e7f78a8fa073d0b1f1b5
 *
 * 分 2 种 请求方式：@GET @POST
 *
 * @Path： 所有在网址中的参数（URL的问号前面）
 * @Query： URL问号后面的参数
 * @QueryMap： 相当于多个@Query
 * @Field： 用于POST请求，提交单个数据
 * @Body： 相当于多个@Field，以对象的形式提交
 * @FormUrlEncoded: 用于修饰Field注解和FieldMap注解;
 *                  使用该注解,表示请求正文将使用表单网址编码。
 *                  字段应该声明为参数，并用@Field注释或FieldMap注释。
 *                  使用FormUrlEncoded注解的请求将具”application / x-www-form-urlencoded” MIME类型。
 *                  字段名称和值将先进行UTF-8进行编码,再根据RFC-3986进行URI编码.
 */
public interface GetDataInterface {
    /**
     * get请求
     * @param key
     * @return
     */
    @GET("/toutiao/index")
    Call<NewsBean> get(@Query("key") String key);

    /**
     * post请求
     * @param key
     * @return
     */
    @FormUrlEncoded
    @POST("/toutiao/index")
    Call<NewsBean> post(@Field("key") String key);
}
