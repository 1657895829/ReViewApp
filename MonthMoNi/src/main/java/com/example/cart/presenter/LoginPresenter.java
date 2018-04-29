package com.example.cart.presenter;

import com.example.cart.bean.LoginBean;
import com.example.cart.model.LoginModel;
import com.example.cart.view.LoginViewCallBack;

/**
 * 登录presenter 层
 */
public class LoginPresenter {
    LoginModel loginModel;
    LoginViewCallBack callBack;
    public LoginPresenter(LoginViewCallBack callBack) {
        this.callBack = callBack;
        loginModel = new LoginModel();
    }

    //登陆的方法
    public void login(String mobile,String password){
        loginModel.login(mobile, password, new LoginViewCallBack() {
            @Override
            public void success(LoginBean loginBean) {
                callBack.success(loginBean);
            }

            @Override
            public void failure() {
                callBack.failure();
            }
        });
    }

    //销毁view层
    public void detach(){
        this.callBack = null;
    }
}
