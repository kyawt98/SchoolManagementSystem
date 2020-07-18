package com.kyawt.schooldb.bottomnav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.parent.ParentListActivity;
import com.kyawt.schooldb.registration.RegistrationListActivity;
import com.kyawt.schooldb.student.StudentListActivity;
import com.kyawt.schooldb.subject.SubjectListActivity;
import com.kyawt.schooldb.teacher.TeacherListActivity;

public class HomeActivity extends AppCompatActivity {

    CardView card_register, card_student, card_teacher, card_class, card_subject, card_parent;
    Button btn_home, btn_myaccount, btn_setting;
    TextView txt_app_name;
    String app_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        card_register = (CardView) findViewById(R.id.cardRegister);
        card_student = (CardView) findViewById(R.id.cardStudent);
        card_teacher = (CardView) findViewById(R.id.cardTeacher);
        card_class = (CardView) findViewById(R.id.cardCourse);
        card_subject = (CardView) findViewById(R.id.cardSubject);
        card_parent = (CardView) findViewById(R.id.cardParent);
        btn_home = (Button) findViewById(R.id.home);
        btn_myaccount = (Button) findViewById(R.id.myaccount);
        btn_setting = (Button) findViewById(R.id.setting);
        txt_app_name = (TextView) findViewById(R.id.txt_app_name);

        //        ----------- pass data from start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            app_name = data.getString("key_for_app_name");
            Log.d("App name", app_name);
        }
//        ----------- pass data end----------------

        //        ------------set data to UI start-------------------------------------

        txt_app_name.setText(app_name+"");

//        ------------set data to UI end ---------------------------------------

        CardMenus();
        nav_bottoms();
    }

    private void CardMenus(){
        card_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegistrationListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        card_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CourseListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        card_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SubjectListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        card_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TeacherListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        card_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegistrationListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        card_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StudentListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        card_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ParentListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void nav_bottoms(){
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MyAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}