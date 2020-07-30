package com.kyawt.schooldb.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyawt.schooldb.MainActivity;
import com.kyawt.schooldb.R;
import com.kyawt.schooldb.model.AdminModel;
import com.kyawt.schooldb.model.StudentModel;
import com.kyawt.schooldb.student.StudentDetailActivity;
import com.kyawt.schooldb.student.StudentListActivity;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;

public class ChangePasswordActivity extends AppCompatActivity {
    Button btn_update, btn_cancel;
    int admin_id;
    String admin_email="", admin_password="", admin_username="";
    EditText et_current_password, et_new_password, et_retype_password;
    String current_password="", new_password="", retype_password="",password;
    String password_update="";
    List<AdminModel> adminModelList;
    ArrayList<AdminModel> adminModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        btn_cancel = (Button) findViewById(R.id.btnCancel);
        btn_update = (Button) findViewById(R.id.btnUpdate);
        et_current_password = (EditText) findViewById(R.id.etAdminCurrentPassword);
        et_new_password = (EditText) findViewById(R.id.etAdminNewPassword);
        et_retype_password = (EditText) findViewById(R.id.etAdminRetypeNewPassword);


        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            admin_id = data.getInt("key_for_admin_id");
            admin_email = data.getString("key_for_email");
            admin_password = data.getString("key_for_password");
            admin_username = data.getString("key_for_username");
        }
//        ----------- pass data by intent end----------------
        Buttons();
        new LoadDataTask().execute();
    }

    private void Buttons(){
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePasswordActivity.this, MyAccountActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputText()){
                    password_update = et_retype_password.getText().toString().trim();

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    AdminModel adminModel = new AdminModel(admin_email,admin_username,password_update);
                    appDatabaseUtility.updatePassword(password_update,admin_id);

                    Intent intent = new Intent(ChangePasswordActivity.this, MyAccountActivity.class);
                    intent.putExtra("key_for_admin_id",admin_id);
                    intent.putExtra("key_for_email",admin_email);
                    intent.putExtra("key_for_password",admin_password );
                    intent.putExtra("key_for_username", admin_username);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Successfully Changed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean inputText(){
        current_password = et_current_password.getText().toString().trim();
        new_password = et_new_password.getText().toString().trim();
        retype_password = et_retype_password.getText().toString().trim();

        if (current_password.isEmpty()){
            et_current_password.setError("Current Password is required");
            return false;
        }

        if (new_password.isEmpty()){
            et_new_password.setError("New Password is required");
            return false;
        }

        if (retype_password.isEmpty()){
            et_retype_password.setError("Confirm Password is required");
            return false;
        }

        if (!current_password.equals(password)){
            et_current_password.setError("Current Password is Wrong!!!");
            return false;
        }

        if (!new_password.equals(retype_password)){
            et_retype_password.setError("New Password are not match");
            return false;
        }
        return true;
    }

    class LoadDataTask extends AsyncTask<Void, Void, Void> {

        AppDatabaseUtility appDatabaseUtility;

        @Override
        protected void onPreExecute() {
            appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            adminModelList = appDatabaseUtility.getAdmin();
            adminModelArrayList = new ArrayList<>();

            for (int i = 0; i < adminModelList.size(); i++) {
                adminModelArrayList.add(adminModelList.get(i));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            password = adminModelArrayList.get(0).admin_password;
        }

    }
}