package com.kyawt.schooldb.teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kyawt.schooldb.R;
import com.kyawt.schooldb.bottomnav.HomeActivity;
import com.kyawt.schooldb.course.AddCourseActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.course.adapter.CourseAdapter;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.teacher.adapter.TeacherAdapter;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;

public class TeacherListActivity extends AppCompatActivity {

    CardView btn_back;
    FloatingActionButton fabAddNewTeacher;

    private static RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private static  RecyclerView recyclerView;
    private TeacherAdapter teacherAdapter;

    ArrayList<TeacherModel> teacherModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        btn_back = (CardView) findViewById(R.id.back);
        fabAddNewTeacher = (FloatingActionButton) findViewById(R.id.fabAddNewTeacher);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerTeacher);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        action();
        new LoadDataTask().execute();

    }


    private void action() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherListActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fabAddNewTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherListActivity.this, AddTeacherActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    class LoadDataTask extends AsyncTask<Void,Void,Void> {

        AppDatabaseUtility appDatabaseUtility;
        List<TeacherModel> teacherModelList;

        @Override
        protected void onPreExecute() {
            appDatabaseUtility= new AppDatabaseUtility(getApplicationContext());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            teacherModelList = appDatabaseUtility.getTeacher();
            teacherModelArrayList = new ArrayList<>();

            for (int i=0; i<teacherModelList.size(); i++){
                teacherModelArrayList.add(teacherModelList.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            teacherAdapter= new TeacherAdapter(teacherModelArrayList, TeacherListActivity.this);
            recyclerView.setAdapter(teacherAdapter);

        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new LoadDataTask().execute();
    }
}