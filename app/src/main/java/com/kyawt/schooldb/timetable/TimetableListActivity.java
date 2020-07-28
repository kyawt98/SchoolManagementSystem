package com.kyawt.schooldb.timetable;

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
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.model.TimetableModel;
import com.kyawt.schooldb.timetable.adapter.TimetableAdapter;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TimetableListActivity extends AppCompatActivity {

    CardView card_back;
    FloatingActionButton fabAddNewTimetable;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private TimetableAdapter timetableAdapter;
    String admin_email="",admin_password="",admin_username="";

    ArrayList<TimetableModel> timetableModelArrayList, registerSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_list);
        card_back = (CardView) findViewById(R.id.back);
        fabAddNewTimetable = (FloatingActionButton) findViewById(R.id.fabAddNewTimetable);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_timetable);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null) {
            admin_email = data.getString("key_for_email");
            admin_password = data.getString("key_for_password");
            admin_username = data.getString("key_for_username");
        }

//        ----------- pass data  by intent end----------------

        action();
        new LoadDataTask().execute();
    }


    private void action() {
        card_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimetableListActivity.this, HomeActivity.class);
                intent.putExtra("key_for_email", admin_email);
                intent.putExtra("key_for_password", admin_password);
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });

        fabAddNewTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimetableListActivity.this, AddTimetableActivity.class);
                intent.putExtra("key_for_email", admin_email);
                intent.putExtra("key_for_password", admin_password);
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });
    }

    class LoadDataTask extends AsyncTask<Void, Void, Void> {

        AppDatabaseUtility appDatabaseUtility;
        List<TimetableModel> timetableModelList;

        @Override
        protected void onPreExecute() {
            appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            timetableModelList = appDatabaseUtility.getTimetable();
            timetableModelArrayList = new ArrayList<>();

            for (int i = 0; i < timetableModelList.size(); i++) {
                timetableModelArrayList.add(timetableModelList.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            timetableAdapter = new TimetableAdapter(timetableModelArrayList, TimetableListActivity.this);
            recyclerView.setAdapter(timetableAdapter);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new LoadDataTask().execute();
    }
}