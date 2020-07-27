package com.kyawt.schooldb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kyawt.schooldb.admin.LoginActivity;
import com.kyawt.schooldb.model.AdminModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

public class MainActivity extends AppCompatActivity {
    Button btnLoginLink, btnSigUp;
    EditText etUserName, etEmail, etPassword, etConfirmPassword;
    String username, email, password, confirm_password;
    ProgressBar Loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoginLink = (Button) findViewById(R.id.btnLoginLink);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        btnSigUp = (Button) findViewById(R.id.btnSigUp);
        Loading = (ProgressBar) findViewById(R.id.Loading);
        Buttons();
    }

    private void Buttons() {
        btnLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hasInputText()) {
                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    AdminModel adminModel = new AdminModel(username, email, password);
                    appDatabaseUtility.insertAdminTask(adminModel);

                    Loading.setVisibility(View.VISIBLE);

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.putExtra("key_for_email", email);
                    intent.putExtra("key_for_password", password);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean hasInputText() {

        username = etUserName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirm_password = etConfirmPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            etUserName.setError("User Name is required");
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            return false;
        }
        if (etPassword.getText().toString().length() < 6) {
            etPassword.setError("Password must be at least 6 characters long.");
            return false;
        }

        if (TextUtils.isEmpty(confirm_password)) {
            etConfirmPassword.setError("Confirm password is required");
            return false;
        }
        if (!etConfirmPassword.getText().toString().equals(etPassword.getText().toString())) {
            etConfirmPassword.setError("Password are not match");
            return false;
        }
        return true;
    }

}