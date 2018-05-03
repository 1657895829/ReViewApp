package app.example.com.myapplication.model;

import java.util.HashMap;
import java.util.Map;
import app.example.com.myapplication.bean.VideoBean;
import app.example.com.myapplication.util.APIFactory;
import app.example.com.myapplication.util.AbstractObserver;
import app.example.com.myapplication.view.VideoView;

/**
 * 影片数据model层
 */

public class VideoModel {
    //获取影片数据
    public void success(final VideoView videoView , String id){
        Map<String,String> map = new HashMap<>();
        map.put("mediaId",id);
        APIFactory.getInstance().get("front/videoDetailApi/videoDetail.do", map, new AbstractObserver<VideoBean>() {
            @Override
            public void onSuccess(VideoBean videoBean) {
                videoView.onSuccess(videoBean);
            }

            @Override
            public void onFailure(int code) {
                videoView.onFailure(code);
            }
        });
    }
}
