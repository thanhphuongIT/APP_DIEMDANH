package com.spkt.app_student_attendance.model;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.spkt.app_student_attendance.LoginActivity;
import com.spkt.app_student_attendance.StudentActivity;
import com.spkt.app_student_attendance.presenter.LoginPresenter;
import com.spkt.app_student_attendance.view.ILoginView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity ;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//import com.spkt.app_student_attendance.StudentActivity;
//import com.vishnusivadas.advanced_httpurlconnection.FetchData;
//import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

public class UserModel extends AppCompatActivity implements IUser {
    private String username;
    private String password;
    private  String dbusername;
    private  String dbpassword;
    private String dbrole;
    private Boolean results;
    private ILoginView loginView;
    public UserModel() {
    }

    public UserModel(String username, String password, ILoginView loginView) {
        this.loginView = loginView;
        this.username = username;
        this.password = password;
    }
    @Override
    public Boolean getResults() {
        return results;
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
    public void getdata(Context context) {

    }

    @Override
    public void checkUserValidity(ILoginView context) {
        String url = "http://192.168.1.12/student_attendence/getdata.php";
        RequestQueue requestQueue = Volley.newRequestQueue((Context) context);
       // requestQueue.start();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Error")) {
                } else {
                    try {
                        JSONObject object = new JSONObject(response);
                        dbusername = object.getString("username").trim();
                        dbpassword = object.getString("password").trim();
                        dbrole = object.getString("i_role").trim();
                        if(object != null) {
                            loginView.onLoginResult(dbrole);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText((Context) loginView, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                // params.put("type","login");
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

