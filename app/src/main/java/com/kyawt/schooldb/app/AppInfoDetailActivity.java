package com.kyawt.schooldb.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.bottomnav.MyAccountActivity;

public class AppInfoDetailActivity extends AppCompatActivity {

    int admin_id;
    String admin_email, admin_password,admin_username, new_app_name, new_app_name_update="";
    EditText etAppNameDetail;
    Button btnUpdate, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info_detail);
        etAppNameDetail = (EditText) findViewById(R.id.etAppNameDetail);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        //        ----------- pass data by intent start------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            admin_id = data.getInt("key_for_admin_id");
            admin_email = data.getString("key_for_email");
            admin_password = data.getString("key_for_password");
            admin_username = data.getString("key_for_username");
        }
//        ----------- pass data by intent end----------------
        Buttons();
    }

    private void Buttons(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppInfoDetailActivity.this, MyAccountActivity.class);
                intent.putExtra("key_for_admin_id", admin_id);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
            }
        });

//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (inputText()){
//                    new_app_name_update = etAppNameDetail.getText().toString().trim();
//
//                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
//                    AppModel appModel = new AppModel(new_app_name_update);
//                    appDatabaseUtility.updateAppNameTask(new_app_name_update,);
//
//                    Intent intent = new Intent(AppInfoDetailActivity.this, MyAccountActivity.class);
//                    intent.putExtra("key_for_admin_id",admin_id);
//                    intent.putExtra("key_for_email",admin_email);
//                    intent.putExtra("key_for_password",admin_password );
//                    intent.putExtra("key_for_username", admin_username);
//                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(),"Successfully Changed!", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    private boolean inputText(){
        new_app_name = etAppNameDetail.getText().toString().trim();
        if (new_app_name.isEmpty()){
            etAppNameDetail.setError("New App Name is required");
            return false;
        }
        return true;
    }
}