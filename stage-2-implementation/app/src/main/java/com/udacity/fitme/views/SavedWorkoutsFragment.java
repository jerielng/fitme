package com.udacity.fitme.views;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.udacity.fitme.R;
import com.udacity.fitme.data.ExerciseProvider;
import com.udacity.fitme.data.SavedWorkoutAdapter;
import com.udacity.fitme.data.WorkoutProvider;
import com.udacity.fitme.model.Exercise;
import com.udacity.fitme.model.Workout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedWorkoutsFragment extends Fragment {

    @BindView(R.id.saved_workouts_list_rv) RecyclerView mWorkoutListRecycler;
    @BindView(R.id.no_workouts_text_container) LinearLayout mNoWorkoutsContainer;
    private RecyclerView.Adapter mWorkoutAdapter;
    private RecyclerView.LayoutManager mWorkoutLayoutManager;

    private Context mContext;

    public SavedWorkoutsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =
                inflater.inflate(R.layout.fragment_saved_workouts, container, false);

        ButterKnife.bind(this, view);
        mContext = container.getContext();

        mWorkoutLayoutManager = new LinearLayoutManager(mContext);
        mWorkoutListRecycler.setLayoutManager(mWorkoutLayoutManager);

        mWorkoutAdapter = new SavedWorkoutAdapter(mContext, loadWorkouts());
        mWorkoutListRecycler.setAdapter(mWorkoutAdapter);

        if (mWorkoutAdapter.getItemCount() == 0) {
            mNoWorkoutsContainer.setVisibility(View.VISIBLE);
        }

        ((AppCompatActivity) mContext)
                .getSupportActionBar()
                .setTitle(getString(R.string.title_saved));

        return view;
    }

    public ArrayList<Workout> loadWorkouts() {
        Uri workoutUri = WorkoutProvider.WORKOUT_CONTENT_URI;
        Uri exerciseUri = ExerciseProvider.EXERCISE_CONTENT_URI;
        Cursor cursor = mContext.getContentResolver()
                .query(workoutUri, null, null, null, null);
        ArrayList<Workout> workoutArrayList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int workoutId = cursor
                        .getInt(cursor.getColumnIndex(WorkoutProvider.COLUMN_WORKOUT_ID));
                String workoutName = cursor
                        .getString(cursor.getColumnIndex(WorkoutProvider.COLUMN_NAME));
                ArrayList<Exercise> exerciseArrayList = new ArrayList<>();
                Cursor exerciseCursor = mContext.getContentResolver()
                        .query(exerciseUri, null, null,
                                null, null);
                if (exerciseCursor != null && exerciseCursor.moveToFirst()) {
                    while (!exerciseCursor.isAfterLast()) {
                        int matchingId = exerciseCursor
                                .getInt(exerciseCursor
                                        .getColumnIndex(WorkoutProvider.COLUMN_WORKOUT_ID));
                        if (matchingId == workoutId) {
                            String exerciseName = exerciseCursor
                                    .getString(exerciseCursor
                                            .getColumnIndex(WorkoutProvider.COLUMN_NAME));
                            String description = exerciseCursor
                                    .getString(exerciseCursor
                                            .getColumnIndex(WorkoutProvider.COLUMN_DESCRIPTION));
                            Exercise newExercise = new Exercise(0, exerciseName,
                                    description, 0, new ArrayList<Integer>(),
                                    new ArrayList<Integer>());
                            exerciseArrayList.add(newExercise);
                        }
                        exerciseCursor.moveToNext();
                    }
                }
                Workout newWorkout = new Workout(workoutName, exerciseArrayList);
                workoutArrayList.add(newWorkout);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return workoutArrayList;
    }
}
