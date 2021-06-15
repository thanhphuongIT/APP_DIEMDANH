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
import com.spkt.app_student_attendance.presenter.IProfileStudentPresenter;
import com.spkt.app_student_attendance.presenter.IProfileTeacherPresenter;
import com.spkt.app_student_attendance.presenter.ProfileTeacherPresenter;
import com.spkt.app_student_attendance.view.IProfileTeacherView;
import com.squareup.picasso.Picasso;

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

public class ProfileTeacherActivity extends AppCompatActivity implements IProfileTeacherView, View.OnClickListener {
    private EditText edt_fullname_teacher, edt_email_teacher, edt_phone_teacher, edt_major_teacher, edt_birthday_teacher, edt_ID_teacher;
    private RadioButton rb_male_teacher, rb_female_teacher;
    private ImageView img_btn_home, img_btn_back;
    private CircleImageView img_avatar_teacher;
    int SELECT_PICTURE = 200;
    private static int RESULT_LOAD_IMAGE = 1;
    private Button btn_update_teacher, btn_changepass_teacher, btn_choose_image;
    private String id_teacher;
    String realpath = "";
    private String hinhanhUrl;
    private IProfileTeacherPresenter profileTeacherPresenter = new ProfileTeacherPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile_teacher);
        mapping();

        Intent intent_teacher = getIntent();
        id_teacher = intent_teacher.getStringExtra("ID_TEACHER");
        //Initteacher
        profileTeacherPresenter.getIDTeacher(id_teacher, this);

        //Set Listener
        img_btn_home.setOnClickListener(this);
        img_btn_back.setOnClickListener(this);

        btn_update_teacher.setOnClickListener(this);
        btn_changepass_teacher.setOnClickListener(this);

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
                img_avatar_teacher.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_home_teacher:
            case R.id.img_btn_back_teacher:
                Intent teacher1 = new Intent(this , TeacherActivity.class);
                teacher1.putExtra("ID_TEACHER", id_teacher);  // Truyền ID_teacher
                startActivity(teacher1);
                break;
            case R.id.btn_update_teacher:
                enable();
                btn_changepass_teacher.setText("Save");
                btn_choose_image.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_changepass_teacher:
                if (btn_changepass_teacher.getText().toString().trim().equals("Save")) {
                    if(edt_ID_teacher.getText().toString().trim().equals("")
                            || edt_phone_teacher.getText().toString().trim().equals("")
                            || edt_birthday_teacher.getText().toString().trim().equals("")
                            || edt_fullname_teacher.getText().toString().trim().equals("")
                            || edt_email_teacher.getText().toString().trim().equals("")) {
                        Toast.makeText(this, "Please complete all information!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else {
                        uploadimgaetoserver();
                        Intent teacher = new Intent(this, ProfileTeacherActivity.class);
                        teacher.putExtra("ID_TEACHER", id_teacher);  // Truyền ID_teacher
                        startActivity(teacher);
                        break;
                    }
                } else {
                    Intent teacher01 = new Intent(this , ChangePasswordTeacherActivity.class);
                    teacher01.putExtra("ID_TEACHER", id_teacher);  // Truyền ID_teacher
                    startActivity(teacher01);
                    break;
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void mapping() {
        edt_fullname_teacher = (EditText) findViewById(R.id.edt_fullname_teacher);
        edt_email_teacher = (EditText) findViewById(R.id.edt_email_teacher);
        edt_phone_teacher = (EditText) findViewById(R.id.edt_phone_teacher);
        edt_major_teacher = (EditText) findViewById(R.id.edt_major_teacher);
        edt_birthday_teacher = (EditText) findViewById(R.id.edt_birthday_teacher);
        edt_ID_teacher = (EditText) findViewById(R.id.edt_ID_teacher);
        btn_update_teacher = (Button) findViewById(R.id.btn_update_teacher);
        img_btn_home = (ImageView) findViewById(R.id.img_btn_home_teacher);
        img_btn_back = (ImageView) findViewById(R.id.img_btn_back_teacher);
        img_avatar_teacher = (CircleImageView) findViewById(R.id.img_avatar_teacher);
        btn_update_teacher = (Button) findViewById(R.id.btn_update_teacher);
        btn_changepass_teacher = (Button) findViewById(R.id.btn_changepass_teacher);
        btn_choose_image = (Button) findViewById(R.id.btn_choose_image_teacher);

        rb_male_teacher = (RadioButton) findViewById(R.id.rb_male_teacher);
        rb_female_teacher = (RadioButton) findViewById(R.id.rb_female_teacher);
    }


    @Override
    public void showInforTeacher(String teacher_id, String teacher_name, String teacher_birth, String teacher_gender, String teacher_mail, String teacher_phone, String teacher_image) {
        edt_ID_teacher.setText(teacher_id);
        edt_email_teacher.setText(teacher_mail);
        edt_phone_teacher.setText(teacher_phone);
        edt_fullname_teacher.setText(teacher_name);
        edt_birthday_teacher.setText(teacher_birth);
        edt_major_teacher.setText("IT");
        hinhanhUrl = teacher_image;
        Picasso.get().load(teacher_image).into(img_avatar_teacher);
        if (teacher_gender.equals("nam")) {
            rb_male_teacher.setChecked(true);
        } else {
            rb_female_teacher.setChecked(true);
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

    @Override
    public void showInforTeacherMain(String teacher_id, String teacher_name, String teacher_image) {

    }

    private void disable() {
        edt_major_teacher.setFocusable(false);
        edt_ID_teacher.setFocusable(false);
        edt_email_teacher.setFocusable(false);
        edt_phone_teacher.setFocusable(false);
        edt_birthday_teacher.setFocusable(false);
        edt_fullname_teacher.setFocusable(false);
        rb_male_teacher.setEnabled(false);
        rb_female_teacher.setEnabled(false);
    }

    private void enable() {
        edt_email_teacher.setFocusableInTouchMode(true);
        edt_phone_teacher.setFocusableInTouchMode(true);
        edt_birthday_teacher.setFocusableInTouchMode(true);
        edt_fullname_teacher.setFocusableInTouchMode(true);
        edt_birthday_teacher.setFocusableInTouchMode(true);
        rb_male_teacher.setEnabled(true);
        rb_female_teacher.setEnabled(true);
    }

    public void updateInforTeacher(String hinhanhUrl) {
        String teacher_id = edt_ID_teacher.getText().toString().trim();
        String teacher_phone = edt_phone_teacher.getText().toString().trim();
        String teacher_birth = edt_birthday_teacher.getText().toString().trim();
        String teacher_name = edt_fullname_teacher.getText().toString().trim();
        String teacher_mail = edt_email_teacher.getText().toString().trim();
        String teacher_image = hinhanhUrl;
        String teacher_gender;
        if (rb_male_teacher.isChecked()) {
            teacher_gender = "nam";
        } else {
            teacher_gender = "nu";
        }
        profileTeacherPresenter.checkUpdate(teacher_id, teacher_name, teacher_birth, teacher_gender, teacher_mail, teacher_phone, hinhanhUrl, this);
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
        if(realpath.equals(""))
        {
            updateInforTeacher(hinhanhUrl);
        }
        else {
            File file = new File(realpath);
            String file_path = file.getAbsolutePath();
            String[] mangtenfile = file_path.split("\\.");

            file_path = mangtenfile[0] + System.currentTimeMillis() + "." + mangtenfile[1];
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            MultipartBody.Part body = MultipartBody.Part.createFormData("upload_file", file_path, requestBody);

            DataClient dataClient = APIUtils.getData();
            retrofit2.Call<String> callback = dataClient.Uploadphoto(body);
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response != null) {
                        String message = response.body();
                        hinhanhUrl = APIUtils.Base_Ur + "image/" + message;
                        updateInforTeacher(hinhanhUrl);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
            });
        }

    }
}
