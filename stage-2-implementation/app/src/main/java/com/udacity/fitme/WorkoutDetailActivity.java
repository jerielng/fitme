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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutDetailActivity extends AppCompatActivity {

    public static final String JSON_RESULTS = "results";
    public static final String JSON_EXERCISE = "exercise";
    public static final String JSON_IMAGE = "image";

    private String mWorkoutName;
    private ArrayList<Exercise> mExerciseList;

    @BindView(R.id.detail_loading_layout) LinearLayout mLoadingScreen;
    @BindView(R.id.detail_loading_animation) ImageView mLoadingImage;


    @BindView(R.id.workout_detail_rv) RecyclerView mWorkoutDetailRecycler;
    private LinearLayoutManager mWorkoutLayoutManager;
    private WorkoutDetailAdapter mWorkoutDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);
        ButterKnife.bind(this);

        mWorkoutName = getIntent().getStringExtra(getString(R.string.workout_name_extra));
        mExerciseList =
                getIntent().getParcelableArrayListExtra(getString(R.string.exercise_list_extra));

        mWorkoutLayoutManager = new LinearLayoutManager(this);
        mWorkoutDetailRecycler.setLayoutManager(mWorkoutLayoutManager);

        getSupportActionBar().setTitle(mWorkoutName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((AnimationDrawable) mLoadingImage.getBackground()).start();
        mLoadingScreen.setVisibility(View.VISIBLE);
        for (Exercise e : mExerciseList) {
            new FetchImageUrlsTask().execute(e.getmId());
        }
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

    public void saveWorkout() {
        Toast.makeText(this, getString(R.string.toast_workout_saved),
                Toast.LENGTH_SHORT).show();
    }

    public void addToWidget() {
        Toast.makeText(this, getString(R.string.toast_added_to_widget),
                Toast.LENGTH_SHORT).show();
    }

    class FetchImageUrlsTask extends AsyncTask<Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... integers) {
            try {
                URL imageRequestUrl = NetworkUtils.buildImageUrl(integers[0]);
                return NetworkUtils.getResponseFromHttpUrl(imageRequestUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mLoadingScreen.setVisibility(View.GONE);
            try {
                JSONArray imageUrlArray = new JSONObject(s).getJSONArray(JSON_RESULTS);
                if (imageUrlArray.length() != 0) {
                    int currentExercise =
                            imageUrlArray.getJSONObject(0).getInt(JSON_EXERCISE);
                    for (Exercise e : mExerciseList) {
                        if (e.getmId() == currentExercise) {
                            for (int i = 0; i < imageUrlArray.length(); i++) {
                                e.getmImageUrlList()
                                        .add(imageUrlArray.getJSONObject(i).getString(JSON_IMAGE));
                            }
                        }
                    }
                }
                mWorkoutDetailAdapter =
                        new WorkoutDetailAdapter(WorkoutDetailActivity.this, mExerciseList);
                mWorkoutDetailRecycler.setAdapter(mWorkoutDetailAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
