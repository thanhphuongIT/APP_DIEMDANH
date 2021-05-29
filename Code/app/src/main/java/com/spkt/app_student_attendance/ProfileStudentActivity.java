package com.spkt.app_student_attendance;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.spkt.app_student_attendance.presenter.ProfileStudentPresenter;
import com.spkt.app_student_attendance.presenter.IProfileStudentPresenter;
import com.spkt.app_student_attendance.view.ILoginView;
import com.spkt.app_student_attendance.view.IProfileStudentView;

public class ProfileStudentActivity extends AppCompatActivity implements IProfileStudentView, View.OnClickListener {
    private EditText edt_fullname_student, edt_email_student, edt_phone_student, edt_class_student, edt_school_student, edt_birthday_student, edt_ID_student;
    private CheckBox cb_male_student, cb_female_student;
    private ImageView img_avatar_student, img_btn_home, img_btn_back;
    private Button btn_update_student, btn_changepass_student, btn_choose_image;
    private IProfileStudentPresenter profileStudentPresenter = new ProfileStudentPresenter(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile_student);
        mapping();

        //Init
        profileStudentPresenter.getIDStudent("110",this);


        //Set Listener
        img_btn_home.setOnClickListener(this);
        img_btn_back.setOnClickListener(this);

        btn_update_student.setOnClickListener(this);
        btn_changepass_student.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_home:
            case R.id.img_btn_back:
                startActivity(new Intent(ProfileStudentActivity.this, StudentActivity.class));
                finish();
                break;
            case R.id.btn_update_student:
                enable();
                btn_changepass_student.setText("Save");
                btn_choose_image.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_changepass_student:
                if (btn_changepass_student.getText().toString().equals("Save"))
                {
                    updateInforStudent();
                    startActivity(new Intent(ProfileStudentActivity.this, ProfileStudentActivity.class));
                    finish();
                    break;
                }
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void mapping() {
        edt_fullname_student = (EditText) findViewById(R.id.edt_fullname_student);
        edt_email_student = (EditText) findViewById(R.id.edt_email_student);
        edt_phone_student = (EditText) findViewById(R.id.edt_phone_student);
        edt_class_student = (EditText) findViewById(R.id.edt_class_student);
        edt_school_student = (EditText) findViewById(R.id.edt_school_student);
        edt_birthday_student = (EditText) findViewById(R.id.edt_birthday_student);
        edt_ID_student = (EditText) findViewById(R.id.edt_ID_student);
        btn_update_student = (Button) findViewById(R.id.btn_update_student);
        img_btn_home = (ImageView) findViewById(R.id.img_btn_home);
        img_btn_back = (ImageView) findViewById(R.id.img_btn_back);
        img_avatar_student = (ImageView) findViewById(R.id.img_avatar_student);
        btn_update_student = (Button) findViewById(R.id.btn_update_student);
        btn_changepass_student = (Button) findViewById(R.id.btn_changepass_student);
        btn_choose_image = (Button) findViewById(R.id.btn_choose_image);

        cb_male_student = (CheckBox) findViewById(R.id.cb_male_student);
        cb_female_student = (CheckBox) findViewById(R.id.cb_female_student);
    }


    @Override
    public void showInforStudent(String student_id, String student_name,String student_birth,String student_gender,String student_mail,String student_phone) {
        edt_school_student.setText("ĐH SPKT TPHCM");
        edt_class_student.setText("18110CL1B");
        edt_ID_student.setText(student_id);
        edt_email_student.setText(student_mail);
        edt_phone_student.setText(student_phone);
        edt_birthday_student.setText(student_id);
        edt_fullname_student.setText(student_name);
        edt_birthday_student.setText(student_birth);
        if (student_gender.equals("nam"))
        {
            cb_male_student.setChecked(true);
        }
        else {
            cb_female_student.setChecked(true);
        }
        disable();
        btn_choose_image.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateSuccessfully(int result) {
        if (result == 1)
        {
            Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText( this, "Update Fail", Toast.LENGTH_SHORT).show();
        }
    }

    private void disable()
    {
        edt_school_student.setFocusable(false);
        edt_class_student.setFocusable(false);
        edt_ID_student.setFocusable(false);
        edt_email_student.setFocusable(false);
        edt_phone_student.setFocusable(false);
        edt_birthday_student.setFocusable(false);
        edt_fullname_student.setFocusable(false);
        edt_birthday_student.setFocusable(false);
        cb_male_student.setEnabled(false);
        cb_female_student.setEnabled(false);
    }
    private void enable()
    {
        edt_email_student.setFocusableInTouchMode(true);
        edt_phone_student.setFocusableInTouchMode(true);
        edt_birthday_student.setFocusableInTouchMode(true);
        edt_fullname_student.setFocusableInTouchMode(true);
        edt_birthday_student.setFocusableInTouchMode(true);
        cb_male_student.setEnabled(true);
        cb_female_student.setEnabled(true);
    }
    public void updateInforStudent()
    {
        String student_id = edt_ID_student.getText().toString().trim();
        String student_phone = edt_phone_student.getText().toString().trim();
        String student_birth = edt_birthday_student.getText().toString().trim();
        String student_name = edt_fullname_student.getText().toString().trim();
        String student_mail = edt_email_student.getText().toString().trim();
        String student_gender;
        if (cb_male_student.isChecked())
        {
            student_gender = "nam";
        }
        else {
            student_gender = "nu";
        }
        profileStudentPresenter.checkUpdate(student_id,  student_name, student_birth, student_gender, student_mail, student_phone,this);
    }
}
