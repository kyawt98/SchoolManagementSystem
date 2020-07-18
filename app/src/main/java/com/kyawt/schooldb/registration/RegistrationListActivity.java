package com.kyawt.schooldb.registration;

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
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kyawt.schooldb.R;
import com.kyawt.schooldb.bottomnav.HomeActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.course.adapter.CourseAdapter;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.registration.adapter.RegisterAdapter;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RegistrationListActivity extends AppCompatActivity {

    CardView btn_back;
    FloatingActionButton fabAddNewRegistration;

    private static RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private static  RecyclerView recyclerView;
    private RegisterAdapter registerAdapter;
    EditText et_search;

    ArrayList<RegisterModel> registerModelArrayList, registerSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_list);

        btn_back = (CardView) findViewById(R.id.back);
        fabAddNewRegistration = (FloatingActionButton) findViewById(R.id.fabAddNewRegistration);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_registration);
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


        action();
        new LoadDataTask().execute();
    }

    private void action(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationListActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fabAddNewRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationListActivity.this, AddRegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    class LoadDataTask extends AsyncTask<Void,Void,Void> {

        AppDatabaseUtility appDatabaseUtility;
        List<RegisterModel> registerModelList;

        @Override
        protected void onPreExecute() {
            appDatabaseUtility= new AppDatabaseUtility(getApplicationContext());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            registerModelList = appDatabaseUtility.getRegister();
            registerModelArrayList = new ArrayList<>();
            registerSearchList = new ArrayList<>();

            for (int i=0; i<registerModelList.size(); i++){
                registerModelArrayList.add(registerModelList.get(i));
                registerSearchList.add(registerModelList.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            registerAdapter= new RegisterAdapter(registerModelArrayList, RegistrationListActivity.this);
            recyclerView.setAdapter(registerAdapter);

        }

    }

    //........... Filter start...........................
    public void Search(String txtSearch){

        txtSearch = txtSearch.toLowerCase(Locale.getDefault());
        Log.d("Filter", txtSearch+"");
        registerModelArrayList.clear();
        if (txtSearch.length() == 0){
            registerModelArrayList.addAll(registerSearchList);
            Log.d("Load Data", "All");
        }else {
            Log.d("Load", "Filtered");

            for (RegisterModel registerModel:registerSearchList){
                if (registerModel.student_name.toLowerCase(Locale.getDefault()).contains(txtSearch)
                        || registerModel.register_date.toLowerCase(Locale.getDefault()).contains(txtSearch)
                        || registerModel.student_email.toLowerCase(Locale.getDefault()).contains(txtSearch)){
                    registerModelArrayList.add(registerModel);
                }
            }
        }

        registerAdapter.notifyDataSetChanged();
    }
    //........... Filter end...........................

    @Override
    protected void onRestart() {
        super.onRestart();
        new LoadDataTask().execute();
    }
}