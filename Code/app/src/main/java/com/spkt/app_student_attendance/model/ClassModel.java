package com.spkt.app_student_attendance.model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.spkt.app_student_attendance.view.IAttendenceTeacherView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassModel extends AppCompatActivity implements IClass {
    private String class_id;
    private String class_name;
    private String class_idteacher;
    private int class_totalstudent;
    // Db get
    private String dbclass_id;
    private String dbclass_name;
    private String dbclass_idteacher;
    private int dbclass_totalstudent;
    private IAttendenceTeacherView iAttendenceTeacherView;

    public ClassModel() {

    }
    public ClassModel(String id, IAttendenceTeacherView context) {
        this.class_idteacher = id;
        this.iAttendenceTeacherView = context;
    }

    public ClassModel(String Class_id, String Class_name, String Class_idteacher, int Class_totalstudent) {
        this.class_id = Class_id;
        this.class_name = Class_name;
        this.class_idteacher = Class_idteacher;
        this.class_totalstudent = Class_totalstudent;
    }

    @Override
    public String getClass_id() {
        return this.class_id;
    }

    @Override
    public String getClass_name() {
        return this.class_name;
    }

    @Override
    public String getClass_idteacher() {
        return this.class_idteacher;
    }

    @Override
    public int getClass_Totalstudent() {
        return this.class_totalstudent;
    }

    @Override
    public void getDataClassForID(String id, IAttendenceTeacherView context) {
        String url = "http://192.168.1.13/student_attendence/getclassforteacher.php";
        RequestQueue requestQueue = Volley.newRequestQueue((Context) context);
        // requestQueue.start();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Error")) {
                } else {
                    try {
                        JSONObject object = new JSONObject(response);
                        dbclass_id = object.getString("class_id").trim();
                        dbclass_name = object.getString("class_name").trim();
                        dbclass_idteacher = object.getString("teacher_id").trim();
                        dbclass_totalstudent = object.getInt("totalstudent");
                        ClassModel class_data = new ClassModel(dbclass_id, dbclass_name, dbclass_idteacher, dbclass_totalstudent);
                        ArrayList<ClassModel> ListClass = new ArrayList<ClassModel>();
                        ListClass.add(class_data);
                        iAttendenceTeacherView.onListClassResult(ListClass);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText((Context) iAttendenceTeacherView, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("teacher_id", class_idteacher);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
