package com.kyawt.schooldb.utility;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import com.kyawt.schooldb.helper.AppDatabase;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.ParentModel;
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.model.StudentModel;
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
                Toast.makeText(context,"Registered student "+register.father_name+" is added.", Toast.LENGTH_LONG).show();
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


    public void updateRegisterByID(final String register_date, final String student_name, final String student_nrc, final String student_dob, final String father_name, final String father_nrc, final String father_ph, final String address, final String email, final String course_name, final int course_fees, final int course_duration, final int register_id){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.registerDAO().updateRegisterByID(register_date,student_name,student_nrc,student_dob,father_name,father_nrc,father_ph,address,email,course_name,course_fees,course_duration,register_id);
                return null;
            }
        }.execute();
    }
//    ....................Update data task end......................

    //    ....................Delete data start......................

    public void deleteRegisterTask(final int register_id){
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.registerDAO().deleteRegisterByID(register_id);
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

    //    ....................Update data task start....................

    public void updateTeacherTask(final String teacher_name, final String teacher_gender, final String teacher_nrc, final String teacher_birthday, final String teacher_address, final String teacher_phone, final String teacher_email, final int teacher_id){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.teacherDAO().updateTeacherByID(teacher_name, teacher_gender,teacher_nrc,teacher_birthday,teacher_address,teacher_phone,teacher_email,teacher_id);
                return null;
            }
        }.execute();
    }
//    ....................Update data  end......................


//    ....................Delete data start......................

    public void deleteTeacherTask(final int teacher_id){
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.teacherDAO().deleteTeacher(teacher_id);
                return null;
            }
        }.execute();
    }
//    ....................Delete data task end......................

    //    =================================== Teacher END =============================================

//    ======================================= Student START ============================================
    //    ....................Insert task start........................

    public void insertStudentTask(final StudentModel student){

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.studentDAO().insertTask(student);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context,student.student_name+" is added.", Toast.LENGTH_LONG).show();
            }
        }.execute();

    }
//    ....................Insert task end..........................

    //    ....................Get data task start......................

    public List<StudentModel> getStudent(){
        List<StudentModel> studentModelList= appDatabase.studentDAO().getAllStudent();
        return studentModelList;
    }
//    ....................Get data task end......................

    //    ....................Update data for course task start....................

    public void updateStudentTask(final String student_name, final String student_gender, final String student_nrc, final String student_birthday, final String student_address, final String student_phone, final String student_email, final int student_id){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.studentDAO().updateStudentByID(student_name, student_gender,student_nrc,student_birthday,student_address,student_phone,student_email,student_id);
                return null;
            }
        }.execute();
    }
//    ....................Update data  end......................


//    ....................Delete data start......................

    public void deleteStudentTask(final int student_id){
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.studentDAO().deleteStudentByID(student_id);
                return null;
            }
        }.execute();
    }
//    ....................Delete data task end......................
//    ======================================= Student END ===============================================



}
