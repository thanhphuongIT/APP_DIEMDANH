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

import com.spkt.app_student_attendance.presenter.LoginPresenter;
import com.spkt.app_student_attendance.view.ILoginView;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_attendence_student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_student);
        mapping();

        btn_attendence_student.setOnClickListener(this);
    }
    public void mapping() {
        btn_attendence_student = (Button) findViewById(R.id.btn_attendence_student);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_attendence_student:
                startActivity(new Intent(StudentActivity.this, AttendenceStudent.class));
                break;
        }
    }
}
