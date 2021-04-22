package com.spkt.app_student_attendance.presenter;

import android.os.Looper;

import com.spkt.app_student_attendance.model.IUser;
import com.spkt.app_student_attendance.model.UserModel;
import com.spkt.app_student_attendance.view.ILoginView;
import android.os.Handler;

public class LoginPresenter implements ILoginPresenter {
    ILoginView iLoginView;
    IUser userlogin;
    Handler handler;
    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }
    private void initUser(){
        userlogin = new UserModel("",""); // Chưa xử lý dữ liệu đổ vào
    }
    @Override
    public void doLogin(String user, String password) {
        Boolean isLoginSuccess = true;
        final Boolean code = userlogin.checkUserValidity(user,password);
        if (code!= false) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.onLoginResult(result, code);
            }
        }, 5000);
    }
}
