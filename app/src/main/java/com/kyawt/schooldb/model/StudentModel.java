package com.kyawt.schooldb.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class StudentModel {
    @PrimaryKey(autoGenerate = true)
    public int student_id;

    @ColumnInfo(name = "Student_name")
    public String student_name;

    @ColumnInfo(name = "Student_gender")
    public String student_gender;

    @ColumnInfo(name = "Student_nrc")
    public String student_nrc;

    @ColumnInfo(name = "Student_dob")
    public String student_dob;

    @ColumnInfo(name = "Student_address")
    public String student_address;

    @ColumnInfo(name = "Student_phone")
    public String student_phone;

    @ColumnInfo(name = "Student_email")
    public String student_email;

    public StudentModel(String student_name, String student_gender, String student_nrc, String student_dob, String student_address, String student_phone, String student_email) {
        this.student_name = student_name;
        this.student_gender = student_gender;
        this.student_nrc = student_nrc;
        this.student_dob = student_dob;
        this.student_address = student_address;
        this.student_phone = student_phone;
        this.student_email = student_email;
    }
}
