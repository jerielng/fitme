package com.udacity.fitme.data;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.fitme.R;
import com.udacity.fitme.WorkoutDetailActivity;
import com.udacity.fitme.model.Exercise;
import com.udacity.fitme.views.ExerciseImageFragment;

import java.util.ArrayList;

public class WorkoutDetailAdapter
        extends RecyclerView.Adapter<WorkoutDetailAdapter.WorkoutViewHolder> {

    private Context mContext;
    private ArrayList<Exercise> mExerciseList;

    public class WorkoutViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout exerciseCard;
        public TextView mNameText;
        public TextView mDescriptionText;
        public Button mImageButton;

        public WorkoutViewHolder(LinearLayout view) {
            super(view);
            exerciseCard = view;
            mNameText = new TextView(mContext);
            mDescriptionText = new TextView(mContext);
            mImageButton = new Button(mContext);
            exerciseCard.addView(mNameText);
            exerciseCard.addView(mDescriptionText);
            exerciseCard.addView(mImageButton);
        }
    }

    public WorkoutDetailAdapter(Context context, ArrayList<Exercise> exerciseList) {
        mContext = context;
        mExerciseList = exerciseList;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout exerciseLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        cardParams.setMargins(15, 15, 15, 15);
        exerciseLayout.setLayoutParams(cardParams);
        exerciseLayout.setOrientation(LinearLayout.VERTICAL);
        exerciseLayout.setBackgroundColor(mContext.getResources().getColor(R.color.card_back));
        exerciseLayout.setPadding(10, 15, 10, 15);
        exerciseLayout.setElevation(8);
        WorkoutDetailAdapter.WorkoutViewHolder workoutViewHolder =
                new WorkoutDetailAdapter.WorkoutViewHolder(exerciseLayout);
        return workoutViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        LinearLayout.LayoutParams contentParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.mNameText.setText(mExerciseList.get(position).getmName());
        holder.mNameText.setTextColor(mContext.getResources().getColor(R.color.card_text));
        holder.mNameText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        holder.mNameText.setTextSize(22);
        holder.mNameText.setTypeface(Typeface.DEFAULT_BOLD);
        holder.mNameText.setPadding(10, 10, 10, 10);

        holder.mDescriptionText.setText(mExerciseList.get(position).getmDescription());
        holder.mDescriptionText.setTextColor(mContext.getResources().getColor(R.color.card_text));
        holder.mDescriptionText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        holder.mDescriptionText.setPadding(10, 5, 10, 5);

        holder.mImageButton.setVisibility(View.GONE);

        if (mExerciseList.get(position).getmImageUrlList().size() != 0) {
            holder.mImageButton.setVisibility(View.VISIBLE);
            holder.mImageButton.setText(mContext.getString(R.string.button_show_me_how));
            holder.mImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExerciseImageFragment imageDetail = new ExerciseImageFragment();
                    ((WorkoutDetailActivity) mContext).getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.workout_detail_activity, imageDetail)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }
}
