package com.udacity.fitme.views;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.fitme.R;
import com.udacity.fitme.data.ExerciseCategoryAdapter;
import com.udacity.fitme.utils.NetworkUtils;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenerateFragment extends Fragment {

    private Context mContext;

    @BindView(R.id.generate_header) TextView mHeaderText;
    @BindView(R.id.muscle_group_rv) RecyclerView mCategoryView;
    @BindView(R.id.equipment_rv) RecyclerView mEquipmentView;
    @BindView(R.id.exercise_selector_rv) RecyclerView mExerciseView;

    private RecyclerView.LayoutManager mCategoryLayoutManager;
    private RecyclerView.LayoutManager mEquipmentLayoutManager;
    private RecyclerView.LayoutManager mExerciseLayoutManager;

    private RecyclerView.Adapter mCategoryAdapter;

    public GenerateFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_generate, container, false);
        ButterKnife.bind(this, view);
        mContext = container.getContext();

        mCategoryLayoutManager = new GridLayoutManager(getContext(), 2,
                LinearLayoutManager.VERTICAL, false);
        mEquipmentLayoutManager = new GridLayoutManager(getContext(), 2,
                LinearLayoutManager.VERTICAL, false);
        mExerciseLayoutManager = new LinearLayoutManager(getContext());

        mCategoryView.setLayoutManager(mCategoryLayoutManager);
        mEquipmentView.setLayoutManager(mEquipmentLayoutManager);
        mExerciseView.setLayoutManager(mExerciseLayoutManager);

        mHeaderText.setText(getString(R.string.muscle_header));
        new FetchCategoriesTask().execute();

        ((AppCompatActivity) mContext)
                .getSupportActionBar()
                .setTitle(getString(R.string.title_generate_full));
        return view;
    }

    class FetchCategoriesTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL categoriesRequestUrl = NetworkUtils.buildCategoriesUrl();
                return NetworkUtils.getResponseFromHttpUrl(categoriesRequestUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            mCategoryAdapter = new ExerciseCategoryAdapter(getContext(), s);
            mCategoryView.setAdapter(mCategoryAdapter);
        }
    }

    class FetchEquipmentTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL equipmentRequestUrl = NetworkUtils.buildEquipmentUrl();
                return NetworkUtils.getResponseFromHttpUrl(equipmentRequestUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
//            mCategoryAdapter = new ExerciseCategoryAdapter(getContext(), s);
//            mCategoryView.setAdapter(mCategoryAdapter);
        }
    }

    class FetchExercisesTask {
    }
}