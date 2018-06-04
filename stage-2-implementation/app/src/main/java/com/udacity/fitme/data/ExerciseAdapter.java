package com.udacity.fitme.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public ExerciseViewHolder(LinearLayout view) {
            super(view);
        }
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
