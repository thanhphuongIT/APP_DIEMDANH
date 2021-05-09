package com.spkt.app_student_attendance;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.spkt.app_student_attendance.presenter.ILoginPresenter;
import com.spkt.app_student_attendance.presenter.LoginPresenter;
import com.spkt.app_student_attendance.view.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {
    private EditText edt_user,edt_password;
    private Button btn_login;
    private TextView btn_text_forgot;
    private ILoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mapping();

        //Init
        loginPresenter = new LoginPresenter(this);
        //Set Listener
        btn_login.setOnClickListener(this);
        btn_text_forgot.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                //btn_login.setEnabled(false);
                loginPresenter.doLogin(edt_user.getText().toString().trim(), edt_password.getText().toString().trim(),this);
                break;
            case R.id.btn_text_forgot:
                startActivity(new Intent(LoginActivity.this, Forgotpassword.class));
                break;
        }
    }
    @Override
    public void onLoginResult(String role) {
        switch (role) {
            case "1":
                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                finish();
                break;
            case "2":
                startActivity(new Intent(LoginActivity.this, TeacherActivity.class));
                finish();
                break;
            case "3":
                startActivity(new Intent(LoginActivity.this, StudentActivity.class));
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void mapping() {
        edt_user = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById((R.id.edt_password));
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_text_forgot = (TextView) findViewById(R.id.btn_text_forgot);
    }


}
