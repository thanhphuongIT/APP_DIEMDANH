package com.spkt.app_student_attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.spkt.app_student_attendance.adapter.ClassAdapterForStudentActivity;
import com.spkt.app_student_attendance.adapter.ClassListAdapterForTeacherActivity;
import com.spkt.app_student_attendance.adapter.TimePresentAdapterForStudentActivity;
import com.spkt.app_student_attendance.model.ClassModel;
import com.spkt.app_student_attendance.model.PresentStudentModel;
import com.spkt.app_student_attendance.presenter.IPresentStudentPresenter;
import com.spkt.app_student_attendance.presenter.PresentStudentPresenter;
import com.spkt.app_student_attendance.view.IPresentStudentView;
import com.spkt.app_student_attendance.view.IPresentTimeStudentView;

import java.util.ArrayList;

public class PresentTimeStudentActivity extends AppCompatActivity implements IPresentTimeStudentView {
    private EditText txt_search_time_student;
    private ListView list_time_for_student;
    private IPresentStudentPresenter PresentStudentPresenter = new PresentStudentPresenter(this);
    private String Id_class_present;
    private String Id_student_present;

    ArrayList<ClassModel> listClass;
    TimePresentAdapterForStudentActivity timePresentAdapterForStuden;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_present_student_list_time);
        LoadTimeForStudent();
        mapping();

    }

    public void LoadTimeForStudent() {
        Intent intent = getIntent();
        Id_class_present = intent.getStringExtra("ID_CLASS");
        Id_student_present = intent.getStringExtra("ID_STUDENT");
        PresentStudentPresenter.doLoadtimepresent(Id_class_present, Id_student_present, this);
    }

    public void mapping() {
        txt_search_time_student = (EditText) findViewById(R.id.txt_search_class_student);
        list_time_for_student = (ListView) findViewById(R.id.list_present_time_for_student);
    }

    @Override
    public void onLisTimeStudentResult(ArrayList<PresentStudentModel> List_time) {
        timePresentAdapterForStuden = new TimePresentAdapterForStudentActivity(List_time);
        list_time_for_student.setAdapter(timePresentAdapterForStuden);
    }
}
