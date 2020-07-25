package com.kyawt.schooldb.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TimetableModel {
    @PrimaryKey(autoGenerate = true)
    public int timetable_id;

    @ColumnInfo(name = "Course_name")
    public String course_name;

    @ColumnInfo(name = "Day")
    public String day;

    @ColumnInfo(name = "Subject_name")
    public String subject_name;

    @ColumnInfo(name = "Start_time")
    public String start_time;

    @ColumnInfo(name = "Finish_time")
    public String finish_time;

    @ColumnInfo(name = "Teacher_name")
    public String teacher_name;

    public TimetableModel(String course_name, String day, String subject_name, String start_time, String finish_time, String teacher_name) {
        this.course_name = course_name;
        this.day = day;
        this.subject_name = subject_name;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.teacher_name = teacher_name;
    }
}
