package com.kyawt.schooldb.timetable.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.model.TimetableModel;

import java.util.List;

@Dao
public interface TimetableDAO {
    @Insert
    long insertTask(TimetableModel timetableModel);

    @Query("SELECT * FROM timetablemodel ORDER BY timetable_id asc")
    List<TimetableModel> getTimetable();

    @Query("UPDATE timetablemodel SET Day = :day, Course_name = :course_name, Subject_name = :sub_name, Start_time = :start_time, Finish_time = :finish_time, Teacher_name = :teacher_name  WHERE timetable_id= :timetable_id")
    void updateTimetableByID(String day, String sub_name, String start_time, String finish_time, String teacher_name,String course_name,int timetable_id);

    @Query("DELETE FROM timetablemodel WHERE timetable_id= :timetable_id")
    void deleteTimetableByID(int timetable_id);

}
