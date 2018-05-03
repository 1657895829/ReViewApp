package app.example.com.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.hjm.bottomtabbar.BottomTabBar;

import app.example.com.myapplication.fragment.FindFragment_03;
import app.example.com.myapplication.fragment.JingXuanFragment_01;
import app.example.com.myapplication.fragment.MineFragment_04;
import app.example.com.myapplication.fragment.ZhuanTiFragment_02;
import butterknife.BindView;
import butterknife.ButterKnife;

//
//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                           O\  =  /O
//                        ____/`---'\____
//                      .'  \\|     |//  `.
//                     /  \\|||  :  |||//  \
//                    /  _||||| -:- |||||-  \
//                    |   | \\\  -  /// |   |
//                    | \_|  ''\---/''  |   |
//                    \  .-\__  `-`  ___/-. /
//                  ___`. .'  /--.--\  `. . __
//               ."" '<  `.___\_<|>_/___.'  >'"".
//              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//              \  \ `-.   \_ __\ /__ _/   .-` /  /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//        佛祖保佑    永无BUG   永不修改    事业感情两辉煌

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_tabBar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //初始化Fragment
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50, 50)      //图片大小
                .setFontSize(12)                       //字体大小
                .setTabPadding(4, 6, 10)//选项卡的间距
                .setChangeColor(Color.RED, Color.BLACK)     //选项卡的选择颜色
                .addTabItem("精选", R.drawable.found, JingXuanFragment_01.class)
                .addTabItem("专题", R.drawable.special, ZhuanTiFragment_02.class)
                .addTabItem("发现", R.drawable.fancy, FindFragment_03.class)
                .addTabItem("我的", R.drawable.my, MineFragment_04.class)
                .isShowDivider(true)    //是否包含分割线
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        Log.i("TGA", "位置：" + position + "      选项卡：" + name);
                    }
                });
    }

}
