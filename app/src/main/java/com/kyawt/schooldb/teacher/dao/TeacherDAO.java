package com.kyawt.schooldb.teacher.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kyawt.schooldb.model.TeacherModel;

import java.util.List;

@Dao
public interface TeacherDAO {
    @Insert
    long insertTask(TeacherModel teacherModel);

    @Update
    void updateTask(TeacherModel teacherModel);

    @Delete
    void deleteTask(TeacherModel teacherModel);

    @Query("DELETE FROM teachermodel WHERE teacher_id = :tId")
    void deleteTeacher(int tId);

    @Query("UPDATE teachermodel SET Teacher_name = :teacher_name, Teacher_gender = :teacher_gender, Teacher_nrc = :teacher_nrc, Teacher_birthday = :teacher_birthday, Teacher_address = :teacher_address, Teacher_phone = :teacher_phone, Teacher_email = :teacher_email WHERE teacher_id = :teacher_id")
    void updateTeacherByID(String teacher_name, String teacher_gender, String teacher_nrc, String teacher_birthday, String teacher_address, String teacher_phone, String teacher_email, int teacher_id);

    @Query("SELECT * FROM teachermodel ORDER BY teacher_id asc")
    List<TeacherModel> getAllTeachers();
}
