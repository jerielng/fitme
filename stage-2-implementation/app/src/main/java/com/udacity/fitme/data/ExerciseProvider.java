package com.udacity.fitme.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ExerciseProvider extends ContentProvider {

    public static final String PREFIX = "content://";
    public static final String AUTHORITY = "com.udacity.fitme";
    public static final Uri URI_BASE = Uri.parse(PREFIX + AUTHORITY);
    public static final Uri EXERCISE_CONTENT_URI =
            URI_BASE.buildUpon().appendPath(SavedDbHelper.EXERCISES_TABLE_NAME).build();

    private SavedDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new SavedDbHelper(this.getContext());
        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long exerciseId = database
                .insert(SavedDbHelper.EXERCISES_TABLE_NAME, null, values);
        if (exerciseId > 0) {
            Uri result = ContentUris.withAppendedId(uri, exerciseId);
            getContext().getContentResolver().notifyChange(result, null);
            return result;
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        Cursor cursor;
        cursor = mDbHelper.getReadableDatabase().query(
                SavedDbHelper.EXERCISES_TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Update is not supported.");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        final SQLiteDatabase database = mDbHelper.getWritableDatabase();
        if (null == selection) {
            selection = "1";
        }
        int deleted = database
                .delete(SavedDbHelper.EXERCISES_TABLE_NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return deleted;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("getType is not supported.");
    }
}
