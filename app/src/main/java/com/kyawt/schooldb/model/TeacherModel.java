package com.kyawt.schooldb.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TeacherModel {

    @PrimaryKey(autoGenerate = true)
    public int teacher_id;

    @ColumnInfo(name = "Teacher_name")
    public String teacher_name;

    @ColumnInfo(name = "Teacher_gender")
    public String teacher_gender;

    @ColumnInfo(name = "Teacher_nrc")
    public String teacher_nrc;

    @ColumnInfo(name = "Teacher_birthday")
    public String teacher_dob;

    @ColumnInfo(name = "Teacher_address")
    public String teacher_address;

    @ColumnInfo(name = "Teacher_phone")
    public String teacher_ph;

    @ColumnInfo(name = "Teacher_email")
    public String teacher_email;

    public TeacherModel(String teacher_name, String teacher_gender, String teacher_nrc, String teacher_dob, String teacher_address, String teacher_ph, String teacher_email) {
        this.teacher_name = teacher_name;
        this.teacher_gender = teacher_gender;
        this.teacher_nrc = teacher_nrc;
        this.teacher_dob = teacher_dob;
        this.teacher_address = teacher_address;
        this.teacher_ph = teacher_ph;
        this.teacher_email = teacher_email;
    }
}
