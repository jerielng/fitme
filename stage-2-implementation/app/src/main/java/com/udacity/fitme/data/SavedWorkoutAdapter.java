package com.udacity.fitme.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class SavedWorkoutAdapter extends RecyclerView.Adapter {

    public Context mContext;

    public class SavedWorkoutAdapterViewHolder extends RecyclerView.ViewHolder {
        public SavedWorkoutAdapterViewHolder(LinearLayout view) {
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
