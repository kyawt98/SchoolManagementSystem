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

    @Query("SELECT * FROM teachermodel ORDER BY teacher_id asc")
    List<TeacherModel> getAllTeachers();
}
