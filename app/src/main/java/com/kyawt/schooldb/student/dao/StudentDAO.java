package com.kyawt.schooldb.student.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kyawt.schooldb.model.StudentModel;
import com.kyawt.schooldb.model.TeacherModel;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    long insertTask(StudentModel student);


    @Query("UPDATE studentmodel SET Student_name = :student_name, Student_gender = :student_gender, Student_nrc = :student_nrc, Student_dob = :student_dob, Student_address = :student_address, Student_phone = :student_phone, Student_email = :student_email WHERE Student_id = :student_id")
    void updateStudentByID(String student_name, String student_gender, String student_nrc, String student_dob, String student_address, String student_phone, String student_email, int student_id);

    @Query("SELECT * FROM studentmodel ORDER BY student_id asc")
    List<StudentModel> getAllStudent();

    @Query("DELETE FROM studentmodel WHERE student_id = :sId")
    void deleteStudentByID(int sId);

}
