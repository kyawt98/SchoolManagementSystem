package com.kyawt.schooldb.subject;

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
import com.kyawt.schooldb.model.SubjectModel;
import com.kyawt.schooldb.subject.adapter.SubjectAdapter;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;

public class SubjectListActivity extends AppCompatActivity {
    CardView btn_back;
    FloatingActionButton fabAddNewSubject;
    private static RecyclerView recyclerView;
    private  RecyclerView.LayoutManager layoutManager;
    private SubjectAdapter subjectAdapter;

    String admin_email="",admin_password="",admin_username="";
    ArrayList<SubjectModel> subjectModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        btn_back = (CardView) findViewById(R.id.back);
        fabAddNewSubject = (FloatingActionButton) findViewById(R.id.fabAddNewSubject);
        recyclerView = (RecyclerView) findViewById(R.id.recycer_subject);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            admin_email = data.getString("key_for_email");
            admin_password = data.getString("key_for_password");
            admin_username = data.getString("key_for_username");
        }

//        ----------- pass data  by intent end----------------

        action();
        new SubjectListActivity.LoadDataTask().execute();

    }

    private void action() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectListActivity.this, HomeActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });

        fabAddNewSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectListActivity.this, AddSubjectActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });
    }

    class LoadDataTask extends AsyncTask<Void,Void,Void> {

        AppDatabaseUtility appDatabaseUtility;
        List<SubjectModel> subjectModelList;

        @Override
        protected void onPreExecute() {
            appDatabaseUtility= new AppDatabaseUtility(getApplicationContext());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            subjectModelList = appDatabaseUtility.getSubject();
            subjectModelArrayList = new ArrayList<>();

            for (int i=0; i<subjectModelList.size(); i++){
                subjectModelArrayList.add(subjectModelList.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            SubjectAdapter subjectAdapter = new SubjectAdapter(subjectModelArrayList, SubjectListActivity.this);
            recyclerView.setAdapter(subjectAdapter);

        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new SubjectListActivity.LoadDataTask().execute();
    }
}