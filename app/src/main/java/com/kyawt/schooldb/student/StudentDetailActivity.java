package com.kyawt.schooldb.student;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.model.StudentModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.teacher.TeacherDetailActivity;
import com.kyawt.schooldb.teacher.TeacherListActivity;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

public class StudentDetailActivity extends AppCompatActivity {

    Button btn_update, btn_delete, btn_cancel;
    EditText et_student_name, et_student_ph, et_student_email, et_student_dob, et_student_address, et_student_nrc;
    RadioButton rdbtn_male, rdbtn_female;
    int student_id=0;
    String student_name="", student_ph="", student_email="", student_dob="", student_address="", student_nrc="", student_gender="";
    String student_name_to_update="", student_ph_to_update="",student_email_to_update="",student_dob_to_update="",student_address_to_update="",student_nrc_to_update="",student_gender_to_update="";
    String student_name_to_delete="", student_ph_to_delete="",student_email_to_delete="",student_dob_to_delete="",student_address_to_delete="",student_nrc_to_delete="",student_gender_to_delete="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        et_student_name = (EditText) findViewById(R.id.et_stu_name_detail);
        et_student_ph = (EditText) findViewById(R.id.et_stu_ph_detail);
        et_student_email = (EditText) findViewById(R.id.et_stu_email_detail);
        et_student_dob = (EditText) findViewById(R.id.et_stu_dob_detail);
        et_student_address = (EditText) findViewById(R.id.et_stu_address_detail);
        et_student_nrc = (EditText) findViewById(R.id.et_stu_nrc_detail);
        btn_update = (Button) findViewById(R.id.btnStudentUpdate);
        btn_cancel = (Button) findViewById(R.id.btnStudentCancel);
        btn_delete = (Button) findViewById(R.id.btnStudentDelete);
        rdbtn_female = (RadioButton) findViewById(R.id.rdbtn_female);
        rdbtn_male = (RadioButton) findViewById(R.id.rdbtn_male);

        //        ----------- pass data from course adapter by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            student_id = data.getInt("key_for_student_id");
            student_name = data.getString("key_for_student_name");
            student_ph = data.getString("key_for_student_ph");
            student_email = data.getString("key_for_student_email");
            student_dob = data.getString("key_for_student_dob");
            student_address = data.getString("key_for_student_address");
            student_nrc = data.getString("key_for_student_nrc");
            student_gender = data.getString("key_for_student_gender");
        }
//        ----------- pass data from student course by intent end----------------

        //        ------------set data to UI start-------------------------------------
        et_student_name.setText(student_name+"");
        et_student_ph.setText(student_ph+"");
        et_student_email.setText(student_email+"");
        et_student_dob.setText(student_dob+"");
        et_student_address.setText(student_address+"");
        et_student_nrc.setText(student_nrc+"");
        if (student_gender.trim().toLowerCase().equalsIgnoreCase("male")){
            rdbtn_male.setChecked(true);
            rdbtn_female.setChecked(false);
        }else if (student_gender.trim().toLowerCase().equalsIgnoreCase("female")){
            rdbtn_female.setChecked(true);
            rdbtn_male.setChecked(false);
        }
//        ------------set data to UI end ---------------------------------------

        Buttons();
    }

    public void Buttons(){
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_student_name.getText().toString().trim().isEmpty()
                        || et_student_email.getText().toString().trim().isEmpty()
                        || et_student_ph.getText().toString().trim().isEmpty()
                        || et_student_address.getText().toString().trim().isEmpty()
                        || et_student_nrc.getText().toString().trim().isEmpty()
                        || et_student_dob.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Required data", Toast.LENGTH_LONG).show();
                }else {
                    student_name_to_update = et_student_name.getText().toString().trim();
                    student_ph_to_update = et_student_ph.getText().toString().trim();
                    student_email_to_update = et_student_email.getText().toString().trim();
                    student_dob_to_update = et_student_dob.getText().toString().trim();
                    student_address_to_update = et_student_address.getText().toString().trim();
                    student_nrc_to_update = et_student_nrc.getText().toString().trim();

                    if (rdbtn_male.isChecked()){
                        student_gender_to_update = "Male";
                    }else if (rdbtn_female.isChecked()){
                        student_gender_to_update = "Female";
                    }

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    StudentModel studentModel = new StudentModel(student_name_to_update,student_gender_to_update,student_nrc_to_update,student_dob_to_update, student_address_to_update, student_ph_to_update, student_email_to_update);
                    appDatabaseUtility.updateStudentTask(student_name_to_update,student_gender_to_update,student_nrc_to_update,student_dob_to_update,student_address_to_update, student_ph_to_update, student_email_to_update, student_id);

                    Intent intent = new Intent(StudentDetailActivity.this, StudentListActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Updated Student Info!", Toast.LENGTH_LONG).show();
                }
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_name_to_delete = et_student_name.getText().toString().trim();
                student_nrc_to_delete = et_student_nrc.getText().toString().trim();
                student_dob_to_delete = et_student_dob.getText().toString().trim();
                student_address_to_delete = et_student_address.getText().toString().trim();
                student_ph_to_delete = et_student_ph.getText().toString().trim();
                student_email_to_delete = et_student_email.getText().toString().trim();

                if (rdbtn_female.isChecked()){
                    student_gender_to_delete = "Female";
                }else if (rdbtn_male.isChecked()){
                    student_gender_to_delete = "Male";
                }

                StudentModel studentModel = new StudentModel(student_name_to_delete,student_nrc_to_delete,student_dob_to_delete,student_address_to_delete,student_ph_to_delete,student_email_to_delete,student_gender_to_delete);
                delete_dialog(student_id);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDetailActivity.this, StudentListActivity.class);
                startActivity(intent);
            }
        });
    }
    //    ............... Delete data start ................

    public void delete_dialog(int student_id){
        final int student_about_to_delete = student_id;

        AlertDialog.Builder builder= new AlertDialog.Builder(StudentDetailActivity.this);
        builder.setTitle("WARNING");
        builder.setMessage("Are you sure to delete?");
        builder.setIcon(R.drawable.ic__remove);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                appDatabaseUtility.deleteStudentTask(student_about_to_delete);
                Toast.makeText(getApplicationContext(), student_name+" is deleted.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

//    ............... Delete data end ................
}