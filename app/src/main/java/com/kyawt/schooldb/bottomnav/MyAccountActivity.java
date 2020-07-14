package com.kyawt.schooldb.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kyawt.schooldb.R;

public class MyAccountActivity extends AppCompatActivity {

    Button btn_home, btn_myaccount, btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        btn_home = (Button) findViewById(R.id.home);
        btn_myaccount = (Button) findViewById(R.id.myaccount);
        btn_setting = (Button) findViewById(R.id.setting);

        nav_bottoms();
    }

    private void nav_bottoms(){
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, MyAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}