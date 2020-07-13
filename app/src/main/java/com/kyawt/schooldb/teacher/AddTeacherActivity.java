package com.kyawt.schooldb.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.AddCourseActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

public class AddTeacherActivity extends AppCompatActivity {

    EditText et_teacher_name, et_teacher_nrc, et_teacher_dob, et_teacher_address, et_teacher_ph, et_teacher_email;
    Button btnSave, btnCancel;
    RadioButton rdbtn_male, rdbtn_female;

    String teacher_id, teacher_name, teacher_gender, teacher_nrc, teacher_dob, teacher_address, teacher_ph, teacher_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        et_teacher_name = (EditText) findViewById(R.id.etTeacherName);
        et_teacher_nrc = (EditText) findViewById(R.id.etTeacherNrc);
        et_teacher_dob = (EditText) findViewById(R.id.etTeacherBirthday);
        et_teacher_address = (EditText) findViewById(R.id.etTeacherAddress);
        et_teacher_ph = (EditText) findViewById(R.id.etTeacherPhone);
        et_teacher_email = (EditText) findViewById(R.id.etTeacherEmail);
        rdbtn_female = (RadioButton) findViewById(R.id.rdbtn_teacher_female);
        rdbtn_male = (RadioButton) findViewById(R.id.rdbtn_teacher_male);
        btnSave = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        Buttons();

    }

    private void Buttons(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTeacherActivity.this, TeacherListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_teacher_name.getText().toString().isEmpty()
                        || et_teacher_dob.getText().toString().isEmpty()
                        || et_teacher_address.getText().toString().isEmpty()
                        || et_teacher_email.getText().toString().isEmpty()
                        || et_teacher_nrc.getText().toString().isEmpty()
                        || et_teacher_ph.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Required all Detail", Toast.LENGTH_LONG).show();
                }else {
                    teacher_name = et_teacher_name.getText().toString().trim();
                    teacher_dob = et_teacher_dob.getText().toString().trim();
                    teacher_address = et_teacher_address.getText().toString().trim();
                    teacher_email = et_teacher_email.getText().toString().trim();
                    teacher_nrc = et_teacher_nrc.getText().toString().trim();
                    teacher_ph = et_teacher_ph.getText().toString().trim();

//                    ...............Toast roll no, name, contact no, gender

                    if (rdbtn_male.isChecked()){
                        teacher_gender="Male";
                    }else if (rdbtn_female.isChecked()){
                        teacher_gender="Female";
                    }

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    TeacherModel teacherModel = new TeacherModel(teacher_name, teacher_gender,teacher_nrc, teacher_dob,teacher_address, teacher_ph, teacher_email );
                    appDatabaseUtility.insertTeacherTask(teacherModel);

                    et_teacher_name.setText("");
                    et_teacher_nrc.setText("");
                    et_teacher_dob.setText("");
                    et_teacher_address.setText("");
                    et_teacher_ph.setText("");
                    et_teacher_email.setText("");

                }
            }
        });
    }
}