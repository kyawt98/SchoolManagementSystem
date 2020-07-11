package com.kyawt.schooldb.utility;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import com.kyawt.schooldb.helper.AppDatabase;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.SubjectModel;

import java.util.List;

public class AppDatabaseUtility {

    private String DB_NAME ="school_db";
    private Context context;
    private AppDatabase appDatabase;

    public AppDatabaseUtility(Context context) {
        this.context = context;
        appDatabase = Room.databaseBuilder(context,AppDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
//        Toast.makeText(context,"Database is created...",Toast.LENGTH_LONG).show();
    }

    //    =================================== Course START ===========================================

    //    ....................Insert task for course start........................

    public void insertTask(final CourseModel course){

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.courseDAO().insertTask(course);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context,course.class_name+" is added.", Toast.LENGTH_LONG).show();
            }
        }.execute();

    }

//    ....................Insert task for course end..........................

    //    ....................Get data for course task start......................

    public List<CourseModel> getCourses(){
        List<CourseModel> courseModelList= appDatabase.courseDAO().getAllCourses();
        return courseModelList;
    }
//    ....................Get data for course task end......................

    //    ....................Update data for course task start....................

    public void updateTask(final CourseModel course){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.courseDAO().updateTask(course);
                return null;
            }
        }.execute();
    }
//    ....................Update data for course task end......................


//    ....................Delete data for course task start......................

    public void deleteTask(final CourseModel course){
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.courseDAO().deleteTask(course);
                return null;
            }
        }.execute();
    }
//    ....................Delete data task end......................

//    =================================== Course END ===========================================

//    =================================== Subject START ===========================================

    //    ....................Insert task start........................

    public void insertSubjectTask(final SubjectModel subject){

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.subjectDAO().insertTask(subject);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context,subject.sub_name+" is added.", Toast.LENGTH_LONG).show();
            }
        }.execute();

    }
//    ....................Insert task end..........................


    //    ....................Get data task start......................

    public List<SubjectModel> getSubject(){
        List<SubjectModel> subjectModelList= appDatabase.subjectDAO().getAllSubjects();
        return subjectModelList;
    }
//    ....................Get data task end......................

    //    ....................Update data task start....................

    public void updateSubjectTask(final SubjectModel subject){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.subjectDAO().updateTask(subject);
                return null;
            }
        }.execute();
    }
//    ....................Update data task end......................

    //    ....................Delete data for course task start......................

    public void deleteSubjectTask(final SubjectModel subject){
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.subjectDAO().deleteTask(subject);
                return null;
            }
        }.execute();
    }
//    ....................Delete data task end......................
//    =================================== Subject END =============================================


}
