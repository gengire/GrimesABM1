package edu.wgu.grimes.abm1.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.UUID;

import edu.wgu.grimes.abm1.model.Term;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "abm1.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TERMS = "abm1_terms";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_START_DATE = "start_date";
    private static final String COLUMN_END_DATE = "end_date";




    public DBHelper(@Nullable Context context, String name,
                    SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERMS);
        onCreate(db);
    }

    public boolean insertTerm(int number, String startDate, String endDate) {
        long result = -1;

        if (selectTerm(number) == null) {
            try (SQLiteDatabase db = this.getWritableDatabase()) {
                String id = genGuid();
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_ID, id);
                cv.put(COLUMN_NUMBER, number);
                cv.put(COLUMN_START_DATE, startDate);
                cv.put(COLUMN_END_DATE, endDate);
                result = db.insert(TABLE_TERMS, null, cv);
            }
        }
        return result != -1;
    }

    public boolean updateTerm(String id, int number, String startDate, String endDate) {
        long result = -1;

        if (selectTerm(id) != null) {
            try (SQLiteDatabase db = this.getWritableDatabase()) {
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_NUMBER, number);
                cv.put(COLUMN_START_DATE, startDate);
                cv.put(COLUMN_END_DATE, endDate);
                result = db.update(TABLE_TERMS, cv, COLUMN_ID + " = ?", new String[] {id});
            }
        }
        return result != -1;
    }

    public Term selectTerm(int number) {
        String query = "select * from " + TABLE_TERMS + " where " + COLUMN_NUMBER + " = ?";
        Term term = null;
        try (SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(number)})) {
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

    public Term selectTerm(String guid) {
        String query = "select * from " + TABLE_TERMS + " where " + COLUMN_ID + " = ?";
        Term term = null;
        try (SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, new String[] {guid})) {
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

    public boolean deleteTerm(String guid) {
        boolean result = false;
        if (selectTerm(guid) != null) {
            try (SQLiteDatabase db = this.getWritableDatabase()) {
                db.delete(TABLE_TERMS, COLUMN_ID + " = ?", new String[] {guid});
            }
            result = true;
        }
        return result;
    }

    public boolean deleteTerm(int number) {
        boolean result = false;
        if (selectTerm(number) != null) {
            try (SQLiteDatabase db = this.getWritableDatabase()) {
                db.delete(TABLE_TERMS, COLUMN_NUMBER + " = ?", new String[] {String.valueOf(number)});
            }
            result = true;
        }
        return result;
    }

    private String genGuid() {
        UUID uuid = UUID.randomUUID();
        String randomGuid = uuid.toString().replace("-","");
        return randomGuid;
    }

}
