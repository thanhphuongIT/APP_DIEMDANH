package com.spkt.app_student_attendance.presenter;

import android.content.Context;
import android.os.Looper;
import android.app.Activity ;
import android.content.Intent;

import com.spkt.app_student_attendance.StudentActivity;
import com.spkt.app_student_attendance.model.IUser;
import com.spkt.app_student_attendance.model.UserModel;
import com.spkt.app_student_attendance.view.ILoginView;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPresenter extends AppCompatActivity implements ILoginPresenter {
    ILoginView iLoginView;
    IUser userlogin;
    Handler handler;
//    private static final int REQUEST_CODE = 0x9345;
//    final Intent intent = new Intent(this, UserModel.class);
    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        handler = new Handler(Looper.getMainLooper());
    }
    private void initUser(String usern, String pass, ILoginView loginView){
        userlogin = new UserModel(usern,pass, loginView);
    }
    @Override
    public void doLogin(String user, String password, ILoginView loginView) {
        initUser(user,password, loginView);
        userlogin.checkUserValidity(loginView);
    }
}
