package com.kyawt.schooldb.timetable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.kyawt.schooldb.course.CourseDetailActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.SubjectModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.model.TimetableModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DetailTimetableActivity extends AppCompatActivity {

    Button btn_update, btn_delete, btn_cancel;
    Spinner sp_day, sp_course, sp_subject, sp_teacher, sp_am_pm, sp_AM_PM;
    EditText et_start_time, et_finish_time;
    int  timetable_id;
    ArrayList<String> courseNameList;
    ArrayList<String> teacherNameList;
    ArrayList<String> subjectNameList;
    ArrayList<String> dayNameList;
    ArrayList<String> am_pmList;
    ArrayList<String> AM_PMList;
    ArrayAdapter<String> teacherArrayAdapter;
    ArrayAdapter<String> subjectArrayAdapter;
    ArrayAdapter<String> am_pmAdapter;
    ArrayAdapter<String> AM_PMAdapter;
    ArrayAdapter<String> courseArrayAdapter;
    ArrayAdapter<String> dayArrayAdapter;
    List<TimetableModel> timetableModelList;
    List<TeacherModel> teacherModelList;
    List<CourseModel> courseModelList;
    List<SubjectModel> subjectModelList;
    private ArrayList<TimetableModel> timetableModelArrayList = new ArrayList<>();
    private ArrayList<CourseModel> courseModelArrayList = new ArrayList<>();
    private ArrayList<TeacherModel> teacherModelArrayList = new ArrayList<>();
    private ArrayList<SubjectModel> subjectModelArrayList = new ArrayList<>();

    String day = "", course_name = "", subject_name = "", teacher_name = "", start_time = "", end_time = "", am_pm="", AM_PM="",admin_email="",admin_password="",admin_username="";
    String day_to_update = "", course_name_to_update = "", subject_name_to_update="", teacher_name_to_update = "", start_time_to_update = "", end_time_to_update = "";
    String day_to_delete = "", course_name_to_delete = "", subject_name_to_delete="", teacher_name_to_delete = "", start_time_to_delete = "", end_time_to_delete = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_timetable);


        sp_day = (Spinner) findViewById(R.id.spinner_day);
        sp_course = (Spinner) findViewById(R.id.spinner_course);
        sp_subject = (Spinner) findViewById(R.id.spinner_subject);
        sp_teacher = (Spinner) findViewById(R.id.spinner_teacher);
        et_start_time = (EditText) findViewById(R.id.etStartTime);
        et_finish_time = (EditText) findViewById(R.id.etEndTime);
        btn_update = (Button) findViewById(R.id.btnUpdate);
        btn_delete = (Button) findViewById(R.id.btnDelete);
        btn_cancel = (Button) findViewById(R.id.btnCancel);
        sp_am_pm = (Spinner) findViewById(R.id.sp_am_pm);
        sp_AM_PM = (Spinner) findViewById(R.id.sp_Am_Pm);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            timetable_id = data.getInt("key_for_timetable_id");
            day = data.getString("key_for_day");
            course_name = data.getString("key_for_course_name");
            subject_name = data.getString("key_for_subject_name");
            teacher_name = data.getString("key_for_teacher_name");
            start_time = data.getString("key_for_start_time");
            end_time = data.getString("key_for_finish_time");
        }

        //        ----------- pass data by intent start-------------
        Bundle data1 = getIntent().getExtras();
        if (data1 != null){
            admin_email = data1.getString("key_for_email");
            admin_password = data1.getString("key_for_password");
            admin_username = data1.getString("key_for_username");
        }

//        ----------- pass data  by intent end----------------


        if (start_time == null || end_time == null){
            et_start_time.setText("");
            et_finish_time.setText("");
        }else if (start_time.length() == 6){
            et_start_time.setText(start_time.subSequence(0,3)+ "");
        }

        new LoadDataTask().execute();
        Buttons();
        daySpinner();
        am_pmSpinner();
        AM_PMSpinner();
        loadSpinner();
    }

    private void daySpinner(){
        dayNameList = new ArrayList<>();
        dayNameList.add("Monday");
        dayNameList.add("Tuesday");
        dayNameList.add("Wednesday");
        dayNameList.add("Thursday");
        dayNameList.add("Friday");
        dayNameList.add("Saturday");
        dayNameList.add("Sunday");
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
        dayArrayAdapter = new ArrayAdapter<>(DetailTimetableActivity.this,R.layout.support_simple_spinner_dropdown_item,dayNameList);
        sp_day.setAdapter(dayArrayAdapter);

        am_pmAdapter = new ArrayAdapter<>(DetailTimetableActivity.this, R.layout.support_simple_spinner_dropdown_item,am_pmList);
        sp_am_pm.setAdapter(am_pmAdapter);

        AM_PMAdapter = new ArrayAdapter<>(DetailTimetableActivity.this, R.layout.support_simple_spinner_dropdown_item,AM_PMList);
        sp_AM_PM.setAdapter(AM_PMAdapter);
    }

    public void Buttons(){
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_start_time.getText().toString().trim().isEmpty()
                        || et_finish_time.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Required data", Toast.LENGTH_LONG).show();
                }else {
                    day_to_update = sp_day.getSelectedItem().toString().trim();
                    course_name_to_update = sp_course.getSelectedItem().toString().trim();
                    subject_name_to_update = sp_subject.getSelectedItem().toString().trim();
                    teacher_name_to_update = sp_teacher.getSelectedItem().toString().trim();
                    start_time_to_update = et_start_time.getText().toString().trim();
                    end_time_to_update = et_finish_time.getText().toString().trim();

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    TimetableModel timetableModel = new TimetableModel(day_to_update,course_name_to_update,subject_name_to_update,teacher_name_to_update,start_time_to_update,end_time_to_update);
                    appDatabaseUtility.updateTimetableTask(day_to_update,subject_name_to_update,start_time_to_update,end_time_to_update,course_name_to_update,teacher_name_to_update,timetable_id);

                    Intent intent = new Intent(DetailTimetableActivity.this, TimetableListActivity.class);
                    intent.putExtra("key_for_email",admin_email);
                    intent.putExtra("key_for_password",admin_password );
                    intent.putExtra("key_for_username", admin_username);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Updated Timetable Info!", Toast.LENGTH_LONG).show();
                }
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day_to_delete = sp_day.getSelectedItem().toString().trim();
                course_name_to_delete = sp_course.getSelectedItem().toString().trim();
                subject_name_to_delete = sp_subject.getSelectedItem().toString().trim();
                teacher_name_to_delete = sp_teacher.getSelectedItem().toString().trim();
                start_time_to_delete = et_start_time.getText().toString().trim();
                end_time_to_delete = et_start_time.getText().toString().trim();

                TimetableModel timetableModel = new TimetableModel(day_to_delete,course_name_to_delete,subject_name_to_delete,teacher_name_to_delete,start_time_to_delete,end_time_to_delete);
                delete_dialog(timetable_id);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailTimetableActivity.this, TimetableListActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
            }
        });
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

            timetableModelList = appDatabaseUtility.getTimetable();
            courseModelList = appDatabaseUtility.getCourses();
            teacherModelList = appDatabaseUtility.getTeacher();
            subjectModelList = appDatabaseUtility.getSubject();
            courseNameList = new ArrayList<>();
            teacherNameList = new ArrayList<>();
            subjectNameList = new ArrayList<>();
            dayNameList = new ArrayList<>();
            am_pmList = new ArrayList<>();
            AM_PMList = new ArrayList<>();

            for (int i = 0; i < courseModelList.size(); i++) {
                courseNameList.add(courseModelList.get(i).class_name);
            }

            for (int i = 0; i < teacherModelList.size(); i++) {
                teacherNameList.add(teacherModelList.get(i).teacher_name);
            }

            for (int i = 0; i < subjectModelList.size(); i++) {
                subjectNameList.add(subjectModelList.get(i).sub_name);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            courseArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, courseNameList);
            sp_course.setAdapter(courseArrayAdapter);

            teacherArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, teacherNameList);
            sp_teacher.setAdapter(teacherArrayAdapter);

            subjectArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, subjectNameList);
            sp_subject.setAdapter(subjectArrayAdapter);

        }

    }

    //    ............... Delete data start ................

    public void delete_dialog(int timetable_id){
        final int timetable_about_to_delete = timetable_id;

        AlertDialog.Builder builder= new AlertDialog.Builder(DetailTimetableActivity.this);
        builder.setTitle("WARNING");
        builder.setMessage("Are you sure to delete?");
        builder.setIcon(R.drawable.ic__remove);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                appDatabaseUtility.deleteTimetableTask(timetable_about_to_delete);
                Toast.makeText(getApplicationContext(), "Timetable for"+ course_name+" is deleted.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

//    ............... Delete data end ................

}