package com.kyawt.schooldb.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.ParentModel;
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.model.SubjectModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.model.TimetableModel;
import com.kyawt.schooldb.registration.AddRegistrationActivity;
import com.kyawt.schooldb.registration.RegistrationListActivity;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AddTimetableActivity extends AppCompatActivity {

    Button btn_save, btn_cancel;
    Spinner sp_day,sp_course_name, sp_sub_name, sp_teacher_name, sp_AM_PM, sp_am_pm;
    EditText et_start_time, et_finish_time;
    String course_name, start_time, finish_time, sub_name, teacher_name, day, st_time, en_time,admin_email="",admin_password="",admin_username="";
    private AppDatabaseUtility appDatabaseUtility;
    List<CourseModel> courseModelList;
    List<TeacherModel> teacherModelList;
    List<SubjectModel> subjectModelList;

    ArrayList<String> dayArrayList;
    ArrayList<String> courseNameList;
    ArrayList<String> subjectNameList;
    ArrayList<String> teacherNameList;
    ArrayList<String> am_pmList;
    ArrayList<String> AM_PMList;
    ArrayAdapter<String > dayArrayAdapter;
    ArrayAdapter<String> courseArrayAdapter;
    ArrayAdapter<String> subjectArrayAdapter;
    ArrayAdapter<String> teacherArrayAdapter;
    ArrayAdapter<String> am_pmAdapter;
    ArrayAdapter<String> AM_PMAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable);
        sp_day = (Spinner) findViewById(R.id.spinner_day);
        sp_course_name = (Spinner) findViewById(R.id.spinner_course);
        et_start_time = (EditText) findViewById(R.id.etStartTime);
        et_finish_time = (EditText) findViewById(R.id.etEndTime);
        sp_sub_name = (Spinner) findViewById(R.id.spinner_subject);
        sp_teacher_name = (Spinner) findViewById(R.id.spinner_teacher);
        btn_cancel = (Button) findViewById(R.id.btnCancel);
        btn_save = (Button) findViewById(R.id.btnAdd);
        sp_AM_PM = (Spinner) findViewById(R.id.sp_Am_Pm);
        sp_am_pm = (Spinner) findViewById(R.id.sp_am_pm);

        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            admin_email = data.getString("key_for_email");
            admin_password = data.getString("key_for_password");
            admin_username = data.getString("key_for_username");
        }

//        ----------- pass data  by intent end----------------


        daySpinner();
        am_pmSpinner();
        AM_PMSpinner();
        loadSpinner();
        Buttons();
        new LoadDataTask().execute();
    }

    private void daySpinner(){
        dayArrayList = new ArrayList<>();
        dayArrayList.add("Monday");
        dayArrayList.add("Tuesday");
        dayArrayList.add("Wednesday");
        dayArrayList.add("Thursday");
        dayArrayList.add("Friday");
        dayArrayList.add("Saturday");
        dayArrayList.add("Sunday");
    }

    private void am_pmSpinner(){
        am_pmList = new ArrayList<>();
        am_pmList.add("AM");
        am_pmList.add("PM");
    }

    private void AM_PMSpinner(){
        AM_PMList = new ArrayList<>();
        AM_PMList.add("AM");
        AM_PMList.add("PM");
    }

    private void loadSpinner(){
        dayArrayAdapter = new ArrayAdapter<>(AddTimetableActivity.this,R.layout.support_simple_spinner_dropdown_item,dayArrayList);
        sp_day.setAdapter(dayArrayAdapter);

        am_pmAdapter = new ArrayAdapter<>(AddTimetableActivity.this, R.layout.support_simple_spinner_dropdown_item,am_pmList);
        sp_am_pm.setAdapter(am_pmAdapter);

        AM_PMAdapter = new ArrayAdapter<>(AddTimetableActivity.this, R.layout.support_simple_spinner_dropdown_item,AM_PMList);
        sp_AM_PM.setAdapter(AM_PMAdapter);
    }

    private void Buttons(){
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTimetableActivity.this, TimetableListActivity.class);
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
                if (et_start_time.getText().toString().isEmpty()
                        || et_finish_time.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Required all Detail", Toast.LENGTH_LONG).show();
                }else {

                    day = sp_day.getSelectedItem().toString().trim();
                    course_name = sp_course_name.getSelectedItem().toString().trim();
                    sub_name = sp_sub_name.getSelectedItem().toString().trim();
                    start_time = et_start_time.getText().toString().trim();
                    finish_time = et_finish_time.getText().toString().trim();
                    teacher_name = sp_teacher_name.getSelectedItem().toString().trim();
                    st_time = sp_am_pm.getSelectedItem().toString().trim();
                    en_time = sp_AM_PM.getSelectedItem().toString().trim();

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    TimetableModel timetableModel = new TimetableModel(day,course_name,sub_name,start_time+st_time,finish_time+en_time,teacher_name);
                    appDatabaseUtility.insertTimetableTask(timetableModel);

                    et_start_time.setText("");
                    et_finish_time.setText("");
                }
            }
        });
    }


    class LoadDataTask extends AsyncTask<Void,Void,Void> {

        AppDatabaseUtility appDatabaseUtility;

        List<CourseModel> courseModelList;
        List<TeacherModel> teacherModelList;
        List<SubjectModel> subjectModelList;

        @Override
        protected void onPreExecute() {
            appDatabaseUtility= new AppDatabaseUtility(getApplicationContext());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            courseModelList = appDatabaseUtility.getCourses();
            courseNameList = new ArrayList<>();
            subjectModelList = appDatabaseUtility.getSubject();
            subjectNameList = new ArrayList<>();
            teacherModelList = appDatabaseUtility.getTeacher();
            teacherNameList = new ArrayList<>();

            for (int i=0; i<courseModelList.size(); i++){
                courseNameList.add(courseModelList.get(i).class_name);
            }

            for (int i=0; i<subjectModelList.size(); i++){
                subjectNameList.add(subjectModelList.get(i).sub_name);
            }

            for (int i=0; i<teacherModelList.size(); i++){
                teacherNameList.add(teacherModelList.get(i).teacher_name);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            courseArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, courseNameList );
            sp_course_name.setAdapter(courseArrayAdapter);

            teacherArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, teacherNameList );
            sp_teacher_name.setAdapter(teacherArrayAdapter);

            subjectArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, subjectNameList );
            sp_sub_name.setAdapter(subjectArrayAdapter);

        }

    }

}