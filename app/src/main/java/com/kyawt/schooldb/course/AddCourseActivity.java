package com.kyawt.schooldb.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.registration.AddRegistrationActivity;
import com.kyawt.schooldb.registration.RegistrationListActivity;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.UUID;

public class AddCourseActivity extends AppCompatActivity {

    Button btn_cancel, btn_save;
    EditText et_class_code, et_class_name;
    String class_code, class_name, admin_email, admin_password,admin_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        btn_cancel = (Button) findViewById(R.id.btnCancel);
        btn_save = (Button) findViewById(R.id.btnAdd);
        et_class_code = (EditText) findViewById(R.id.etClassCode);
        et_class_name = (EditText) findViewById(R.id.etClassName);

        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            admin_email = data.getString("key_for_email");
            admin_password = data.getString("key_for_password");
            admin_username = data.getString("key_for_username");
        }

//        ----------- pass data  by intent end----------------

        Buttons();
    }

    private void Buttons(){
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCourseActivity.this, CourseListActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_class_code.getText().toString().isEmpty()
                        || et_class_name.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Required all Detail", Toast.LENGTH_LONG).show();
                }else {
                    class_code = et_class_code.getText().toString().trim();
                    class_name = et_class_name.getText().toString().trim();

//                    ...............Toast roll no, name, contact no, gender

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    CourseModel courseModel = new CourseModel(Integer.parseInt(class_code), class_name);
                    appDatabaseUtility.insertTask(courseModel);

                    et_class_name.setText("");
                    et_class_code.setText("");

                }
            }
        });
    }
}