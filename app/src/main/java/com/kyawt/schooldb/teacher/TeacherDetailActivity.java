package com.kyawt.schooldb.teacher;

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
import com.kyawt.schooldb.course.CourseDetailActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

public class TeacherDetailActivity extends AppCompatActivity {

    Button btn_update, btn_delete, btn_cancel;
    EditText et_teacher_name, et_teacher_ph, et_teacher_email, et_teacher_dob, et_teacher_address, et_teacher_nrc;
    RadioButton rdbtn_male, rdbtn_female;
    int teacher_id=0;
    String teacher_name="", teacher_ph="", teacher_email="", teacher_dob="", teacher_address="", teacher_nrc="", teacher_gender="";
    String teacher_name_to_update="", teacher_ph_to_update="",teacher_email_to_update="",teacher_dob_to_update="",teacher_address_to_update="", teacher_nrc_to_update="",teacher_gender_to_update="";
    String teacher_name_to_delete="", teacher_ph_to_delete="",teacher_email_to_delete="",teacher_dob_to_delete="",teacher_address_to_delete="", teacher_nrc_to_delete="",teacher_gender_to_delete="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);

        et_teacher_name = (EditText) findViewById(R.id.etTeacherNameDetail);
        et_teacher_ph = (EditText) findViewById(R.id.etTeacherPhoneDetail);
        et_teacher_email = (EditText) findViewById(R.id.etTeacherEmailDetail);
        et_teacher_dob = (EditText) findViewById(R.id.etTeacherBirthdayDetail);
        et_teacher_address = (EditText) findViewById(R.id.etTeacherAddressDetail);
        et_teacher_nrc = (EditText) findViewById(R.id.etTeacherNrcDetail);
        rdbtn_female = (RadioButton) findViewById(R.id.rdbtn_teacher_female_detail);
        rdbtn_male = (RadioButton) findViewById(R.id.rdbtn_teacher_male_detail);

        btn_update = (Button) findViewById(R.id.btnTeacherUpdate);
        btn_delete = (Button) findViewById(R.id.btnDelete);
        btn_cancel = (Button) findViewById(R.id.btnCancel);

        //        ----------- pass data from course adapter by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            teacher_id = data.getInt("key_for_teacher_id");
            teacher_name = data.getString("key_for_teacher_name");
            teacher_ph = data.getString("key_for_teacher_ph");
            teacher_email = data.getString("key_for_teacher_email");
            teacher_dob = data.getString("key_for_teacher_dob");
            teacher_address = data.getString("key_for_teacher_address");
            teacher_nrc = data.getString("key_for_teacher_nrc");
            teacher_gender = data.getString("key_for_teacher_gender");
        }
//        ----------- pass data from student course by intent end----------------

        //        ------------set data to UI start-------------------------------------
        et_teacher_name.setText(teacher_name+"");
        et_teacher_ph.setText(teacher_ph+"");
        et_teacher_email.setText(teacher_email+"");
        et_teacher_dob.setText(teacher_dob+"");
        et_teacher_address.setText(teacher_address+"");
        et_teacher_nrc.setText(teacher_nrc+"");
        if (teacher_gender.trim().toLowerCase().equalsIgnoreCase("male")){
            rdbtn_male.setChecked(true);
            rdbtn_female.setChecked(false);
        }else if (teacher_gender.trim().toLowerCase().equalsIgnoreCase("female")){
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
                if (et_teacher_name.getText().toString().trim().isEmpty()
                        || et_teacher_email.getText().toString().trim().isEmpty()
                        || et_teacher_ph.getText().toString().trim().isEmpty()
                        || et_teacher_address.getText().toString().trim().isEmpty()
                        || et_teacher_nrc.getText().toString().trim().isEmpty()
                        || et_teacher_dob.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Required data", Toast.LENGTH_LONG).show();
                }else {
                    teacher_name_to_update = et_teacher_name.getText().toString().trim();
                    teacher_ph_to_update = et_teacher_ph.getText().toString().trim();
                    teacher_email_to_update = et_teacher_email.getText().toString().trim();
                    teacher_dob_to_update = et_teacher_dob.getText().toString().trim();
                    teacher_address_to_update = et_teacher_address.getText().toString().trim();
                    teacher_nrc_to_update = et_teacher_nrc.getText().toString().trim();

                    if (rdbtn_male.isChecked()){
                        teacher_gender_to_update = "Male";
                    }else if (rdbtn_female.isChecked()){
                        teacher_gender_to_update = "Female";
                    }

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    TeacherModel teacherModel = new TeacherModel(teacher_name_to_update,teacher_gender_to_update,teacher_nrc_to_update,teacher_dob_to_update, teacher_address_to_update, teacher_ph_to_update, teacher_email_to_update);
                    appDatabaseUtility.updateTeacherTask(teacher_name_to_update,teacher_gender_to_update,teacher_nrc_to_update,teacher_dob_to_update, teacher_address_to_update, teacher_ph_to_update, teacher_email_to_update, teacher_id);

                    Intent intent = new Intent(TeacherDetailActivity.this, TeacherListActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Updated Teacher Info!", Toast.LENGTH_LONG).show();
                }
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               teacher_name_to_delete = et_teacher_name.getText().toString().trim();
                teacher_nrc_to_delete = et_teacher_nrc.getText().toString().trim();
                teacher_dob_to_delete = et_teacher_dob.getText().toString().trim();
                teacher_address_to_delete = et_teacher_address.getText().toString().trim();
                teacher_ph_to_delete = et_teacher_ph.getText().toString().trim();
                teacher_email_to_delete = et_teacher_email.getText().toString().trim();

                if (rdbtn_female.isChecked()){
                    teacher_gender_to_delete = "Female";
                }else if (rdbtn_male.isChecked()){
                    teacher_gender_to_delete = "Male";
                }

                TeacherModel teacherModel = new TeacherModel(teacher_name_to_delete,teacher_nrc_to_delete,teacher_dob_to_delete,teacher_address_to_delete,teacher_ph_to_delete,teacher_email_to_delete,teacher_gender_to_delete);
                delete_dialog(teacher_id);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherDetailActivity.this, TeacherListActivity.class);
                startActivity(intent);
            }
        });
    }

    //    ............... Delete data start ................

    public void delete_dialog(int teacher_id){
        final int teacher_about_to_delete = teacher_id;

        AlertDialog.Builder builder= new AlertDialog.Builder(TeacherDetailActivity.this);
        builder.setTitle("WARNING");
        builder.setMessage("Are you sure to delete?");
        builder.setIcon(R.drawable.ic__remove);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                appDatabaseUtility.deleteTeacherTask(teacher_about_to_delete);
                Toast.makeText(getApplicationContext(), teacher_name+" is deleted.", Toast.LENGTH_LONG).show();
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