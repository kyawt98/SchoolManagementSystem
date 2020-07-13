package com.kyawt.schooldb.registration.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kyawt.schooldb.model.RegisterModel;

import java.util.List;

@Dao
public interface RegisterDAO {

    @Insert
    long insertTask(RegisterModel registerModel);

    @Update
    void updateTask(RegisterModel registerModel);

    @Delete
    void deleteTask(RegisterModel registerModel);

    @Query("SELECT * FROM registermodel ORDER BY register_id asc")
    List<RegisterModel> getAllRegisters();
}
