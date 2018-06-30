package com.udacity.fitme;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.fitme.data.ExerciseProvider;
import com.udacity.fitme.data.WorkoutProvider;
import com.udacity.fitme.model.Exercise;
import com.udacity.fitme.views.ExerciseListFragment;

import java.util.ArrayList;

public class WorkoutDetailActivity extends AppCompatActivity {

    private String mWorkoutName;
    private ArrayList<Exercise> mExerciseList;

    private ExerciseListFragment mExerciseListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);
        mWorkoutName = getIntent().getStringExtra(getString(R.string.workout_name_extra));
        mExerciseList =
                getIntent().getParcelableArrayListExtra(getString(R.string.exercise_list_extra));
        getSupportActionBar().setTitle(mWorkoutName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mExerciseListFragment = new ExerciseListFragment();
        mExerciseListFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.workout_detail_activity, mExerciseListFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.workout_detail_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_save_workout:
                saveWorkout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
        return true;
    }

    public void saveWorkout() {
        ContentValues mWorkoutValues = new ContentValues();
        mWorkoutValues.put(WorkoutProvider.COLUMN_NAME, mWorkoutName);
        Uri workoutUri =
                getContentResolver().insert(WorkoutProvider.WORKOUT_CONTENT_URI, mWorkoutValues);
        long parsedId = ContentUris.parseId(workoutUri);
        for (Exercise e : mExerciseList) {
            ContentValues mExerciseValues = new ContentValues();
            mExerciseValues.put(WorkoutProvider.COLUMN_NAME, e.getmName());
            mExerciseValues.put(WorkoutProvider.COLUMN_DESCRIPTION, e.getmDescription());
            mExerciseValues.put(WorkoutProvider.COLUMN_WORKOUT_ID, parsedId);
            getContentResolver().insert(ExerciseProvider.EXERCISE_CONTENT_URI, mExerciseValues);
        }
        Toast.makeText(this, getString(R.string.toast_workout_saved),
                Toast.LENGTH_SHORT).show();
    }

    public void unsaveWorkout() {

    }

    public boolean isSaved() {
        Uri uri = WorkoutProvider.WORKOUT_CONTENT_URI;
        Cursor cursor = getContentResolver()
                .query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String workoutName = cursor
                        .getString(cursor.getColumnIndex(WorkoutProvider.COLUMN_NAME));
                if (mWorkoutName.equals(workoutName)) {
                    return true;
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
        return false;
    }
}
