package com.kyawt.schooldb.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.CourseDetailActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

import java.util.ArrayList;
import java.util.List;

public class RegisterDetailActivity extends AppCompatActivity {

    Button btn_delete, btn_update, btn_cancel;
    EditText et_register_date, et_stu_name, et_stu_nrc, et_stu_bd, et_father_name, et_father_nrc, et_father_ph, et_address, et_email, et_course_fees, et_course_duration;
    Spinner sp_course_name;

    int register_id=0;
    String course_id;
    String register_date="", stu_name="", stu_nrc, stu_bd="", father_name="", father_nrc="", father_ph="", address="", email="",course_name="";
    int course_fees=0, course_duration=0;
    String register_date_to_update="", stu_name_to_update="", stu_nrc_to_update, stu_bd_to_update="", father_name_to_update="", father_nrc_to_update="", father_ph_to_update="", address_to_update="", email_to_update="",course_name_to_update="",course_fees_to_update="", course_duration_to_update="";
    String register_date_to_delete="", stu_name_to_delete="", stu_nrc_to_delete, stu_bd_to_delete="", father_name_to_delete="", father_nrc_to_delete="", father_ph_to_delete="", address_to_delete="", email_to_delete="",course_name_to_delete="",course_fees_to_delete="", course_duration_to_delete="";

    ArrayList<String> courseNameList;
    ArrayAdapter<String> courseNameArrayAdapter;
    private AppDatabaseUtility appDatabaseUtility;
    List<CourseModel> courseModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_detail);

        btn_update = (Button) findViewById(R.id.btnUpdate);
        btn_delete = (Button) findViewById(R.id.btnDelete);
        btn_cancel = (Button) findViewById(R.id.btnCancel);

        et_register_date = (EditText) findViewById(R.id.etDateDetail);
        et_stu_name = (EditText) findViewById(R.id.etStudentNameDetail);
        et_stu_nrc = (EditText) findViewById(R.id.etStudentNrcDetail);
        et_stu_bd = (EditText) findViewById(R.id.etStudentBirthdayDetail);
        et_father_name = (EditText) findViewById(R.id.etParentNameDetail);
        et_father_nrc = (EditText) findViewById(R.id.etParentNrcDetail);
        et_father_ph = (EditText) findViewById(R.id.etParentPhoneDetail);
        et_address = (EditText) findViewById(R.id.etAddressDetail);
        et_email = (EditText) findViewById(R.id.etEmailDetail);
        et_course_fees = (EditText) findViewById(R.id.etClassFeesDetail);
        et_course_duration = (EditText) findViewById(R.id.etDurationDetail);
        sp_course_name = (Spinner) findViewById(R.id.spinCourseDetail);

        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            register_date = data.getString("key_for_register_date");
            stu_name = data.getString("key_for_student_name");
            stu_nrc = data.getString("key_for_student_nrc");
            stu_bd = data.getString("key_for_student_dob");
            father_name = data.getString("key_for_father_name");
            father_nrc = data.getString("key_for_father_nrc");
            father_ph = data.getString("key_for_father_ph");
            address = data.getString("key_for_address");
            email = data.getString("key_for_email");
            course_name = data.getString("key_for_course_name");
            course_fees = data.getInt("key_for_course_fees");
            course_duration = data.getInt("key_for_course_duration");
            register_id = data.getInt("register_id");

        }
//        ----------- pass data by intent end----------------

        //        ------------set data to UI start-------------------------------------

        et_register_date.setText(register_date+"");
        et_stu_name.setText(stu_name+"");
        et_stu_nrc.setText(stu_nrc+"");
        et_stu_bd.setText(stu_bd+"");
        et_father_name.setText(father_name+"");
        et_father_nrc.setText(father_nrc+"");
        et_father_ph.setText(father_ph+"");
        et_address.setText(address+"");
        et_email.setText(email+"");
        et_course_fees.setText(course_fees+"");
        et_course_duration.setText(course_duration+"");

//        ------------set data to UI end ---------------------------------------
        Buttons();
        new LoadDataTask().execute();

    }

    public void Buttons(){
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_register_date.getText().toString().trim().isEmpty()
                        || et_stu_name.getText().toString().trim().isEmpty()
                        || et_stu_nrc.getText().toString().trim().isEmpty()
                        || et_stu_bd.getText().toString().trim().isEmpty()
                        || et_father_name.getText().toString().trim().isEmpty()
                        || et_father_nrc.getText().toString().trim().isEmpty()
                        || et_father_ph.getText().toString().trim().isEmpty()
                        || et_address.getText().toString().trim().isEmpty()
                        || et_email.getText().toString().trim().isEmpty()
                        || et_course_fees.getText().toString().trim().isEmpty()
                        || et_course_duration.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Required data", Toast.LENGTH_LONG).show();
                }else {
                    register_date_to_update = et_register_date.getText().toString().trim();
                    stu_name_to_update = et_stu_name.getText().toString().trim();
                    stu_nrc_to_update = et_stu_nrc.getText().toString().trim();
                    stu_bd_to_update = et_stu_bd.getText().toString().trim();
                    father_name_to_update = et_father_name.getText().toString().trim();
                    father_nrc_to_update = et_father_nrc.getText().toString().trim();
                    father_ph_to_update = et_father_ph.getText().toString().trim();
                    address_to_update = et_address.getText().toString().trim();
                    email_to_update = et_email.getText().toString().trim();
                    course_fees_to_update = et_course_fees.getText().toString().trim();
                    course_duration_to_update = et_course_duration.getText().toString().trim();
                    course_name_to_update = sp_course_name.getSelectedItem().toString().trim();

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());

                    if (register_id != 0){
                        RegisterModel registerModel = new RegisterModel(register_date_to_update, stu_name_to_update,stu_nrc_to_update,stu_bd_to_update, father_name_to_update,father_nrc_to_update, father_ph_to_update,address_to_update,email_to_update,course_name_to_update,Integer.parseInt(course_fees_to_update) ,Integer.parseInt(course_duration_to_update));
                        appDatabaseUtility.updateRegisterByID(register_date_to_update,stu_name_to_update,stu_nrc_to_update,stu_bd_to_update, father_name_to_update,father_nrc_to_update, father_ph_to_update,address_to_update,email_to_update,course_name_to_update,Integer.parseInt(course_fees_to_update) ,Integer.parseInt(course_duration_to_update),register_id);

                        Intent intent = new Intent(RegisterDetailActivity.this, RegistrationListActivity .class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Updated Course Info!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register_date_to_delete = et_register_date.getText().toString().trim();
                stu_name_to_delete = et_stu_name.getText().toString().trim();
                stu_nrc_to_delete = et_stu_nrc.getText().toString().trim();
                stu_bd_to_delete = et_stu_bd.getText().toString().trim();
                father_name_to_delete = et_father_name.getText().toString().trim();
                father_nrc_to_delete = et_father_nrc.getText().toString().trim();
                father_ph_to_delete = et_father_ph.getText().toString().trim();
                address_to_delete = et_address.getText().toString().trim();
                email_to_delete = et_email.getText().toString().trim();
                course_fees_to_delete = et_course_fees.getText().toString().trim();
                course_duration_to_delete = et_course_duration.getText().toString().trim();
                course_name_to_delete = sp_course_name.getSelectedItem().toString().trim();

                if (register_id != 0){
                    RegisterModel registerModel = new RegisterModel(register_date_to_delete,stu_name_to_delete,stu_nrc_to_delete,stu_bd_to_delete,father_name_to_delete,father_nrc_to_delete,father_ph_to_delete, address_to_delete,email_to_delete, course_name_to_delete, Integer.parseInt(course_fees_to_delete), Integer.parseInt(course_duration_to_delete));
                    delete_dialog(register_id);
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDetailActivity.this, RegistrationListActivity.class);
                startActivity(intent);
            }
        });
    }

    class LoadDataTask extends AsyncTask<Void,Void,Void> {

        AppDatabaseUtility appDatabaseUtility;
        List<CourseModel> courseModelList;

        @Override
        protected void onPreExecute() {
            appDatabaseUtility= new AppDatabaseUtility(getApplicationContext());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            courseModelList = appDatabaseUtility.getCourses();
            courseNameList = new ArrayList<>();

            for (int i=0; i<courseModelList.size(); i++){
                courseNameList.add(courseModelList.get(i).class_name);

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            courseNameArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, courseNameList );
            sp_course_name.setAdapter(courseNameArrayAdapter);
        }

    }

    //    ............... Delete data start ................

    public void delete_dialog(final int register_id){
        final int  register_about_to_delete = register_id;

        AlertDialog.Builder builder= new AlertDialog.Builder(RegisterDetailActivity.this);
        builder.setTitle("WARNING");
        builder.setMessage("Are you sure to delete?");
        builder.setIcon(R.drawable.ic__remove);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                appDatabaseUtility.deleteRegisterTask(register_about_to_delete);
                Toast.makeText(getApplicationContext(), register_id+" is deleted.", Toast.LENGTH_LONG).show();
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