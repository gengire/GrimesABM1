package edu.wgu.grimes.abm1.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.UUID;

public abstract class BaseDao {

    protected SQLiteOpenHelper helper;

    protected String genGuid() {
        UUID uuid = UUID.randomUUID();
        String randomGuid = uuid.toString().replace("-","");
        return randomGuid;
    }

    public long selectCountById(String guid) {
        String query = "select count(*) from " + tableName() + " where " + idColumnName() + " = ?";
        long count = 0;
        try (SQLiteDatabase db = helper.getWritableDatabase();
             Cursor cursor = db.rawQuery(query, new String[] {guid})) {
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                count = cursor.getLong(0);
            }
        }
        return count;
    }

    public boolean deleteById(String guid) {
        boolean result = false;
        if (selectCountById(guid) > 0) {
            try (SQLiteDatabase db = helper.getWritableDatabase()) {
                db.delete(tableName(), idColumnName() + " = ?", new String[] {guid});
            }
            result = true;
        }
        return result;
    }

    public void deleteByColumnArg(String column, String arg) {
        try (SQLiteDatabase db = helper.getWritableDatabase()) {
            db.delete(tableName(), column + " = ?", new String[] {arg});
        }
    }

    public void dropTable(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName());
        db.execSQL(sb.toString());
    }

    abstract String tableName();
    abstract String idColumnName();
}
