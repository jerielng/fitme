package com.udacity.fitme.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.udacity.fitme.R;
import com.udacity.fitme.data.SavedWorkoutAdapter;

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

        mWorkoutAdapter = new SavedWorkoutAdapter();
        mWorkoutListRecycler.setAdapter(mWorkoutAdapter);


        if (mWorkoutAdapter.getItemCount() == 0) {
            mNoWorkoutsContainer.setVisibility(View.VISIBLE);
        }

        ((AppCompatActivity) mContext)
                .getSupportActionBar()
                .setTitle(getString(R.string.title_saved));

        return view;
    }
}
