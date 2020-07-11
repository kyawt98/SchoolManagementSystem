package com.kyawt.schooldb.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.AddCourseActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.SubjectModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

public class AddSubjectActivity extends AppCompatActivity {
    Button btn_cancel, btn_save;
    EditText et_sub_code, et_sub_name;
    String sub_code, sub_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        btn_cancel = (Button) findViewById(R.id.btnCancel);
        btn_save = (Button) findViewById(R.id.btnAddSubject);
        et_sub_code = (EditText) findViewById(R.id.etSubjectCode);
        et_sub_name = (EditText) findViewById(R.id.etSubjectName);

        Buttons();
    }

    private void Buttons(){
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSubjectActivity.this, SubjectListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_sub_code.getText().toString().isEmpty()
                        || et_sub_name.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Required all Detail", Toast.LENGTH_LONG).show();
                }else {
                    sub_code = et_sub_code.getText().toString().trim();
                    sub_name = et_sub_name.getText().toString().trim();

//                    ...............Toast roll no, name, contact no, gender

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    SubjectModel subjectModel = new SubjectModel(Integer.parseInt(sub_code), sub_name);
                    appDatabaseUtility.insertSubjectTask(subjectModel);

                    et_sub_name.setText("");
                    et_sub_code.setText("");

                }
            }
        });
    }
}