package com.spkt.app_student_attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_attendence_student, btn_information_student,btn_present_student;
    private  String id_student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_student);
        mapping();
        Intent intent_teacher = getIntent();
        id_student = intent_teacher.getStringExtra("ID_STUDENT");

        Intent intent_student = getIntent();
        id_student = intent_student.getStringExtra("ID_STUDENT");

        btn_attendence_student.setOnClickListener(this);
        btn_information_student.setOnClickListener(this);
        btn_present_student.setOnClickListener(this);
    }
    public void mapping() {
        btn_attendence_student = (Button) findViewById(R.id.btn_attendence_student);
        btn_information_student = (Button) findViewById(R.id.btn_information_student);
        btn_present_student = (Button) findViewById(R.id.btn_present_student);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_attendence_student:
                Intent studentattendance = new Intent(this , AttendenceStudentActivity.class);
                studentattendance.putExtra("ID_STUDENT", id_student);  // Truyền ID_TEACHER
                startActivity(studentattendance);
                break;
            case R.id.btn_information_student:
                Intent student = new Intent(this , ProfileStudentActivity.class);
                student.putExtra("ID_STUDENT", id_student);  // Truyền ID_STUDENT
                startActivity(student);
                break;
            case R.id.btn_present_student:
                Intent studentpresent = new Intent(this , PresentStudentActivity.class);
                studentpresent.putExtra("ID_STUDENT", id_student);  // Truyền ID_TEACHER
                startActivity(studentpresent);
                break;
        }
    }
}
