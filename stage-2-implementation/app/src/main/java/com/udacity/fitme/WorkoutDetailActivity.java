package com.udacity.fitme;

import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.udacity.fitme.data.WorkoutDetailAdapter;
import com.udacity.fitme.model.Exercise;
import com.udacity.fitme.utils.NetworkUtils;
import com.udacity.fitme.views.ExerciseListFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
            case R.id.settings_add_to_widget:
                addToWidget();
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
        Toast.makeText(this, getString(R.string.toast_workout_saved),
                Toast.LENGTH_SHORT).show();
    }

    public void addToWidget() {
        Toast.makeText(this, getString(R.string.toast_added_to_widget),
                Toast.LENGTH_SHORT).show();
    }
}
