package edu.wgu.grimes.abm1.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TableLayout;

import java.util.List;

import edu.wgu.grimes.abm1.model.Course;
import edu.wgu.grimes.abm1.model.Term;

public class TermDao extends BaseDao {

    private static final String TABLE_TERMS = "abm1_terms";
    private static final String COLUMN_ID = "term_id";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_START_DATE = "term_start_date";
    private static final String COLUMN_END_DATE = "term_end_date";

    public TermDao(SQLiteOpenHelper helper) {
        this.helper = helper;
    }

    public Term selectByTermNumber(int number) {
        String query = "select * from " + TABLE_TERMS + " where " + COLUMN_NUMBER + " = ?";
        return selectByQueryArg(query, String.valueOf(number));
    }

    public Term selectByTermId(String guid) {
        String query = "select * from " + TABLE_TERMS + " where " + COLUMN_ID + " = ?";
        return selectByQueryArg(query, guid);
    }

    private Term selectByQueryArg(String query, String arg) {
        Term term = null;
        try (SQLiteDatabase db = helper.getWritableDatabase();
             Cursor cursor = db.rawQuery(query, new String[] {arg})) {
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                term = new Term();
                term.setGuid(cursor.getString(0));
                term.setNumber(cursor.getInt(1));
                term.setStartDate(cursor.getString(2));
                term.setEndDate(cursor.getString(3));
            }
        }
        return term;
    }

    public String insert(int number, String startDate, String endDate) {
        long result = -1;
        String id = null;
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NUMBER, number);
        cv.put(COLUMN_START_DATE, startDate);
        cv.put(COLUMN_END_DATE, endDate);
        Term term = selectByTermNumber(number);
        if (term == null) {
            id = genGuid();
            cv.put(COLUMN_ID, id);
            try (SQLiteDatabase db = helper.getWritableDatabase()) {
                result = db.insert(TABLE_TERMS, null, cv);
            }
        } else {
            id = term.getGuid();
        }
        return id;
    }

    public void deleteTermByNumber(int number) {
        deleteByColumnArg(COLUMN_NUMBER, String.valueOf(number));
    }

    public long update(String id, int number, String startDate, String endDate) {
        long result = -1;
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NUMBER, number);
        cv.put(COLUMN_START_DATE, startDate);
        cv.put(COLUMN_END_DATE, endDate);
        if (selectByTermId(id) != null) {
            try (SQLiteDatabase db = helper.getWritableDatabase()) {
                result = db.update(TABLE_TERMS, cv, COLUMN_ID + " = ?", new String[] {id});
            }
        }
        return result;
    }

    public void createTable(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table ");
        sb.append(TABLE_TERMS).append(" ");
        sb.append("(");
        sb.append(COLUMN_ID).append(" TEXT PRIMARY KEY, ");
        sb.append(COLUMN_NUMBER).append(" INTEGER UNIQUE, ");
        sb.append(COLUMN_START_DATE).append(" TEXT, ");
        sb.append(COLUMN_END_DATE).append(" TEXT");
        sb.append(")");
        db.execSQL(sb.toString());
    }

    public String tableName() {
        return TABLE_TERMS;
    }

    public String idColumnName() {
        return COLUMN_ID;
    }





}
