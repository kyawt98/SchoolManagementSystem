package com.kyawt.schooldb.utility;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import com.kyawt.schooldb.helper.AppDatabase;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.ParentModel;
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.model.SubjectModel;
import com.kyawt.schooldb.model.TeacherModel;

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

    public List<CourseModel> getCourseID() {
        int c_id=0;
        List<CourseModel> courseModelList = appDatabase.courseDAO().getClassById( c_id);
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
//    =================================== Parent START =============================================
    //    ....................Insert task start........................

    public void insertParentTask(final ParentModel parent){

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.parentDAO().insertTask(parent);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context,parent.parent_name+" is added.", Toast.LENGTH_LONG).show();
            }
        }.execute();

    }
//    ....................Insert task end..........................
//    =================================== Parent END =============================================

//    =================================== Register START =============================================
    //    ....................Insert task start........................

    public void insertRegisterTask(final RegisterModel register){

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.registerDAO().insertTask(register);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context,"Registered student "+register.student_name+" is added.", Toast.LENGTH_LONG).show();
            }
        }.execute();

    }
//    ....................Insert task end..........................

    //    ....................Get data task start......................

    public List<RegisterModel> getRegister(){
        List<RegisterModel> registerModelList= appDatabase.registerDAO().getAllRegisters();
        return registerModelList;

    }
//    ....................Get data task end......................

    //    ....................Update data task start....................

    public void updateRegisterTask(final RegisterModel register){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.registerDAO().updateTask(register);
                return null;
            }
        }.execute();
    }
//    ....................Update data task end......................

    //    ....................Delete data start......................

    public void deleteRegisterTask(final RegisterModel register){
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.registerDAO().deleteTask(register);
                return null;
            }
        }.execute();
    }
//    ....................Delete data task end......................

    //    =================================== Register END =============================================

    //    =================================== Teacher START =============================================

    //    ....................Insert task start........................

    public void insertTeacherTask(final TeacherModel teacher){

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.teacherDAO().insertTask(teacher);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context,"Teacher Name "+teacher.teacher_name+" is added.", Toast.LENGTH_LONG).show();
            }
        }.execute();

    }
//    ....................Insert task end..........................

    //    ....................Get data task start......................

    public List<TeacherModel> getTeacher(){
        List<TeacherModel> teacherModelList= appDatabase.teacherDAO().getAllTeachers();
        return teacherModelList;
    }
//    ....................Get data task end......................

    //    ....................Update data for course task start....................

    public void updateTeacherTask(final TeacherModel teacher){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.teacherDAO().updateTask(teacher);
                return null;
            }
        }.execute();
    }
//    ....................Update data  end......................


//    ....................Delete data start......................

    public void deleteTeacherTask(final TeacherModel teacher){
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.teacherDAO().deleteTask(teacher);
                return null;
            }
        }.execute();
    }
//    ....................Delete data task end......................

    //    =================================== Teacher END =============================================



}
