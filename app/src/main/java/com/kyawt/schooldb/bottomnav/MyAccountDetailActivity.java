package com.kyawt.schooldb.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.CourseDetailActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.model.AdminModel;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

public class MyAccountDetailActivity extends AppCompatActivity {

    String admin_email="", admin_password="",admin_username="";
    EditText et_username, et_email, et_current_password,et_new_password, et_retype_password ;
    Button btn_update, btn_cancel;
    String email_update="", username_update="";
    int admin_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_detail);
        et_username = (EditText) findViewById(R.id.etAccNameDetail);
        et_email = (EditText) findViewById(R.id.etAdminEmailDetail);
        et_current_password = (EditText) findViewById(R.id.etAdminCurrentPassword);
        et_new_password = (EditText) findViewById(R.id.etAdminNewPassword);
        et_retype_password = (EditText) findViewById(R.id.etAdminRetypeNewPassword);
        btn_update = (Button) findViewById(R.id.btnUpdate);
        btn_cancel = (Button) findViewById(R.id.btnCancel);

        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            admin_id = data.getInt("key_for_admin_id");
            admin_email = data.getString("key_for_email");
            admin_password = data.getString("key_for_password");
            admin_username = data.getString("key_for_username");
        }
//        ----------- pass data by intent end----------------
        et_username.setText(admin_username);
        et_email.setText(admin_email);

        Buttons();
    }

    private void Buttons(){
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountDetailActivity.this, MyAccountActivity.class);
                intent.putExtra("key_for_admin_id",admin_id);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_username.getText().toString().trim().isEmpty()
                        || et_email.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Required data", Toast.LENGTH_LONG).show();
                }else {
                    email_update = et_email.getText().toString().trim();
                    username_update = et_username.getText().toString().trim();

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    AdminModel adminModel = new AdminModel(email_update,username_update,admin_password);
                    appDatabaseUtility.updateAdminTask(username_update,email_update,admin_id);
                    Intent intent = new Intent(MyAccountDetailActivity.this, MyAccountActivity.class);
                    intent.putExtra("key_for_email",admin_email);
                    intent.putExtra("key_for_password",admin_password );
                    intent.putExtra("key_for_username", admin_username);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Successfully Changes!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}