package com.udacity.fitme.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SavedDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "workouts.db";
    public static final int VERSION_NUMBER = 1;
    public static final String TABLE_NAME = "workouts";

    public SavedDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WORKOUTS_TABLE =
                "CREATE TABLE " + TABLE_NAME
                + " (";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
