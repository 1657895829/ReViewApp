package example.com.sousuodome.utils;

/**
 * Created by lenovo on 2018/4/28.
 */

public class Api {
    public static boolean isOnline = false;
    public static final String DEV = "https://www.zhaoapi.cn";
    public static final String WROK = "";
    public static final String HOST = isOnline ? WROK : DEV;
    public static final String PRODUCT_CATAGORY_LIST = HOST + "/product/getProducts";
    public static final String LOGIN = HOST + "/user/login";//登陆
    public static final String REGISTER = HOST + "/user/reg";//注册
    public static final String CLASS = HOST + "/product/getCatagory";//分类
    public static final String PRODUCT_CATAGORY = HOST + "/product/getProductCatagory";//商品子分类接口
    public static final String ZHUYEURL = HOST+"/ad/getAd";
    public static final String ZHUYEMIDDLEVIEW = HOST+"/product/getCatagory";
    public static final String ADD_CARD = HOST + "/product/addCart";
    public static final String PRODUCT_DETAIL = HOST + "/product/getProductDetail?pid=%s&source=android";
    public static final String SELECT_CARD = HOST + "/product/getCarts";
    public static final String DEL_CARD = HOST + "/product/deleteCart";
    //创建订单接口
    public static final String CREATEORDER = HOST + "/product/createOrder?uid=%s&price=%s";
    //  修改订单状态
    public static final String UPDATEORDER = HOST+"/product/updateOrder?uid=%s";
    // 获取订单列表接口
    public static final String  GETORDERS = HOST+"/product/getOrders";
    //查询商品
    public static final String  SEARCHPRODUCTS = HOST+"/product/searchProducts";
}
