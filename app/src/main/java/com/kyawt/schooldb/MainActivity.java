package com.kyawt.schooldb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kyawt.schooldb.bottomnav.HomeActivity;
import com.kyawt.schooldb.bottomnav.MyAccountActivity;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navBotton);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.home:
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.myaccount:
                            Intent intent2 = new Intent(MainActivity.this, MyAccountActivity.class);
                            startActivity(intent2);
                            break;


                    }
                    return false;
                }
            };
}