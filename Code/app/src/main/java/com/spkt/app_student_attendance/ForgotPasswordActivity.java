package com.spkt.app_student_attendance;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_forgot;
    private  TextView text_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);
        mapping();
        btn_forgot.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void mapping() {
        btn_forgot = (Button) findViewById(R.id.btn_forgot);
        text_phone = (TextView) findViewById(R.id.text_phone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_forgot:
                String number = text_phone.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + number));
                startActivity(callIntent);
                break;
        }
    }
}
