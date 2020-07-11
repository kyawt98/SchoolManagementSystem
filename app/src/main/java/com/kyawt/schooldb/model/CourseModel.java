package com.kyawt.schooldb.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CourseModel {

    @PrimaryKey
    public int class_code;

    @ColumnInfo(name = "Course_name")
    public String class_name;

    public CourseModel( int class_code, String class_name) {
        this.class_code = class_code;
        this.class_name = class_name;
    }
}
