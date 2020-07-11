package com.kyawt.schooldb.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kyawt.schooldb.R;

public class AddRegistrationActivity extends AppCompatActivity {

    Button btn_cancel, btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_registration);

        btn_cancel = (Button) findViewById(R.id.btnCancel);
        btn_save = (Button) findViewById(R.id.btnAdd);

        Buttons();
    }

    private void Buttons(){
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddRegistrationActivity.this, RegistrationListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}