package com.example.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cart.activity.HomeActivity;
import com.example.cart.activity.RegistActivity;
import com.example.cart.bean.LoginBean;
import com.example.cart.bean.RegistBean;
import com.example.cart.presenter.LoginPresenter;
import com.example.cart.view.LoginViewCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//用户登录页面
public class MainActivity extends AppCompatActivity implements LoginViewCallBack {

    @BindView(R.id.login_mobile)
    EditText loginMobile;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_forget)
    TextView loginForget;
    @BindView(R.id.login_regist)
    TextView loginRegist;
    @BindView(R.id.login_btn)
    Button loginBtn;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //注册EventBus
        EventBus.getDefault().register(this);

        //调用p层
        presenter = new LoginPresenter(this);
    }

    @OnClick({R.id.login_mobile, R.id.login_password, R.id.login_forget, R.id.login_regist, R.id.login_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

            //忘记密码
            case R.id.login_forget:
                break;

            //新用户注册
            case R.id.login_regist:
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
                break;

            //登录按钮
            case R.id.login_btn:
                if (!TextUtils.isEmpty(loginMobile.getText().toString()) && !TextUtils.isEmpty(loginPassword.getText().toString())){
                    if (isMobile(loginMobile.getText().toString())){
                        Toast.makeText(MainActivity.this, "手机号合法！", Toast.LENGTH_SHORT).show();
                        if (loginPassword.getText().toString().length() != 6 ){
                            Toast.makeText(MainActivity.this, "密码长度必须是6位！", Toast.LENGTH_SHORT).show();
                        }else {
                            presenter.login(loginMobile.getText().toString(), loginPassword.getText().toString());
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "手机号不合法！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 验证手机号码格式
     */
    private static boolean  isMobile(String number) {
            /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        //表示手机号一共11位，"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String num = "[1][358]\\d{9}";
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    //接收注册的信息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void msg(RegistBean registBean){

    }

    @Override
    public void success(LoginBean loginBean) {
        if (loginBean != null){
            String msg = loginBean.getMsg();
            Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (msg.equals("登录成功")){
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void failure() {
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
    }

    //重写onDestroy方法，避免内存泄露,销毁EventBus
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();

        EventBus.getDefault().unregister(this);
    }
}
