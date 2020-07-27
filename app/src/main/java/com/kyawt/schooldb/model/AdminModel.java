package com.kyawt.schooldb.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AdminModel {
    @PrimaryKey(autoGenerate = true)
    public int admin_id;

    @ColumnInfo(name = "Admin_user_name")
    public String admin_user_name;

    @ColumnInfo(name = "Admin_email")
    public String admin_email;

    @ColumnInfo(name = "Admin_password")
    public String admin_password;

    public AdminModel(String admin_user_name, String admin_email, String admin_password) {
        this.admin_user_name = admin_user_name;
        this.admin_email = admin_email;
        this.admin_password = admin_password;
    }
}
