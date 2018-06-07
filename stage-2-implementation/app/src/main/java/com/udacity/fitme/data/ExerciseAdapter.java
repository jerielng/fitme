package com.udacity.fitme.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udacity.fitme.R;
import com.udacity.fitme.model.Exercise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    public static final String JSON_RESULTS = "results";
    public static final String JSON_ID = "id";
    public static final String JSON_NAME = "name";
    public static final String JSON_DESCRIPTION = "description";
    public static final String JSON_CATEGORY = "category";
    public static final String JSON_MUSCLES = "muscles";
    public static final String JSON_EQUIPMENT = "equipment";

    public Context mContext;
    private ArrayList<Exercise> mExerciseList;
    private ArrayList<Exercise> mSelectedExerciseList;

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public Exercise mExerciseObject;
        public LinearLayout mTextHolder;
        public TextView mNameText;
        public TextView mDescriptionText;
        public boolean selected;

        public ExerciseViewHolder(LinearLayout view) {
            super(view);
            mTextHolder = view;
            mNameText = new TextView(mContext);
            mDescriptionText = new TextView(mContext);
            mTextHolder.addView(mNameText);
            mTextHolder.addView(mDescriptionText);
            selected = false;
        }

        public void toggleViewSelected() {
            if (selected) {
                mTextHolder.setBackgroundResource(R.color.card_back);
                selected = false;
                for (int i = 0; i < mSelectedExerciseList.size(); i++) {
                    if (mSelectedExerciseList.get(i).getmId() == mExerciseObject.getmId()) {
                        mSelectedExerciseList.remove(i);
                        break;
                    }
                }
            } else {
                mTextHolder.setBackgroundResource(R.color.colorPrimaryDark);
                selected = true;
                mSelectedExerciseList.add(mExerciseObject);
            }
        }
    }

    public ExerciseAdapter(Context context, String jsonString) {
        mContext = context;
        mExerciseList = new ArrayList<>();
        mSelectedExerciseList = new ArrayList<>();
        extractExercises(jsonString);
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
        return mExerciseList.size();
    }

    public void extractExercises(String jsonString) {
        try {
            JSONArray exerciseArray = new JSONObject(jsonString).getJSONArray(JSON_RESULTS);
            for (int i = 0; i < exerciseArray.length(); i++) {
                int id = exerciseArray.getJSONObject(i).getInt(JSON_ID);
                String name = exerciseArray.getJSONObject(i).getString(JSON_NAME);
                String description = exerciseArray.getJSONObject(i).getString(JSON_DESCRIPTION);
                int category = exerciseArray.getJSONObject(i).getInt(JSON_CATEGORY);

                ArrayList<Integer> muscleList = new ArrayList<>();
                JSONArray jsonMuscleArray =
                        exerciseArray.getJSONObject(i).getJSONArray(JSON_MUSCLES);
                for (int j = 0; j < jsonMuscleArray.length(); j++) {
                    muscleList.add(jsonMuscleArray.getInt(j));
                }

                ArrayList<Integer> equipmentList = new ArrayList<>();
                JSONArray jsonEquipmentArray =
                        exerciseArray.getJSONObject(i).getJSONArray(JSON_EQUIPMENT);
                for (int j = 0; j < jsonEquipmentArray.length(); j++) {
                    muscleList.add(jsonEquipmentArray.getInt(j));
                }

                Exercise newExercise =
                        new Exercise(id, name, description, category, muscleList, equipmentList);
                mExerciseList.add(newExercise);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
