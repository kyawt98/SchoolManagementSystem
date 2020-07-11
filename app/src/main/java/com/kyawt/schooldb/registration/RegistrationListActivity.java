package com.kyawt.schooldb.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kyawt.schooldb.R;
import com.kyawt.schooldb.bottomnav.HomeActivity;

public class RegistrationListActivity extends AppCompatActivity {

    CardView btn_back;
    FloatingActionButton fabAddNewRegistration;
    RecyclerView recycler_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_list);

        btn_back = (CardView) findViewById(R.id.back);
        fabAddNewRegistration = (FloatingActionButton) findViewById(R.id.fabAddNewRegistration);

        action();

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
}