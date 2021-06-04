package com.spkt.app_student_attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_attendence_teacher;
    private String id_teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.acctivity_main_teacher);
        mapping();
        Intent intent_teacher = getIntent();
        id_teacher = intent_teacher.getStringExtra("ID_TEACHER");
        btn_attendence_teacher.setOnClickListener(this);
    }
    public void mapping() {
        btn_attendence_teacher = (Button) findViewById(R.id.btn_attendence_teacher);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_attendence_teacher:
                Intent teacher = new Intent(this , AttendenceTeacherActivity.class);
                teacher.putExtra("ID_TEACHER", id_teacher);  // Truyền ID_TEACHER
                startActivity(teacher);
                break;
        }
    }
}
