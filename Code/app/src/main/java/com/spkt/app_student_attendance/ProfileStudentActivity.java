package com.spkt.app_student_attendance;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.database.Cursor;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.spkt.app_student_attendance.Retrofit.APIUtils;
import com.spkt.app_student_attendance.Retrofit.DataClient;
import com.spkt.app_student_attendance.presenter.ProfileStudentPresenter;
import com.spkt.app_student_attendance.presenter.IProfileStudentPresenter;
import com.spkt.app_student_attendance.view.IProfileStudentView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileStudentActivity extends AppCompatActivity implements IProfileStudentView, View.OnClickListener {
    private EditText edt_fullname_student, edt_email_student, edt_phone_student, edt_class_student, edt_school_student, edt_birthday_student, edt_ID_student;
    private RadioButton rb_male_student, rb_female_student;
    private ImageView img_btn_home, img_btn_back;
    private CircleImageView img_avatar_student;
    int SELECT_PICTURE = 200;
    private static int RESULT_LOAD_IMAGE = 1;
    private Button btn_update_student, btn_changepass_student, btn_choose_image;
    private String id_student;
    String realpath = "";
    private IProfileStudentPresenter profileStudentPresenter = new ProfileStudentPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile_student);
        mapping();

        Intent intent_student = getIntent();
        id_student = intent_student.getStringExtra("ID_STUDENT");
        //InitSTUDENT
        profileStudentPresenter.getIDStudent(id_student, this);

        //Set Listener
        img_btn_home.setOnClickListener(this);
        img_btn_back.setOnClickListener(this);

        btn_update_student.setOnClickListener(this);
        btn_changepass_student.setOnClickListener(this);

        btn_choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_PICTURE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data !=null)
        {
            Uri uri = data.getData();
            realpath = getRealPathFromURI(uri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_avatar_student.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_home:
            case R.id.img_btn_back:
                startActivity(new Intent(ProfileStudentActivity.this, StudentActivity.class));
                finish();
                break;
            case R.id.btn_changepass:
                enable();
                btn_changepass_student.setText("Save");
                btn_choose_image.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_changepass_student:
                if (btn_changepass_student.getText().toString().equals("Save")) {
                    uploadimgaetoserver();
                    updateInforStudent();
                    startActivity(new Intent(ProfileStudentActivity.this, ProfileStudentActivity.class));
                    finish();
                    break;
                } else {
                    Intent student = new Intent(this , ChangePasswordStudentActivity.class);
                    student.putExtra("ID_STUDENT", id_student);  // Truyền ID_STUDENT
                    startActivity(student);
                }
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
        btn_update_student = (Button) findViewById(R.id.btn_changepass);
        img_btn_home = (ImageView) findViewById(R.id.img_btn_home);
        img_btn_back = (ImageView) findViewById(R.id.img_btn_back);
        img_avatar_student = (CircleImageView) findViewById(R.id.img_avatar_student);
        btn_update_student = (Button) findViewById(R.id.btn_changepass);
        btn_changepass_student = (Button) findViewById(R.id.btn_changepass_student);
        btn_choose_image = (Button) findViewById(R.id.btn_choose_image);

        rb_male_student = (RadioButton) findViewById(R.id.rb_male_student);
        rb_female_student = (RadioButton) findViewById(R.id.rb_female_student);
    }


    @Override
    public void showInforStudent(String student_id, String student_name, String student_birth, String student_gender, String student_mail, String student_phone) {
        edt_school_student.setText("ĐH SPKT TPHCM");
        edt_class_student.setText("18110CL1B");
        edt_ID_student.setText(student_id);
        edt_email_student.setText(student_mail);
        edt_phone_student.setText(student_phone);
        edt_birthday_student.setText(student_id);
        edt_fullname_student.setText(student_name);
        edt_birthday_student.setText(student_birth);
        if (student_gender.equals("nam")) {
            rb_male_student.setChecked(true);
        } else {
            rb_female_student.setChecked(true);
        }
        disable();
        btn_choose_image.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateSuccessfully(int result) {
        if (result == 1) {
            Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Update Fail", Toast.LENGTH_SHORT).show();
        }
    }

    private void disable() {
        edt_school_student.setFocusable(false);
        edt_class_student.setFocusable(false);
        edt_ID_student.setFocusable(false);
        edt_email_student.setFocusable(false);
        edt_phone_student.setFocusable(false);
        edt_birthday_student.setFocusable(false);
        edt_fullname_student.setFocusable(false);
        edt_birthday_student.setFocusable(false);
        rb_male_student.setEnabled(false);
        rb_female_student.setEnabled(false);
    }

    private void enable() {
        edt_email_student.setFocusableInTouchMode(true);
        edt_phone_student.setFocusableInTouchMode(true);
        edt_birthday_student.setFocusableInTouchMode(true);
        edt_fullname_student.setFocusableInTouchMode(true);
        edt_birthday_student.setFocusableInTouchMode(true);
        rb_male_student.setEnabled(true);
        rb_female_student.setEnabled(true);
    }

    public void updateInforStudent() {
        String student_id = edt_ID_student.getText().toString().trim();
        String student_phone = edt_phone_student.getText().toString().trim();
        String student_birth = edt_birthday_student.getText().toString().trim();
        String student_name = edt_fullname_student.getText().toString().trim();
        String student_mail = edt_email_student.getText().toString().trim();
        String student_gender;
        if (rb_male_student.isChecked()) {
            student_gender = "nam";
        } else {
            student_gender = "nu";
        }
        profileStudentPresenter.checkUpdate(student_id, student_name, student_birth, student_gender, student_mail, student_phone, this);
    }

    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = {MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    public void uploadimgaetoserver()
    {
        File file = new File(realpath);
        String file_path = file.getAbsolutePath();
        String[] mangtenfile = file_path.split("\\.");

        file_path = mangtenfile[0] + System.currentTimeMillis() + "."+mangtenfile[1];
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("upload_file",file_path,requestBody);

        DataClient dataClient = APIUtils.getData();
        retrofit2.Call<String> callback = dataClient.Uploadphoto(body);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response!=null)
                {
                    String message = response.body();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });

    }
}
