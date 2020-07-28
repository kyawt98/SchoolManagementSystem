package com.kyawt.schooldb.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kyawt.schooldb.MainActivity;
import com.kyawt.schooldb.R;

public class MyAccountActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;
    String admin_email="", admin_password="", admin_username;
    EditText et_username, et_password, et_email,et_app_name;
    Button btn_edit,btn_sign_out;
    ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navBotton);

        et_email = (EditText) findViewById(R.id.et_email);
        et_username = (EditText) findViewById(R.id.et_user_name);
        et_password = (EditText) findViewById(R.id.et_password);
        et_app_name = (EditText) findViewById(R.id.et_app_name);
        btn_sign_out = (Button) findViewById(R.id.btn_log_out);
        loading = (ProgressBar) findViewById(R.id.Loading);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        //        ----------- pass data from course adapter by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            admin_email = data.getString("key_for_email");
            admin_password = data.getString("key_for_password");
            admin_username = data.getString("key_for_username");
        }
//        ----------- pass data from student course by intent end----------------
        et_username.setText(admin_username);
        et_email.setText(admin_email);
        et_password.setText(admin_password);
        et_app_name.setText("SchoolDB");
        Buttons();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(MyAccountActivity.this, HomeActivity.class);
                intent.putExtra("key_for_email",admin_email);
                intent.putExtra("key_for_password",admin_password );
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                break;

            case R.id.myaccount:
                break;
        }
        return false;
    }

    private void Buttons(){
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, MainActivity.class);
                startActivity(intent);
                loading.setVisibility(View.VISIBLE);
            }
        });
    }
}