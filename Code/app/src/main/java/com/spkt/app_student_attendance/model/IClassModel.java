package com.spkt.app_student_attendance.model;

import com.spkt.app_student_attendance.view.IAttendenceTeacherView;
import com.spkt.app_student_attendance.view.IPresentStudentView;

public interface IClassModel {
    String getClass_id();

    String getClass_name();

    String getClass_idteacher();

    int getClass_Totalstudent();

    void getDataClassForIDTeacher(String id,IAttendenceTeacherView context);
    void getDataClassForIDStudent(String id, IPresentStudentView context);
}
