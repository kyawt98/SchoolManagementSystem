package com.kyawt.schooldb.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyawt.schooldb.MainActivity;
import com.kyawt.schooldb.R;
import com.kyawt.schooldb.bottomnav.HomeActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.course.adapter.CourseAdapter;
import com.kyawt.schooldb.model.AdminModel;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button btnSignUpLink, btnSignIn;
    String email, password;
    String a_email = "", a_password = "",a_username="";
    int a_id;
    EditText et_email, et_password;
    ArrayList<AdminModel> adminModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSignUpLink = (Button) findViewById(R.id.btnSignUpLink);
        et_email = (EditText) findViewById(R.id.etUserName);
        et_password = (EditText) findViewById(R.id.etPassword);
        btnSignIn = (Button) findViewById(R.id.btnSigIn);
        Buttons();

        //        ----------- pass data from course adapter by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null) {
            email = data.getString("key_for_email");
            password = data.getString("key_for_password");
        }
//        ----------- pass data from student course by intent end----------------

        et_email.setText(email);
        et_password.setText(password);

        new LoadDataTask().execute();

    }

    private void Buttons() {
        btnSignUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    class LoadDataTask extends AsyncTask<Void, Void, Void> {

        AppDatabaseUtility appDatabaseUtility;
        List<AdminModel> adminModelList;

        @Override
        protected void onPreExecute() {
            appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            adminModelList = appDatabaseUtility.getAdmin();
            Log.d("admin",adminModelList.get(0).admin_email.toString());
            adminModelArrayList = new ArrayList<>();

            for (int i = 0; i < adminModelList.size(); i++) {
                adminModelArrayList.add(adminModelList.get(i));
//                a_email = adminModelArrayList.get(i).admin_email;
//                a_password = adminModelArrayList.get(i).admin_password;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            btnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=0;

                        a_email = adminModelArrayList.get(position).admin_email;
                        a_password = adminModelArrayList.get(position).admin_password;
                        a_username = adminModelArrayList.get(position).admin_user_name;
                        a_id = adminModelArrayList.get(position).admin_id;

                    if ((et_email.getText().toString().trim().equals(a_email) && (et_password.getText().toString().trim().equals(a_password)))) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("key_for_email",a_email);
                        intent.putExtra("key_for_admin_id", a_id);
                        intent.putExtra("key_for_password",a_password );
                        intent.putExtra("key_for_username",a_username );
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "Email or Password was wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new LoadDataTask().execute();
    }
}