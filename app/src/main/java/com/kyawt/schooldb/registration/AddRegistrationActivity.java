package com.kyawt.schooldb.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.course.adapter.CourseAdapter;
import com.kyawt.schooldb.course.dao.CourseDAO;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.registration.dao.RegisterDAO;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddRegistrationActivity extends AppCompatActivity {

    Button btn_cancel, btn_save;
    EditText et_register_id,et_register_date, et_stu_name, et_stu_nrc, et_stu_bd, et_father_name, et_father_nrc, et_father_ph, et_address, et_email, et_course_fees, et_course_duration;
    Spinner sp_course_name;

    String register_id,register_date, stu_name, stu_nrc, stu_bd, father_name, father_nrc, father_ph, address, email,course_name,course_fees, course_duration;


   ArrayList<String> courseNameList;
   ArrayAdapter<String> courseNameArrayAdapter;
   private AppDatabaseUtility appDatabaseUtility;
   List<CourseModel> courseModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_registration);

        btn_cancel = (Button) findViewById(R.id.btnCancel);
        btn_save = (Button) findViewById(R.id.btnAdd);

        et_register_date = (EditText) findViewById(R.id.etDate);
        et_stu_name = (EditText) findViewById(R.id.etStudentName);
        et_stu_nrc = (EditText) findViewById(R.id.etStudentNrc);
        et_stu_bd = (EditText) findViewById(R.id.etStudentBirthday);
        et_father_name = (EditText) findViewById(R.id.etParentName) ;
        et_father_nrc = (EditText) findViewById(R.id.etParentNrc);
        et_address = (EditText) findViewById(R.id.etAddress);
        et_father_ph = (EditText) findViewById(R.id.etParentPhone);
        et_email = (EditText) findViewById(R.id.etEmail);
        et_course_fees= (EditText) findViewById(R.id.etClassFees);
        et_course_duration = (EditText) findViewById(R.id.etDuration);

        sp_course_name = (Spinner) findViewById(R.id.spinCourse);

        courseNameList = new ArrayList<>();


        Buttons();
        new LoadDataTask().execute();
    }


    private void Buttons(){
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddRegistrationActivity.this, RegistrationListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( et_register_date.getText().toString().isEmpty()
                || et_stu_name.getText().toString().isEmpty()
                || et_stu_nrc.getText().toString().isEmpty()
                || et_stu_bd.getText().toString().isEmpty()
                || et_father_name.getText().toString().isEmpty()
                || et_father_nrc.getText().toString().isEmpty()
                || et_father_ph.getText().toString().isEmpty()
                || et_address.getText().toString().isEmpty()
                || et_email.getText().toString().isEmpty()
                || et_course_fees.getText().toString().isEmpty()
                || et_course_duration.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Required all Detail", Toast.LENGTH_LONG).show();
                }else {

                   // register_id = UUID.randomUUID().toString();
                    register_date = et_register_date.getText().toString().trim();
                    stu_name = et_stu_name.getText().toString().trim();
                    stu_nrc = et_stu_nrc.getText().toString().trim();
                    stu_bd = et_stu_bd.getText().toString().trim();
                    father_name = et_father_name.getText().toString().trim();
                    father_nrc = et_father_nrc.getText().toString().trim();
                    father_ph = et_father_ph.getText().toString().trim();
                    address = et_address.getText().toString().trim();
                    email = et_email.getText().toString().trim();
                    course_fees = et_course_fees.getText().toString().trim();
                    course_duration = et_course_duration.getText().toString().trim();
                    course_name = sp_course_name.getSelectedItem().toString().trim();
//                    ...............Toast roll no, name, contact no, gender


                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    RegisterModel registerModel = new RegisterModel(register_date, stu_name, stu_nrc, stu_bd, father_name, father_nrc, father_ph, address,email, course_name, Integer.parseInt(course_fees), Integer.parseInt(course_duration) );
                    appDatabaseUtility.insertRegisterTask(registerModel);

                    et_register_date.setText("");
                    et_stu_name.setText("");
                    et_stu_nrc.setText("");
                    et_stu_bd.setText("");
                    et_father_name.setText("");
                    et_father_nrc.setText("");
                    et_father_ph.setText("");
                    et_address.setText("");
                    et_email.setText("");
                    et_course_fees.setText("");
                    et_course_duration.setText("");
                    sp_course_name.setSelected(false);

                }
            }
        });
    }

    class LoadDataTask extends AsyncTask<Void,Void,Void> {

        AppDatabaseUtility appDatabaseUtility;
        List<CourseModel> courseModelList;

        @Override
        protected void onPreExecute() {
            appDatabaseUtility= new AppDatabaseUtility(getApplicationContext());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            courseModelList = appDatabaseUtility.getCourses();
            courseNameList = new ArrayList<>();

            for (int i=0; i<courseModelList.size(); i++){
                courseNameList.add(courseModelList.get(i).class_name);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            courseNameArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, courseNameList );
            sp_course_name.setAdapter(courseNameArrayAdapter);

        }

    }

    
}