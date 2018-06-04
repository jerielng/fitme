package com.udacity.fitme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WorkoutDetailActivity extends AppCompatActivity {

    private String mWorkoutName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);
        mWorkoutName = getIntent().getStringExtra(getString(R.string.workout_name_extra));
        getSupportActionBar().setTitle(mWorkoutName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
