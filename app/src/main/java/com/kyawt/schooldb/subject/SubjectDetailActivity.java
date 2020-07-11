package com.kyawt.schooldb.subject;

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
import com.kyawt.schooldb.course.CourseDetailActivity;
import com.kyawt.schooldb.course.CourseListActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.SubjectModel;
import com.kyawt.schooldb.utility.AppDatabaseUtility;

public class SubjectDetailActivity extends AppCompatActivity {

    Button btn_update, btn_delete, btn_cancel;
    EditText et_sub_code, et_sub_name;
    int sub_code;
    String sub_name="";
    String sub_code_to_update="", sub_name_to_update;
    String sub_code_to_delete="", sub_name_to_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);

        et_sub_code = (EditText) findViewById(R.id.etSubCodeDetail);
        et_sub_name = (EditText) findViewById(R.id.etSubNameDetail);
        btn_update = (Button) findViewById(R.id.btnUpdate);
        btn_delete = (Button) findViewById(R.id.btnDelete);
        btn_cancel = (Button) findViewById(R.id.btnCancel);

        //        ----------- pass data from subject adapter by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            sub_code = data.getInt("key_for_sub_code");
            sub_name = data.getString("key_for_sub_name");
        }
//        ----------- pass data from subject by intent end----------------

        //        ------------set data to UI start-------------------------------------

        et_sub_code.setText(sub_code+"");
        et_sub_name.setText(sub_name+"");

//        ------------set data to UI end ---------------------------------------
        Buttons();
    }

    public void Buttons(){
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_sub_code.getText().toString().trim().isEmpty()
                        || et_sub_name.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Required data", Toast.LENGTH_LONG).show();
                }else {
                    sub_code_to_update = et_sub_code.getText().toString().trim();
                    sub_name_to_update = et_sub_name.getText().toString().trim();

                    AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                    SubjectModel subjectModel = new SubjectModel(Integer.parseInt(sub_code_to_update), sub_name_to_update);
                    appDatabaseUtility.updateSubjectTask(subjectModel);

                    Intent intent = new Intent(SubjectDetailActivity.this, SubjectListActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Updated Subject Info!", Toast.LENGTH_LONG).show();
                }
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub_code_to_delete = et_sub_code.getText().toString().trim();
                sub_name_to_delete = et_sub_name.getText().toString().trim();

                SubjectModel subjectModel = new SubjectModel(Integer.parseInt(sub_code_to_delete), sub_name_to_delete);
                delete_dialog(subjectModel);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectDetailActivity.this, SubjectListActivity.class);
                startActivity(intent);
            }
        });
    }

    //    ............... Delete data start ................

    public void delete_dialog(final SubjectModel subject){
        final SubjectModel subject_about_to_delete = subject;

        AlertDialog.Builder builder= new AlertDialog.Builder(SubjectDetailActivity.this);
        builder.setTitle("WARNING");
        builder.setMessage("Are you sure to delete?");
        builder.setIcon(R.drawable.ic__remove);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppDatabaseUtility appDatabaseUtility = new AppDatabaseUtility(getApplicationContext());
                appDatabaseUtility.deleteSubjectTask(subject_about_to_delete);
                Toast.makeText(getApplicationContext(), subject.sub_name+" is deleted.", Toast.LENGTH_LONG).show();
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