package com.kyawt.schooldb.course;

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
import com.kyawt.schooldb.course.adapter.CourseAdapter;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.registration.AddRegistrationActivity;
import com.kyawt.schooldb.registration.RegistrationListActivity;
import com.kyawt.schooldb.student.StudentListActivity;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    CardView btn_back;
    FloatingActionButton fabAddNewCourse;

    private static RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private static  RecyclerView recyclerView;
    private CourseAdapter courseAdapter;

    ArrayList<CourseModel> courseModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        btn_back = (CardView) findViewById(R.id.back);
        fabAddNewCourse = (FloatingActionButton) findViewById(R.id.fabAddNewCourse);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerCourse);
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
                Intent intent = new Intent(CourseListActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fabAddNewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseListActivity.this, AddCourseActivity.class);
                startActivity(intent);
                finish();
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
            courseModelArrayList = new ArrayList<>();

            for (int i=0; i<courseModelList.size(); i++){
                courseModelArrayList.add(courseModelList.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            courseAdapter= new CourseAdapter(courseModelArrayList, CourseListActivity.this);
            recyclerView.setAdapter(courseAdapter);
        }

    }
    
    @Override
    protected void onRestart() {
        super.onRestart();
        new LoadDataTask().execute();
    }
}