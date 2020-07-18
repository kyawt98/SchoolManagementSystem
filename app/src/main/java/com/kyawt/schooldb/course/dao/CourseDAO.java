package com.kyawt.schooldb.course.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kyawt.schooldb.model.CourseModel;

import java.util.List;

@Dao
public interface CourseDAO {

    @Insert
    long insertTask(CourseModel courseModel);

    @Update
    void updateTask(CourseModel courseModel);

    @Delete
    void deleteTask(CourseModel courseModel);

    @Query("SELECT * from coursemodel ORDER BY class_code asc")
    List<CourseModel> getAllCourses();

    @Query("SELECT * FROM coursemodel WHERE class_code = :cId")
    List<CourseModel> getClassById(int cId);
}
