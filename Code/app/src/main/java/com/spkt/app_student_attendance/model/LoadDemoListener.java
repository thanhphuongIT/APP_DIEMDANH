package com.spkt.app_student_attendance.model;

import com.spkt.app_student_attendance.model.entity.Demo;

import java.util.List;

/**
 * Created by LynkMieu on 7/6/2017.
 */

public interface LoadDemoListener {
    void onLoadDemoSuccess(List<Demo> listDemo);
    void onLoadDemoFailure(String message);
}
