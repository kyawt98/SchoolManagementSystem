package com.kyawt.schooldb.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.bottomnav.MyAccountActivity;
import com.kyawt.schooldb.model.AppModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

public class AddAppActivity extends AppCompatActivity {

    int admin_id;
    String admin_email, admin_password,admin_username, app_name;
    EditText etAppName;
    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_app);
        etAppName = (EditText) findViewById(R.id.etAppName);
        btnAdd =(Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);
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
                Intent intent = new Intent(AddAppActivity.this, MyAccountActivity.class);
                intent.putExtra("key_for_admin_id", admin_id);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAppName.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Required all Detail", Toast.LENGTH_LONG).show();
                }else {
                    app_name = etAppName.getText().toString().trim();

//                    ...............Toast roll no, name, contact no, gender

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    AppModel appModel = new AppModel(app_name);
                    appDatabaseUtility.insertAPPTask(appModel);

                    etAppName.setText("");

                }
            }
        });
    }
}