package com.spkt.app_student_attendance.presenter;

import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.spkt.app_student_attendance.model.AttendanceStudentModel;
import com.spkt.app_student_attendance.model.IAttendanceStudentModel;
import com.spkt.app_student_attendance.view.IAttendanceStudentView;

public class AttendanceStudentPresenter extends AppCompatActivity implements IAttendanceStudentPresenter {
    IAttendanceStudentView iAttendanceStudentView;
    IAttendanceStudentModel infoAttendanceStudentModel;
    Handler handler;

    public AttendanceStudentPresenter(IAttendanceStudentView iAttendanceStudentView) {
        this.iAttendanceStudentView = iAttendanceStudentView;
        handler = new Handler(Looper.getMainLooper());
    }

    private void initAttendanceStudent(String class_id, String student_id, String attendance_time, String status, IAttendanceStudentView iAttendanceStudentView) {
        infoAttendanceStudentModel = new AttendanceStudentModel(class_id, student_id, attendance_time, status, iAttendanceStudentView);
    }

    @Override
    public void AddAttendanceStudentPresenter(String class_id, String student_id, String attendance_time, String status, IAttendanceStudentView AttendanceStudentView) {
        initAttendanceStudent(class_id, student_id, attendance_time, status, iAttendanceStudentView);
        infoAttendanceStudentModel.AddAttendanceStudentModel(iAttendanceStudentView);
    }
}
