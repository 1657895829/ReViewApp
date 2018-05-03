package app.example.com.myapplication.view;

import app.example.com.myapplication.bean.VideoBean;

/**
 * 影片评论数据接口
 */

public interface VideoView {
    public void onSuccess(VideoBean videoBean);
    public void onFailure(int code);
}
