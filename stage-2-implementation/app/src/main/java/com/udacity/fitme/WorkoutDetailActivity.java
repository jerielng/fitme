package com.udacity.fitme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.udacity.fitme.model.Exercise;

import java.util.ArrayList;

import butterknife.BindView;

public class WorkoutDetailActivity extends AppCompatActivity {

    private String mWorkoutName;
    private ArrayList<Exercise> mExerciseList;

    @BindView(R.id.workout_detail_rv) RecyclerView mWorkoutDetailRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);
        mWorkoutName = getIntent().getStringExtra(getString(R.string.workout_name_extra));
        getSupportActionBar().setTitle(mWorkoutName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
