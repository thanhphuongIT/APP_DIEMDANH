package com.spkt.app_student_attendance;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
    private CheckBox cb_remeberme;
    private ImageView ic_eye;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("DataLogin", MODE_PRIVATE);
        mapping();

        edt_user.setText(sharedPreferences.getString("email",""));
        edt_password.setText(sharedPreferences.getString("password",""));
        cb_remeberme.setChecked(sharedPreferences.getBoolean("checked",false));

        //Init
        loginPresenter = new LoginPresenter(this);
        //Set Listener

        btn_login.setOnClickListener(this);
        btn_text_forgot.setOnClickListener(this);
        ic_eye.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                //btn_login.setEnabled(false);
                loginPresenter.doLogin(edt_user.getText().toString().trim(), edt_password.getText().toString().trim(),this);
                break;
            case R.id.btn_text_forgot:
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                break;
            case R.id.ic_eye:
               if(ic_eye.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.eye).getConstantState()))
               {
                   ic_eye.setImageResource(R.drawable.eye_closed);
                   edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
               }
               else
               {
                   ic_eye.setImageResource(R.drawable.eye);
                   edt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
               }
        }
    }
    @Override
    public void onLoginResult(String role,String id_teacher, String id_student) {
        switch (role) {
            case "1":
                handlePreferences();
                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                finish();
                break;
            case "2":
                handlePreferences();
                Intent teacher = new Intent(this , TeacherActivity.class);
                teacher.putExtra("ID_TEACHER", id_teacher);  // Truyền ID_TEACHER
                startActivity(teacher);
                finish();
                break;
            case "3":
                handlePreferences();
                Intent student = new Intent(this , StudentActivity.class);
                student.putExtra("ID_STUDENT", id_student);  // Truyền ID_STUDENT
                startActivity(student);
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void mapping() {
        edt_user = (EditText) findViewById(R.id.edt_oldpassword);
        edt_password = (EditText) findViewById((R.id.edt_password));
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_text_forgot = (TextView) findViewById(R.id.btn_text_forgot);
        cb_remeberme = (CheckBox) findViewById(R.id.cb_remeberme);
        ic_eye = (ImageView) findViewById(R.id.ic_eye);
    }

    protected void handlePreferences() {
        if(cb_remeberme.isChecked()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", edt_user.getText().toString());
            editor.putString("password",edt_password.getText().toString());
            editor.putBoolean("checked",true);
            editor.apply();
        }
        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("email");
            editor.remove("password");
            editor.remove("checked");
            editor.apply();
        }
    }


}
