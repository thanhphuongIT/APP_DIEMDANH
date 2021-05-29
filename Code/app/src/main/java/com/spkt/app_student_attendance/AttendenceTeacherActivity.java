package com.spkt.app_student_attendance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.spkt.app_student_attendance.model.ClassModel;
import com.spkt.app_student_attendance.presenter.AttendenceTeacherPresenter;
import com.spkt.app_student_attendance.presenter.IAttendenceTeacherPresenter;
import com.spkt.app_student_attendance.view.IAttendenceTeacherView;

import java.util.ArrayList;

public class AttendenceTeacherActivity extends AppCompatActivity implements IAttendenceTeacherView {
    private EditText editText_search;
    private ListView listView_class;
    IAttendenceTeacherPresenter attendenceTeacherPresenter = new AttendenceTeacherPresenter(this);
    ArrayList<ClassModel> listClass;
    ClassListAdapterActivity classListAdapter;
    private String teacher_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_classlist_attendance_teacher);
        mapping();
        LoadClassForTeacher();

        listView_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassModel class_model = (ClassModel) classListAdapter.getItem(position);
                //Do in Click Class
                Intent intent = new Intent(view.getContext() , RenderQRActivity.class);
                intent.putExtra("ID_CLASS", class_model.getClass_id());  // Truyền ID_Class
                startActivity(intent);
            }
        });
    }

    public void LoadClassForTeacher() {
        Intent intent = getIntent();
        teacher_id = intent.getStringExtra("ID_TEACHER");
        attendenceTeacherPresenter.doLoadListClass(teacher_id, this);
    }

    public void mapping() {
        editText_search = (EditText) findViewById(R.id.edt_search_class);
        listView_class = (ListView) findViewById(R.id.list_class);
    }

    @Override
    public void onListClassResult(ArrayList<ClassModel> List_Class) {
        classListAdapter = new ClassListAdapterActivity(List_Class);
        listView_class.setAdapter(classListAdapter);
    }
}
