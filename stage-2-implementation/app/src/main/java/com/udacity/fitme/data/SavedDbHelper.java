package com.udacity.fitme.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SavedDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "workouts.db";
    public static final int VERSION_NUMBER = 1;
    public static final String WORKOUTS_TABLE_NAME = "workouts";
    public static final String EQUIPMENT_TABLE_NAME = "equipment";
    public static final String MUSCLES_TABLE_NAME = "muscles";
    public static final String IMAGES_TABLE_NAME = "images";

    public SavedDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WORKOUTS_TABLE =
                "CREATE TABLE " + WORKOUTS_TABLE_NAME
                + " ("
                + WorkoutProvider.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + WorkoutProvider.COLUMN_NAME + " TEXT, "
                + WorkoutProvider.COLUMN_DESCRIPTION + " TEXT);";
        final String SQL_CREATE_EQUIPMENT_TABLE =
                "CREATE TABLE " + EQUIPMENT_TABLE_NAME
                        + " ("
                        + WorkoutProvider.COLUMN_ID + " INTEGER PRIMARY KEY, "
                        + WorkoutProvider.COLUMN_EQUIPMENT + " INTEGER);";
        final String SQL_CREATE_MUSCLES_TABLE =
                "CREATE TABLE " + MUSCLES_TABLE_NAME
                        + " ("
                        + WorkoutProvider.COLUMN_ID + " INTEGER PRIMARY KEY, "
                        + WorkoutProvider.COLUMN_MUSCLE_CATEGORY + " INTEGER);";
        final String SQL_CREATE_IMAGES_TABLE =
                "CREATE TABLE " + IMAGES_TABLE_NAME
                        + " ("
                        + WorkoutProvider.COLUMN_ID + " INTEGER PRIMARY KEY, "
                        + WorkoutProvider.COLUMN_IMAGE_URL + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_WORKOUTS_TABLE);
        db.execSQL(SQL_CREATE_EQUIPMENT_TABLE);
        db.execSQL(SQL_CREATE_MUSCLES_TABLE);
        db.execSQL(SQL_CREATE_IMAGES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WORKOUTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EQUIPMENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MUSCLES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + IMAGES_TABLE_NAME);
        onCreate(db);
    }
}
