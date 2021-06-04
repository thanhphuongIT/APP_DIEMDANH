package com.spkt.app_student_attendance.Retrofit;

import com.spkt.app_student_attendance.model.IPConfigModel;

public class APIUtils {
    private static IPConfigModel ipConfigModel;
    public static String Base_Ur = "";
    public static DataClient getData(){
        ipConfigModel = new IPConfigModel();
        Base_Ur="http://"+ipConfigModel.getIpconfig()+"/student_attendence/";
        return RetrofitClient.getClient(Base_Ur).create(DataClient.class);
    }
}
