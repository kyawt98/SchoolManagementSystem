package com.kyawt.schooldb.parent.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kyawt.schooldb.model.ParentModel;

import java.util.List;

@Dao
public interface ParentDAO {

    @Insert
    long insertTask(ParentModel parentModel);

    @Update
    void updateTask(ParentModel parentModel);

    @Delete
    void deleteTask(ParentModel parentModel);

    @Query("SELECT * FROM parentmodel ORDER BY parent_id asc")
    List<ParentModel> getAllParents();
}
