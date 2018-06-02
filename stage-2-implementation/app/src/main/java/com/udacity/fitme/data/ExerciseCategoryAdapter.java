package com.udacity.fitme.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.GridLayout;

public class ExerciseCategoryAdapter extends RecyclerView.Adapter {

    public class ExerciseCategoryViewHolder extends RecyclerView.ViewHolder {
        public ExerciseCategoryViewHolder(GridLayout view) {
            super(view);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
