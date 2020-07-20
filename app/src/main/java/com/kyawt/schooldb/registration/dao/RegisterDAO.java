package com.kyawt.schooldb.registration.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kyawt.schooldb.model.RegisterModel;

import java.util.List;

@Dao
public interface RegisterDAO {

    @Insert
    long insertTask(RegisterModel registerModel);

    @Update
    void updateTask(RegisterModel registerModel);

    @Delete
    void deleteTask(RegisterModel registerModel);

    @Query("SELECT * FROM registermodel ORDER BY register_id asc")
    List<RegisterModel> getAllRegisters();

    @Query("UPDATE registermodel SET Register_date = :register_date, Student_name = :student_name, Student_nrc = :student_nrc, Student_birthday = :student_dob, Father_name = :father_name, Father_nrc = :father_nrc, Father_phone = :father_ph, Student_address = :address, Student_email = :email, Course_name = :course_name, Course_fees = :course_fees, Course_duration = :course_duration   WHERE register_id= :register_id")
    void updateRegisterByID(String register_date, String student_name, String student_nrc, String student_dob, String father_name, String father_nrc, String father_ph, String address, String email, String course_name, int course_fees, int course_duration, int register_id);

    @Query("DELETE FROM registermodel WHERE register_id= :register_id")
    void deleteRegisterByID(int register_id);

}
