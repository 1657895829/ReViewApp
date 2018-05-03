package app.example.com.myapplication.presenter;

import app.example.com.myapplication.bean.VideoBean;
import app.example.com.myapplication.model.VideoModel;
import app.example.com.myapplication.view.VideoView;

/**
 * 影片数据p层
 */

public class VideoPresenter {
    private VideoView videoView;
    private VideoModel videoModel;

    public VideoPresenter(VideoView videoView) {
        this.videoView = videoView;
        videoModel = new VideoModel();
    }

    //获取影片数据
    public void success(String id){
        videoModel.success(new VideoView() {
            @Override
            public void onSuccess(VideoBean videoBean) {
                videoView.onSuccess(videoBean);
            }

            @Override
            public void onFailure(int code) {
                videoView.onFailure(code);
            }
        },id);
    }

    //关闭p层，避免内存泄露
    public void detach(){
        this.videoView = null;
    }
}
