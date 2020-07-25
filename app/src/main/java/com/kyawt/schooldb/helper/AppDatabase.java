package com.kyawt.schooldb.helper;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kyawt.schooldb.course.dao.CourseDAO;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.ParentModel;
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.model.StudentModel;
import com.kyawt.schooldb.model.SubjectModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.model.TimetableModel;
import com.kyawt.schooldb.parent.dao.ParentDAO;
import com.kyawt.schooldb.registration.dao.RegisterDAO;
import com.kyawt.schooldb.student.dao.StudentDAO;
import com.kyawt.schooldb.subject.dao.SubjectDAO;
import com.kyawt.schooldb.teacher.dao.TeacherDAO;
import com.kyawt.schooldb.timetable.dao.TimetableDAO;

@Database(entities = {CourseModel.class, SubjectModel.class, TimetableModel.class, RegisterModel.class, TeacherModel.class, StudentModel.class}, version = 9, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseDAO courseDAO();
    public abstract SubjectDAO subjectDAO();
    public abstract TimetableDAO timetableDAO();
    public abstract RegisterDAO registerDAO();
    public abstract TeacherDAO teacherDAO();
    public abstract StudentDAO studentDAO();
}
