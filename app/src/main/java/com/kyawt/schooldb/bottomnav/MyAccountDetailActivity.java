package com.kyawt.schooldb.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.CourseDetailActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

public class MyAccountDetailActivity extends AppCompatActivity {

    String admin_email="", admin_password="",admin_username="";
    EditText et_username, et_email, et_current_password,et_new_password, et_retype_password ;
    Button btn_update, btn_cancel;

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
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
            }
        });

//        btn_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (et_course_code.getText().toString().trim().isEmpty()
//                        || et_course_name.getText().toString().trim().isEmpty()){
//                    Toast.makeText(getApplicationContext(), "Required data", Toast.LENGTH_LONG).show();
//                }else {
//                    course_code_to_update = et_course_code.getText().toString().trim();
//                    course_name_to_update = et_course_name.getText().toString().trim();
//
//                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
//                    CourseModel courseModel = new CourseModel(Integer.parseInt(course_code_to_update), course_name_to_update);
//                    appDatabaseUtility.updateTask(courseModel);
//
//                    Intent intent = new Intent(CourseDetailActivity.this, CourseListActivity.class);
//                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(),"Updated Course Info!", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }
}