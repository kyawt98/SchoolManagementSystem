package com.kyawt.schooldb.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.model.StudentModel;
import com.kyawt.schooldb.registration.RegistrationListActivity;
import com.kyawt.schooldb.registration.adapter.RegisterAdapter;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    Button btn_cancel, btn_save;
    EditText et_stu_name, et_stu_nrc, et_stu_dob, et_stu_address, et_stu_ph, et_stu_email;
    RadioButton rdbtn_male, rdbtn_female;

    String stu_name = "", stu_nrc = "", stu_bd = "", address = "", email = "", phone = "", stu_gender="";

    private AppDatabaseUtility appDatabaseUtility;
    List<RegisterModel> registerModelList;
    ArrayList<RegisterModel> registerModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        btn_cancel = (Button) findViewById(R.id.btnCancel);
        btn_save = (Button) findViewById(R.id.btnAdd);
        et_stu_name = (EditText) findViewById(R.id.et_stu_name);
        et_stu_nrc = (EditText) findViewById(R.id.et_stu_nrc);
        et_stu_dob = (EditText) findViewById(R.id.et_stu_dob);
        et_stu_address = (EditText) findViewById(R.id.et_stu_address);
        et_stu_ph = (EditText) findViewById(R.id.et_stu_ph);
        et_stu_email = (EditText) findViewById(R.id.et_stu_email);
        rdbtn_female = (RadioButton) findViewById(R.id.rdbtn_female);
        rdbtn_male = (RadioButton) findViewById(R.id.rdbtn_male);

        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null) {

            stu_name = data.getString("key_for_student_name");
            stu_nrc = data.getString("key_for_student_nrc");
            stu_bd = data.getString("key_for_student_dob");
            address = data.getString("key_for_address");
            email = data.getString("key_for_email");
            phone = data.getString("key_for_father_ph");

        }
//        ----------- pass data by intent end----------------

        //        ------------set data to UI start-------------------------------------

        et_stu_name.setText(stu_name + "");
        et_stu_nrc.setText(stu_nrc + "");
        et_stu_dob.setText(stu_bd + "");
        et_stu_address.setText(address + "");
        et_stu_email.setText(email + "");
        et_stu_ph.setText(phone + "");

//        ------------set data to UI end ---------------------------------------

        action();
    }

    private void action() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddStudentActivity.this, StudentListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_stu_name.getText().toString().isEmpty()
                        || et_stu_nrc.getText().toString().isEmpty()
                        || et_stu_dob.getText().toString().isEmpty()
                        || et_stu_address.getText().toString().isEmpty()
                        || et_stu_ph.getText().toString().isEmpty()
                        || et_stu_email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Required all Detail", Toast.LENGTH_LONG).show();
                } else {
                    stu_name = et_stu_name.getText().toString().trim();
                    stu_nrc = et_stu_nrc.getText().toString().trim();
                    stu_bd = et_stu_dob.getText().toString().trim();
                    address = et_stu_address.getText().toString().trim();
                    email = et_stu_email.getText().toString().trim();
                    phone = et_stu_ph.getText().toString().trim();

                    if (rdbtn_male.isChecked()){
                       stu_gender ="Male";
                    }else if (rdbtn_female.isChecked()){
                        stu_gender="Female";
                    }

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    StudentModel studentModel = new StudentModel(stu_name,stu_gender,stu_nrc,stu_bd,address,phone,email);
                    appDatabaseUtility.insertStudentTask(studentModel);

                    et_stu_name.setText("");
                    et_stu_nrc.setText("");
                    et_stu_dob.setText("");
                    et_stu_address.setText("");
                    et_stu_ph.setText("");
                    et_stu_email.setText("");

                }
            }
        });
    }


}