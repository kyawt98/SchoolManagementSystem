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
import com.kyawt.schooldb.course.CourseDetailActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.SubjectModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.model.TimetableModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;

public class DetailTimetableActivity extends AppCompatActivity {

    Button btn_update, btn_delete, btn_cancel;
    Spinner sp_day, sp_course, sp_subject, sp_teacher, sp_am_pm, sp_AM_PM;
    EditText et_start_time, et_finish_time;
    int timetable_id;
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
    private ArrayList<TimetableModel> timetableModelArrayList = new ArrayList<>();

    String day = "", course_name = "", subject_name = "", teacher_name = "", start_time = "", end_time = "", am_pm="", AM_PM="";
    String day_to_update = "", course_name_to_update = "", subject_name_to_update, teacher_name_to_update = "", start_time_to_update = "", end_time_to_update = "";
    String day_to_delete = "", course_name_to_delete = "", subject_name_to_delete, teacher_name_to_delete = "", start_time_to_delete = "", end_time_to_delete = "";

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

        et_start_time.setText(start_time.subSequence(0,4)+ "");
        et_finish_time.setText(end_time.subSequence(0,4));
        if (am_pm == start_time.subSequence(5,6)){
            sp_am_pm.setSelected(true);
        }

        new LoadDataTask().execute();
        Buttons();

    }

    public void Buttons(){
//        btn_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (et_start_time.getText().toString().trim().isEmpty()
//                        || et_finish_time.getText().toString().trim().isEmpty()){
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
//
//
//        btn_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                course_code_to_delete = et_course_code.getText().toString().trim();
//                course_name_to_delete = et_course_name.getText().toString().trim();
//
//                CourseModel courseModel = new CourseModel(Integer.parseInt(course_code_to_delete), course_name_to_delete);
//                delete_dialog(courseModel);
//            }
//        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailTimetableActivity.this, TimetableListActivity.class);
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
            courseNameList = new ArrayList<>();
            teacherNameList = new ArrayList<>();
            subjectNameList = new ArrayList<>();
            dayNameList = new ArrayList<>();
            am_pmList = new ArrayList<>();
            AM_PMList = new ArrayList<>();

            for (int i = 0; i < timetableModelList.size(); i++) {
                courseNameList.add(timetableModelList.get(i).course_name);
            }

            for (int i = 0; i < timetableModelList.size(); i++) {
                teacherNameList.add(timetableModelList.get(i).teacher_name);
            }

            for (int i = 0; i < timetableModelList.size(); i++) {
                subjectNameList.add(timetableModelList.get(i).subject_name);
            }

            for (int i = 0; i < timetableModelList.size(); i++) {
                dayNameList.add(timetableModelList.get(i).day);
            }

            for (int i = 0; i < timetableModelList.size(); i++) {
                am_pmList.add(timetableModelList.get(i).start_time.substring(4,4));
            }

            for (int i = 0; i < timetableModelList.size(); i++) {
                AM_PMList.add(timetableModelList.get(i).finish_time.substring(4,4));
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

            dayArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, dayNameList);
            sp_day.setAdapter(dayArrayAdapter);

            am_pmAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, am_pmList);
            sp_am_pm.setAdapter(am_pmAdapter);

        }

    }

}