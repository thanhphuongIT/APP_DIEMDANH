package com.spkt.app_student_attendance.model;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel implements IUser {
    private String username;
    private String password;
    private DBConnector dbConnector = new DBConnector();
    public UserModel() {
    }

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Boolean checkUserValidity(String user, String password) {
        Boolean result = false;
        ResultSet rSet;
        try {
            Connection conn = dbConnector.getCon();
            PreparedStatement st = conn.prepareStatement(("SELECT username, password where username=? and password=?"));
            st.setString(1, user);
            st.setString(2, password);
            rSet = st.executeQuery();
            result= rSet.next();

            if (result == true) {
                if (rSet.getInt("username") > 0) {
                    st.close();
                }
            }
        } catch (SQLException e) {
            Log.e("SQL Error", e.getMessage());
        }

        return result;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

//class connect extends AsyncTask<Void, String, String> {
//
//    @Override
//    protected String doInBackground(Void... strings) {
//        String response = "";
//
//        String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
//        String DATABASE_URL = "jdbc:mysql://192.168.1.14:3306/student_attendence";
//        String USERNAME = "PhuongBT";
//        String PASSWORD = "1234567890";
//
//        try {
//            Class.forName(DATABASE_DRIVER);
//            Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
//            System.out.println("Database connection success");
//            response = "ok";
//        }
//        catch(Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//        return response;
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//        System.out.println(s);
//    }
//}

