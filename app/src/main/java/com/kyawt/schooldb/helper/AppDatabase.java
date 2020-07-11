package com.kyawt.schooldb.helper;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kyawt.schooldb.course.dao.CourseDAO;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.SubjectModel;
import com.kyawt.schooldb.subject.dao.SubjectDAO;

@Database(entities = {CourseModel.class, SubjectModel.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseDAO courseDAO();
    public abstract SubjectDAO subjectDAO();

}
