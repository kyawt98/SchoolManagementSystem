package com.kyawt.schooldb.parent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.AddCourseActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

public class AddParentActivity extends AppCompatActivity {
    Button btn_cancel, btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parent);

        btn_cancel = (Button) findViewById(R.id.btnCancel);
        btn_save = (Button) findViewById(R.id.btnAdd);

        Buttons();
    }

    private void Buttons(){
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddParentActivity.this, ParentListActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (et_class_code.getText().toString().isEmpty()
//                        || et_class_name.getText().toString().isEmpty())
//                {
//                    Toast.makeText(getApplicationContext(),"Required all Detail", Toast.LENGTH_LONG).show();
//                }else {
//                    class_code = et_class_code.getText().toString().trim();
//                    class_name = et_class_name.getText().toString().trim();
//
////                    ...............Toast roll no, name, contact no, gender
//
//                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
//                    CourseModel courseModel = new CourseModel(Integer.parseInt(class_code), class_name);
//                    appDatabaseUtility.insertTask(courseModel);
//
//                    et_class_name.setText("");
//                    et_class_code.setText("");
//
//                }
//            }
//        });
    }
}