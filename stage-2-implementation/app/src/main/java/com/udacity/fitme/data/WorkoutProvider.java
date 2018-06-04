package com.udacity.fitme.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class WorkoutProvider extends ContentProvider {

    public static final String PREFIX = "content://";
    public static final String AUTHORITY = "com.udacity.fitme";
    public static final Uri URI_BASE = Uri.parse(PREFIX + AUTHORITY);
    public static final Uri WORKOUT_CONTENT_URI =
            URI_BASE.buildUpon().appendPath(SavedDbHelper.WORKOUTS_TABLE_NAME).build();
    public static final Uri EQUIPMENT_CONTENT_URI =
            URI_BASE.buildUpon().appendPath(SavedDbHelper.EQUIPMENT_TABLE_NAME).build();
    public static final Uri MUSCLES_CONTENT_URI =
            URI_BASE.buildUpon().appendPath(SavedDbHelper.MUSCLES_TABLE_NAME).build();
    public static final Uri IMAGES_CONTENT_URI =
            URI_BASE.buildUpon().appendPath(SavedDbHelper.IMAGES_TABLE_NAME).build();

    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_MUSCLE_CATEGORY = "muscle_category";
    public static final String COLUMN_EQUIPMENT = "equipment";
    public static final String COLUMN_IMAGE_URL = "image_url";

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
