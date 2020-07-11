package com.kyawt.schooldb.course;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.student.StudentListActivity;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

public class CourseDetailActivity extends AppCompatActivity {

    Button btn_update, btn_delete, btn_cancel;
    EditText et_course_code, et_course_name;
    int course_code;
    String course_name="";
    String course_code_to_update="", course_name_to_update="";
    String course_code_to_delete="", course_name_to_delete="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        et_course_code = (EditText) findViewById(R.id.etClassCodeDetail);
        et_course_name = (EditText) findViewById(R.id.etClassNameDetail);
        btn_update = (Button) findViewById(R.id.btnUpdate);
        btn_delete = (Button) findViewById(R.id.btnDelete);
        btn_cancel = (Button) findViewById(R.id.btnCancel);

        //        ----------- pass data from course adapter by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            course_code = data.getInt("key_for_course_code");
            course_name = data.getString("key_for_course_name");
        }
//        ----------- pass data from student course by intent end----------------

        //        ------------set data to UI start-------------------------------------

        et_course_code.setText(course_code+"");
        et_course_name.setText(course_name+"");

//        ------------set data to UI end ---------------------------------------
        Buttons();

    }

    public void Buttons(){
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_course_code.getText().toString().trim().isEmpty()
                        || et_course_name.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Required data", Toast.LENGTH_LONG).show();
                }else {
                    course_code_to_update = et_course_code.getText().toString().trim();
                    course_name_to_update = et_course_name.getText().toString().trim();

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    CourseModel courseModel = new CourseModel(Integer.parseInt(course_code_to_update), course_name_to_update);
                    appDatabaseUtility.updateTask(courseModel);

                    Intent intent = new Intent(CourseDetailActivity.this, CourseListActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Updated Course Info!", Toast.LENGTH_LONG).show();
                }
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course_code_to_delete = et_course_code.getText().toString().trim();
                course_name_to_delete = et_course_name.getText().toString().trim();

                CourseModel courseModel = new CourseModel(Integer.parseInt(course_code_to_delete), course_name_to_delete);
                delete_dialog(courseModel);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailActivity.this, CourseListActivity.class);
                startActivity(intent);
            }
        });
    }

    //    ............... Delete data start ................

    public void delete_dialog(final CourseModel course){
        final CourseModel course_about_to_delete = course;

        AlertDialog.Builder builder= new AlertDialog.Builder(CourseDetailActivity.this);
        builder.setTitle("WARNING");
        builder.setMessage("Are you sure to delete?");
        builder.setIcon(R.drawable.ic__remove);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                appDatabaseUtility.deleteTask(course_about_to_delete);
                Toast.makeText(getApplicationContext(), course.class_name+" is deleted.", Toast.LENGTH_LONG).show();
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