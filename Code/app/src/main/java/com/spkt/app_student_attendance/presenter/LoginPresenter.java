package com.spkt.app_student_attendance.presenter;

import android.content.Context;
import android.os.Looper;
import android.app.Activity ;
import android.content.Intent;

import com.spkt.app_student_attendance.StudentActivity;
import com.spkt.app_student_attendance.model.IUser;
import com.spkt.app_student_attendance.model.UserModel;
import com.spkt.app_student_attendance.view.ILoginView;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPresenter extends AppCompatActivity implements ILoginPresenter {
    ILoginView iLoginView;
    IUser userlogin;
    Handler handler;
//    private static final int REQUEST_CODE = 0x9345;
//    final Intent intent = new Intent(this, UserModel.class);
    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        handler = new Handler(Looper.getMainLooper());
    }
    private void initUser(String usern, String pass, ILoginView loginView){
        userlogin = new UserModel(usern,pass, loginView);
    }
    @Override
    public void doLogin(String user, String password, ILoginView loginView) {
        initUser(user,password, loginView);
        userlogin.checkUserValidity(loginView);
//      Toast.makeText(this, userlogin.getResults().toString(), Toast.LENGTH_LONG).show();
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Toast.makeText(this, "m co vo day ko", Toast.LENGTH_LONG).show();
//        super.onActivityResult(requestCode, resultCode, data);
//        // kiểm tra
//        if (requestCode == REQUEST_CODE) {
//            // RESULT_OK chỉ ra rằng kết quả này đã thành công
//            if (resultCode == Activity.RESULT_OK) {
//                // Nhận dữ liệu từ Intent trả về
//                final String result = data.getStringExtra("key");
//                // Sử dụng kết quả result bằng cách hiện Toast
//                Toast.makeText(this, "Result: " + result, Toast.LENGTH_LONG).show();
//            } else {
//
//                // DetailActivity không thành công, không có data trả về.
//            }
//        }
//    }

}
