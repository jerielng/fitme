package com.udacity.fitme.views;

import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.udacity.fitme.R;
import com.udacity.fitme.WorkoutDetailActivity;
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

public class ExerciseListFragment extends Fragment {

    public static final String JSON_RESULTS = "results";
    public static final String JSON_EXERCISE = "exercise";
    public static final String JSON_IMAGE = "image";

    private ArrayList<Exercise> mExerciseList;

    @BindView(R.id.detail_loading_layout)
    LinearLayout mLoadingScreen;
    @BindView(R.id.detail_loading_animation)
    ImageView mLoadingImage;

    @BindView(R.id.workout_detail_rv)
    RecyclerView mWorkoutDetailRecycler;
    private LinearLayoutManager mWorkoutLayoutManager;
    private WorkoutDetailAdapter mWorkoutDetailAdapter;

    public ExerciseListFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =
                inflater.inflate(R.layout.fragment_exercise_list, container, false);
        ButterKnife.bind(this, view);

        mExerciseList = getArguments()
                .getParcelableArrayList(getString(R.string.exercise_list_extra));

        mWorkoutLayoutManager = new LinearLayoutManager(getActivity());
        mWorkoutDetailRecycler.setLayoutManager(mWorkoutLayoutManager);

        ((AnimationDrawable) mLoadingImage.getBackground()).start();
        mLoadingScreen.setVisibility(View.VISIBLE);
        for (Exercise e : mExerciseList) {
            new FetchImageUrlsTask().execute(e.getmId());
        }
        return view;
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
                        new WorkoutDetailAdapter(getActivity(), mExerciseList);
                mWorkoutDetailRecycler.setAdapter(mWorkoutDetailAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
