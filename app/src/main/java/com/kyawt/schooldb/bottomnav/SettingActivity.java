package com.kyawt.schooldb.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.CourseDetailActivity;

import java.util.Set;

public class SettingActivity extends AppCompatActivity {
    Button btn_home, btn_myaccount, btn_setting, btn_done;
    EditText et_change_app_name;
    String app_name="";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_home = (Button) findViewById(R.id.home);
        btn_myaccount = (Button) findViewById(R.id.myaccount);
        btn_setting = (Button) findViewById(R.id.setting);
        et_change_app_name = (EditText) findViewById(R.id.etNewAppName);
        btn_done = (Button) findViewById(R.id.btnDone);

        app_name = et_change_app_name.getText().toString();

        nav_bottoms();
    }

    private void nav_bottoms(){
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MyAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SettingActivity.this, HomeActivity.class);
                intent.putExtra("key_for_app_name", app_name);
                startActivity(intent);
            }
        });
    }
}