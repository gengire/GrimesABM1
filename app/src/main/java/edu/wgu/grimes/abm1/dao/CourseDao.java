package edu.wgu.grimes.abm1.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.grimes.abm1.model.Course;
import edu.wgu.grimes.abm1.model.STATUS;

public class CourseDao extends BaseDao {

    public static final String TABLE_COURSES = "abm1_courses";
    public static final String COLUMN_ID = "course_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_START_DATE = "course_start_date";
    public static final String COLUMN_END_DATE = "course_anticipated_end_date";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_TERM_ID = "term_id";

    public CourseDao(SQLiteOpenHelper helper) {
        this.helper = helper;
    }

    public Course selectByCourseId(String guid) {
        String query = "select * from " + TABLE_COURSES + " where " + COLUMN_ID + " = ?";
        return selectByQueryArg(query, guid);
    }

    public Course selectByCourseCode(String code) {
        String query = "select * from " + TABLE_COURSES + " where " + COLUMN_CODE + " = ?";
        return selectByQueryArg(query, code);
    }

    private Course selectByQueryArg(String query, String arg) {
        Course course = null;
        try (SQLiteDatabase db = helper.getWritableDatabase();
             Cursor cursor = db.rawQuery(query, new String[] {arg})) {
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                course = new Course();
                course.setGuid(cursor.getString(0));
                course.setTitle(cursor.getString(1));
                course.setCode(cursor.getString(2));
                course.setStartDate(cursor.getString(3));
                course.setAnticipatedEndDate(cursor.getString(4));
            }
        }
        return course;
    }

    public List<Course> selectCoursesByTermId(String termId) {
        List<Course> courses = new ArrayList<>();
        String query = "select * from " + TABLE_COURSES + " where " + COLUMN_TERM_ID + " = ?";
        try (SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, new String[] {termId})) {
            while (cursor.moveToNext()) {
                Course course = new Course();
                course.setGuid(cursor.getString(0));
                course.setTitle(cursor.getString(1));
                course.setCode(cursor.getString(2));
                course.setStartDate(cursor.getString(3));
                course.setAnticipatedEndDate(cursor.getString(4));
                course.setStatus(STATUS.COURSE.valueOf(cursor.getString(5)));
                course.setTermId(cursor.getString(6));
                courses.add(course);
            }
        }
        return courses;
    }

    public long insert(String title, String code, String startDate, String endDate, STATUS.COURSE status, String termId) {
        long result = -1;
        String id = genGuid();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_CODE, code);
        cv.put(COLUMN_START_DATE, startDate);
        cv.put(COLUMN_END_DATE, endDate);
        cv.put(COLUMN_STATUS, status.toString());
        cv.put(COLUMN_TERM_ID, termId);
        if (selectByCourseCode(code) == null) {
            try (SQLiteDatabase db = helper.getWritableDatabase()) {
                result = db.insert(TABLE_COURSES, null, cv);
            }
        }
        return result;
    }

    public long update(String id, String title, String code, String startDate, String endDate, STATUS status, String termId) {
        long result = -1;
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_CODE, code);
        cv.put(COLUMN_START_DATE, startDate);
        cv.put(COLUMN_END_DATE, endDate);
        cv.put(COLUMN_STATUS, status.toString());
        cv.put(COLUMN_TERM_ID, termId);
        if (selectByCourseId(id) != null) {
            try (SQLiteDatabase db = helper.getWritableDatabase()) {
                result = db.update(TABLE_COURSES, cv, COLUMN_ID + " = ?", new String[] {id});
            }
        }
        return result;
    }

    public void createTable(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table ");
        sb.append(tableName()).append(" ");
        sb.append("(");
        sb.append(COLUMN_ID).append(" TEXT PRIMARY KEY, ");
        sb.append(COLUMN_TITLE).append(" TEXT, ");
        sb.append(COLUMN_CODE).append(" TEXT, ");
        sb.append(COLUMN_START_DATE).append(" TEXT, ");
        sb.append(COLUMN_END_DATE).append(" TEXT, ");
        sb.append(COLUMN_STATUS).append(" TEXT, ");
        sb.append(COLUMN_TERM_ID).append(" TEXT NOT NULL, ");
        sb.append("FOREIGN KEY(" + COLUMN_TERM_ID + ") REFERENCES abm1_terms(term_id)");
        sb.append(")");
        db.execSQL(sb.toString());
    }

    public String tableName() {
        return TABLE_COURSES;
    }

    public String idColumnName() {
        return COLUMN_ID;
    }
}
