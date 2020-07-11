package com.kyawt.schooldb.subject.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kyawt.schooldb.model.SubjectModel;

import java.util.List;

@Dao
public interface SubjectDAO {
    @Insert
    long insertTask(SubjectModel subjectModel);

    @Update
    void updateTask(SubjectModel subjectModel);

    @Delete
    void deleteTask(SubjectModel subjectModel);

    @Query("SELECT * from subjectmodel ORDER BY sub_code asc")
    List<SubjectModel> getAllSubjects();
}
