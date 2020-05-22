package edu.wgu.grimes.abm1.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.grimes.abm1.dao.CourseDao;
import edu.wgu.grimes.abm1.dao.TermDao;
import edu.wgu.grimes.abm1.model.Course;
import edu.wgu.grimes.abm1.model.STATUS;
import edu.wgu.grimes.abm1.model.Term;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "abm1.db";
    private static final int DATABASE_VERSION = 1;

    private TermDao termDao;
    private CourseDao courseDao;


    public DBHelper(@Nullable Context context, String name,
                    SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
            initDaos();
    }

    private void initDaos() {
        termDao = new TermDao(this);
        courseDao = new CourseDao(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        termDao.createTable(db);
        courseDao.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        termDao.dropTable(db);
        courseDao.dropTable(db);
        onCreate(db);
    }

    public Term selectTerm(int number) {
        Term term = termDao.selectByTermNumber(number);
        if (term != null) {
            term.getCourses().addAll(courseDao.selectCoursesByTermId(term.getGuid()));
        }
        return term;

    }

    public String insertTerm(int number, String startDate, String endDate) {
        return termDao.insert(number, startDate, endDate);
    }

    public boolean updateTerm(String id, int number, String startDate, String endDate) {
        return termDao.update(id, number, startDate, endDate) != -1;
    }

    public boolean deleteTerm(String guid) {
        return termDao.deleteById(guid);
    }

    public void deleteTerm(int number) {
        termDao.deleteTermByNumber(number);
    }


    public Course selectCourse(String guid) {
        return courseDao.selectByCourseId(guid);
    }

    public boolean insertCourse(String title, String code, String startDate, String endDate, STATUS.COURSE status, String termId) {
        return courseDao.insert(title, code, startDate, endDate, status, termId) != -1;
    }

}
