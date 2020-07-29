package com.kyawt.schooldb.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kyawt.schooldb.MainActivity;
import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.parent.ParentListActivity;
import com.kyawt.schooldb.registration.RegistrationListActivity;
import com.kyawt.schooldb.student.StudentListActivity;
import com.kyawt.schooldb.subject.SubjectListActivity;
import com.kyawt.schooldb.teacher.TeacherListActivity;
import com.kyawt.schooldb.timetable.TimetableListActivity;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    CardView card_register, card_student, card_teacher, card_class, card_subject, card_timetable;
    Button btn_home, btn_myaccount, btn_setting;
    String app_name="",admin_email="",admin_password="",admin_username="";
    int admin_id;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        card_register = (CardView) findViewById(R.id.cardRegister);
        card_student = (CardView) findViewById(R.id.cardStudent);
        card_teacher = (CardView) findViewById(R.id.cardTeacher);
        card_class = (CardView) findViewById(R.id.cardCourse);
        card_subject = (CardView) findViewById(R.id.cardSubject);
        card_timetable = (CardView) findViewById(R.id.cardTimetable);
//        txt_app_name = (TextView) findViewById(R.id.txt_app_name);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navBotton);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            admin_id = data.getInt("key_for_admin_id");
            admin_email = data.getString("key_for_email");
            admin_password = data.getString("key_for_password");
            admin_username = data.getString("key_for_username");
        }

//        ----------- pass data  by intent end----------------


        CardMenus();

//        nav_bottoms();
    }

    private void CardMenus(){
        card_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegistrationListActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });

        card_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CourseListActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });

        card_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SubjectListActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });

        card_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TeacherListActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });

        card_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegistrationListActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });

        card_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StudentListActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });

        card_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TimetableListActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                break;

            case R.id.myaccount:
                Intent intent2 = new Intent(HomeActivity.this, MyAccountActivity.class);
                intent2.putExtra("key_for_admin_id", admin_id);
                intent2.putExtra("key_for_email",admin_email);
                intent2.putExtra("key_for_password",admin_password );
                intent2.putExtra("key_for_username", admin_username);
                startActivity(intent2);
                break;
        }
        return false;
    }

}