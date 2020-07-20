package com.kyawt.schooldb.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ParentModel {

    @PrimaryKey(autoGenerate = true)
    public int parent_id;

    @ColumnInfo(name = "Parent_name")
    public String parent_name;

    @ColumnInfo(name = "Parent_gender")
    public String parent_gender;

    @ColumnInfo(name = "Parent_nrc")
    public String parent_nrc;

    @ColumnInfo(name = "Parent_birthday")
    public String parent_birthday;

    @ColumnInfo(name = "Parent_address")
    public String parent_address;

    @ColumnInfo(name = "Parent_phone")
    public String parent_ph;

    @ColumnInfo(name = "Parent_email")
    public String parent_email;

    public ParentModel(String parent_name, String parent_gender, String parent_nrc, String parent_birthday, String parent_address, String parent_ph, String parent_email) {
        this.parent_name = parent_name;
        this.parent_gender = parent_gender;
        this.parent_nrc = parent_nrc;
        this.parent_birthday = parent_birthday;
        this.parent_address = parent_address;
        this.parent_ph = parent_ph;
        this.parent_email = parent_email;
    }
}
