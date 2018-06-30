package com.udacity.fitme.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SavedDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "workouts.db";
    public static final int VERSION_NUMBER = 1;
    public static final String WORKOUTS_TABLE_NAME = "workouts";
    public static final String EXERCISES_TABLE_NAME = "exercises";

    public SavedDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WORKOUTS_TABLE =
                "CREATE TABLE " + WORKOUTS_TABLE_NAME
                + " ("
                + WorkoutProvider.COLUMN_WORKOUT_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + WorkoutProvider.COLUMN_NAME + " TEXT);";
        final String SQL_CREATE_EXERCISES_TABLE =
                "CREATE TABLE " + EXERCISES_TABLE_NAME
                        + " ("
                        + WorkoutProvider.COLUMN_EXERCISE_ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + WorkoutProvider.COLUMN_NAME + " TEXT, "
                        + WorkoutProvider.COLUMN_DESCRIPTION + " TEXT, "
                        + WorkoutProvider.COLUMN_WORKOUT_ID + " INTEGER);";
        db.execSQL(SQL_CREATE_WORKOUTS_TABLE);
        db.execSQL(SQL_CREATE_EXERCISES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WORKOUTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISES_TABLE_NAME);
        onCreate(db);
    }
}
