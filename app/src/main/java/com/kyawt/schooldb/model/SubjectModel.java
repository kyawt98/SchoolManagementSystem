package com.kyawt.schooldb.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SubjectModel {
    @PrimaryKey
    public int sub_code;

    @ColumnInfo(name = "Subject_name")
    public String sub_name;

    public SubjectModel(int sub_code, String sub_name) {
        this.sub_code = sub_code;
        this.sub_name = sub_name;
    }
}
