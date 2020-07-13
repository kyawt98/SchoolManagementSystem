package com.kyawt.schooldb.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RegisterModel {

    @PrimaryKey(autoGenerate = true)
    public int register_id;

    @ColumnInfo(name = "Register_date")
    public String register_date;

    @ColumnInfo(name = "Student_name")
    public String student_name;

    @ColumnInfo(name = "Student_nrc")
    public String student_nrc;

    @ColumnInfo(name = "Student_birthday")
    public String student_bd;

    @ColumnInfo(name = "Father_name")
    public String father_name;

    @ColumnInfo(name = "Father_nrc")
    public String father_nrc;

    @ColumnInfo(name = "Father_phone")
    public String father_ph;

    @ColumnInfo(name = "Student_address")
    public String student_address;

    @ColumnInfo(name = "Student_email")
    public String student_email;

    @ColumnInfo(name = "Course_name")
    public String course_name;

    @ColumnInfo(name = "Course_fees")
    public int course_fees;

    @ColumnInfo(name = "Course_duration")
    public int course_duration;

    public RegisterModel(int register_id, String register_date, String student_name, String student_nrc, String student_bd, String father_name, String father_nrc, String father_ph, String student_address, String student_email, String course_name, int course_fees, int course_duration) {
        this.register_id = register_id;
        this.register_date = register_date;
        this.student_name = student_name;
        this.student_nrc = student_nrc;
        this.student_bd = student_bd;
        this.father_name = father_name;
        this.father_nrc = father_nrc;
        this.father_ph = father_ph;
        this.student_address = student_address;
        this.student_email = student_email;
        this.course_name = course_name;
        this.course_fees = course_fees;
        this.course_duration = course_duration;
    }
}
