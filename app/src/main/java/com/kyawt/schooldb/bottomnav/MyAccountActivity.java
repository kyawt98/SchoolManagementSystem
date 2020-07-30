package com.kyawt.schooldb.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.kyawt.schooldb.app.AddAppActivity;
import com.kyawt.schooldb.app.AppInfoDetailActivity;
import com.kyawt.schooldb.model.AdminModel;
import com.kyawt.schooldb.model.AppModel;
import com.kyawt.schooldb.model.StudentModel;
import com.kyawt.schooldb.student.StudentListActivity;
import com.kyawt.schooldb.student.adapter.StudentAdapter;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;

public class MyAccountActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    int admin_id;
    String admin_email = "", admin_password = "", admin_username;
    EditText et_username, et_password, et_email, et_app_name;
    Button btn_acc_edit, btn_app_edit, btn_password_change, btn_delete, btn_sign_out, btn_app_add;
    ProgressBar loading;
    List<AppModel> appModelList;
    List<AdminModel> adminModelList;
    ArrayList<AdminModel> adminModelArrayList;
    ArrayList<AppModel> appModelArrayList;
    Toolbar toolbar;

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
        btn_acc_edit = (Button) findViewById(R.id.btn_edit);
//        btn_app_edit = (Button) findViewById(R.id.btn_app_edit);
        btn_password_change = (Button) findViewById(R.id.btn_password_edit);
        btn_delete = (Button) findViewById(R.id.btnDelete);
        btn_app_add = (Button) findViewById(R.id.btn_app_add);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        loading = (ProgressBar) findViewById(R.id.Loading);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null) {
            admin_id = data.getInt("key_for_admin_id");
            admin_email = data.getString("key_for_email");
            admin_password = data.getString("key_for_password");
            admin_username = data.getString("key_for_username");
        }
//        ----------- pass data by intent end----------------
        Buttons();
        new LoadDataTask().execute();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(MyAccountActivity.this, HomeActivity.class);
                intent.putExtra("key_for_admin_id", admin_id);
                intent.putExtra("key_for_email", admin_email);
                intent.putExtra("key_for_password", admin_password);
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
                break;

            case R.id.myaccount:
                break;
        }
        return false;
    }

    private void Buttons() {
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, MainActivity.class);
                loading.setVisibility(View.VISIBLE);
                startActivity(intent);
            }
        });

        btn_acc_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, MyAccountDetailActivity.class);
                intent.putExtra("key_for_admin_id", admin_id);
                intent.putExtra("key_for_email", admin_email);
                intent.putExtra("key_for_password", admin_password);
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
            }
        });

        btn_password_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, ChangePasswordActivity.class);
                intent.putExtra("key_for_admin_id", admin_id);
                intent.putExtra("key_for_email", admin_email);
                intent.putExtra("key_for_password", admin_password);
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
            }
        });

//        btn_app_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MyAccountActivity.this, AppInfoDetailActivity.class);
//                intent.putExtra("key_for_admin_id", admin_id);
//                intent.putExtra("key_for_email",admin_email);
//                intent.putExtra("key_for_password",admin_password );
//                intent.putExtra("key_for_username", admin_username);
//                startActivity(intent);
//            }
//        });

        btn_app_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, AddAppActivity.class);
                intent.putExtra("key_for_admin_id", admin_id);
                intent.putExtra("key_for_email", admin_email);
                intent.putExtra("key_for_password", admin_password);
                intent.putExtra("key_for_username", admin_username);
                startActivity(intent);
            }
        });
    }

    class LoadDataTask extends AsyncTask<Void, Void, Void> {

        AppDatabaseUtility appDatabaseUtility;
        List<StudentModel> studentModelList;

        @Override
        protected void onPreExecute() {
            appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            appModelList = appDatabaseUtility.getApp();
            adminModelList = appDatabaseUtility.getAdmin();
            appModelArrayList = new ArrayList<>();
            adminModelArrayList = new ArrayList<>();

            for (int i = 0; i < appModelList.size(); i++) {
                appModelArrayList.add(appModelList.get(i));
            }


            for (int i = 0; i < adminModelList.size(); i++) {
                adminModelArrayList.add(adminModelList.get(i));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            et_app_name.setText(appModelArrayList.get(appModelArrayList.size() - 1).app_name);
            et_username.setText(adminModelArrayList.get(adminModelArrayList.size() - 1).admin_user_name);
            et_email.setText(adminModelArrayList.get(adminModelArrayList.size() - 1).admin_email);
            et_password.setText(adminModelArrayList.get(adminModelArrayList.size() -1).admin_password);
            toolbar.setTitle(appModelArrayList.get(appModelArrayList.size() - 1).app_name);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}