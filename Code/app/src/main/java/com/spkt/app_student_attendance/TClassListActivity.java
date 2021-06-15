package com.spkt.app_student_attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.spkt.app_student_attendance.adapter.ClassListAdapterForTeacherActivity;

import com.spkt.app_student_attendance.model.ClassModel;
import com.spkt.app_student_attendance.model.IPConfigModel;

import com.spkt.app_student_attendance.presenter.ITClassListPresenter;
import com.spkt.app_student_attendance.presenter.TClassListPresenter;

import com.spkt.app_student_attendance.view.ITClassListView;

import java.util.ArrayList;

public class TClassListActivity extends AppCompatActivity implements ITClassListView,View.OnClickListener {
    private IPConfigModel ipConfigModel = new IPConfigModel();
    private EditText editText;
    private ImageView img_btn_back;
    private String id_student;
    //MVP
    private ListView list;
    private String teacher_id;
    private ITClassListPresenter TClassListPresenter = new TClassListPresenter(this);
    ArrayList<ClassModel> List_Class;
    ClassListAdapterForTeacherActivity classListAdapterForTeacherActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classlist_teacher);
        AnhXa();
        LoadClassForStudent();

        //bắt sự kiện click
        img_btn_back.setOnClickListener(this);
        // Funcion on Click list view
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassModel class_model = (ClassModel) classListAdapterForTeacherActivity.getItem(position);
                //Do in Click Class
                Intent intent = new Intent(view.getContext() , TclassListDetailActivity.class);
                intent.putExtra("ID_CLASS", class_model.getClass_id());
                intent.putExtra("CLASS_NAME", class_model.getClass_name());// Truyền ID_Class
                intent.putExtra("ID_TEACHER",teacher_id);
                startActivity(intent);
            }
        });

    }
    public void LoadClassForStudent() {
        Intent intent = getIntent();
        teacher_id =intent.getStringExtra("ID_TEACHER");
        TClassListPresenter.doLoadListTeacher(teacher_id, this);
    }

    public void AnhXa() {
        list = findViewById(R.id.recyclerView3);
        img_btn_back = (ImageView) findViewById(R.id.img_btn_back_teacher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_teacher:
                Intent intent = new Intent(this, TeacherActivity.class);
                intent.putExtra("ID_TEACHER", teacher_id);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onListClassTeacherResult(ArrayList<ClassModel> List_Class) {
        classListAdapterForTeacherActivity = new ClassListAdapterForTeacherActivity(List_Class);
        list.setAdapter(classListAdapterForTeacherActivity);
    }
}
