package com.kyawt.schooldb.parent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kyawt.schooldb.R;
import com.kyawt.schooldb.bottomnav.HomeActivity;
import com.kyawt.schooldb.course.AddCourseActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.model.ParentModel;
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.parent.adapter.ParentAdapter;
import com.kyawt.schooldb.registration.RegistrationListActivity;
import com.kyawt.schooldb.registration.adapter.RegisterAdapter;
import com.kyawt.schooldb.teacher.adapter.TeacherAdapter;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;

public class ParentListActivity extends AppCompatActivity {

    CardView btn_back;
    FloatingActionButton fabAddNewParent;
    EditText et_search;

    private static RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private static  RecyclerView recyclerView;
    private ParentAdapter parentAdapter;

    ArrayList<ParentModel> parentModelArrayList,parentSearchList;
    ArrayList<RegisterModel> registerModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_list);

        btn_back = (CardView) findViewById(R.id.back);
        fabAddNewParent = (FloatingActionButton) findViewById(R.id.fabAddNewParent);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerParent);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        //        ----------- pass data by intent start-------------
//        Bundle data = getIntent().getExtras();
//        if (data != null){
//            register_date = data.getString("key_for_register_date");
//            stu_name = data.getString("key_for_student_name");
//            stu_nrc = data.getString("key_for_student_nrc");
//            stu_bd = data.getString("key_for_student_dob");
//            father_name = data.getString("key_for_father_name");
//            father_nrc = data.getString("key_for_father_nrc");
//            father_ph = data.getString("key_for_father_ph");
//            address = data.getString("key_for_address");
//            email = data.getString("key_for_email");
//            course_name = data.getString("key_for_course_name");
//            course_fees = data.getInt("key_for_course_fees");
//            course_duration = data.getInt("key_for_course_duration");
//            register_id = data.getInt("register_id");
//
//        }
////        ----------- pass data by intent end----------------
//
//        //        ------------set data to UI start-------------------------------------
//
//        et_register_date.setText(register_date+"");
//        et_stu_name.setText(stu_name+"");
//        et_stu_nrc.setText(stu_nrc+"");
//        et_stu_bd.setText(stu_bd+"");
//        et_father_name.setText(father_name+"");
//        et_father_nrc.setText(father_nrc+"");
//        et_father_ph.setText(father_ph+"");
//        et_address.setText(address+"");
//        et_email.setText(email+"");
//        et_course_fees.setText(course_fees+"");
//        et_course_duration.setText(course_duration+"");

//        ------------set data to UI end ---------------------------------------

        action();
    }

    private void action() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentListActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fabAddNewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentListActivity.this, AddParentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    class LoadDataTask extends AsyncTask<Void,Void,Void> {

        AppDatabaseUtility appDatabaseUtility;
        List<ParentModel> parentModelList;
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
//            registerSearchList = new ArrayList<>();

            for (int i=0; i<registerModelList.size(); i++){
                registerModelArrayList.add(registerModelList.get(i));
//                registerSearchList.add(registerModelList.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            parentAdapter= new ParentAdapter(parentModelArrayList, ParentListActivity.this);
            recyclerView.setAdapter(parentAdapter);

        }

    }
}