package com.udacity.fitme.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkoutDetailAdapter
        extends RecyclerView.Adapter<WorkoutDetailAdapter.WorkoutViewHolder> {

    public class WorkoutViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout exerciseCard;
        public TextView mNameText;
        public TextView mDescriptionText;
        public GridLayout mImageGrid;

        public WorkoutViewHolder(LinearLayout view) {
            super(view);
            exerciseCard = view;
        }
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
