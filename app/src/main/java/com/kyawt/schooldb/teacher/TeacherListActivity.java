package com.kyawt.schooldb.teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
import java.util.Locale;

public class TeacherListActivity extends AppCompatActivity {

    CardView btn_back;
    EditText et_search;
    FloatingActionButton fabAddNewTeacher;

    private static RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private static  RecyclerView recyclerView;
    private TeacherAdapter teacherAdapter;
    String admin_email="",admin_password="",admin_username="";

    ArrayList<TeacherModel> teacherModelArrayList,teacherSearchList;

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


//       ----------------search-----------------

        et_search = (EditText) findViewById(R.id.et_search);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                Search(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//       ----------------search-----------------

        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            admin_email = data.getString("key_for_email");
            admin_password = data.getString("key_for_password");
            admin_username = data.getString("key_for_username");
        }

//        ----------- pass data  by intent end----------------

        action();
        new LoadDataTask().execute();

    }


    private void action() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherListActivity.this, HomeActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });

        fabAddNewTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherListActivity.this, AddTeacherActivity.class);
                intent.putExtra("key_for_admin_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
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
            teacherSearchList = new ArrayList<>();


            for (int i=0; i<teacherModelList.size(); i++){
                teacherModelArrayList.add(teacherModelList.get(i));
                teacherSearchList.add(teacherModelList.get(i));
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

    //........... Filter start...........................
    public void Search(String txtSearch){

        txtSearch = txtSearch.toLowerCase(Locale.getDefault());
        Log.d("Filter", txtSearch+"");
        teacherModelArrayList.clear();
        if (txtSearch.length() == 0){
            teacherModelArrayList.addAll(teacherSearchList);
            Log.d("Load Data", "All");
        }else {
            Log.d("Load", "Filtered");

            for (TeacherModel teacherModel:teacherSearchList){
                if (teacherModel.teacher_name.toLowerCase(Locale.getDefault()).contains(txtSearch)
                        || teacherModel.teacher_email.toLowerCase(Locale.getDefault()).contains(txtSearch)){
                    teacherModelArrayList.add(teacherModel);
                }
            }
        }

        teacherAdapter.notifyDataSetChanged();
    }
    //........... Filter end...........................

    @Override
    protected void onRestart() {
        super.onRestart();
        new LoadDataTask().execute();
    }
}