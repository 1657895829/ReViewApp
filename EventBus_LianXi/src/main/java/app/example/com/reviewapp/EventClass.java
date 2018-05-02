package app.example.com.reviewapp;

/**
 *  新建一个类 EventClass
 *  这个类很简单，构造时传进去一个字符串，然后可以通过getMsg()获取出来。
 */
public class EventClass {
    private  String mMsg;

    public  EventClass(String msg) {
        //声明属性
        mMsg = msg;
    }

    public String getMsg() {
        return mMsg;
    }
}
