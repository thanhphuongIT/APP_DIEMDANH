package com.spkt.app_student_attendance.presenter;

import com.spkt.app_student_attendance.view.IProfileStudentView;

public interface IProfileStudentPresenter {
    void getIDStudent(String ID_Student, IProfileStudentView context);
    void checkUpdate(String student_id, String student_name,String student_birth,String student_gender,String student_mail,String student_phone, IProfileStudentView iProfileStudentView);
}
