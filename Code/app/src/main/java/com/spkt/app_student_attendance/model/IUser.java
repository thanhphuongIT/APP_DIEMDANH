package com.spkt.app_student_attendance.model;

public interface IUser {
        String getUsername();
        String getPassword();
        Boolean checkUserValidity(String user, String password);
}
