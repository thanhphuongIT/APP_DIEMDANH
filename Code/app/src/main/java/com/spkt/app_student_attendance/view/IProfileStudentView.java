package com.spkt.app_student_attendance.view;

public interface  IProfileStudentView {
    void showInforStudent(String student_id, String student_name,String student_birth,String student_gender,String student_mail,String student_phone);
    void updateSuccessfully(int result);
}
