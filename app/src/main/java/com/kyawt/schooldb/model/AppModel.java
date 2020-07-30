package com.kyawt.schooldb.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AppModel {
    @PrimaryKey(autoGenerate = true)
    public int app_id;

    @ColumnInfo(name = "App_name")
    public String app_name;

    public AppModel(String app_name) {
        this.app_name = app_name;
    }
}
