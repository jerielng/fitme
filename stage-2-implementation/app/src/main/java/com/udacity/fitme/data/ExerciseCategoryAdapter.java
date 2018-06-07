package com.udacity.fitme.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udacity.fitme.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExerciseCategoryAdapter extends
        RecyclerView.Adapter<ExerciseCategoryAdapter.ExerciseCategoryViewHolder> {

    public static final String JSON_RESULTS = "results";
    public static final String JSON_NAME = "name";
    public static final String JSON_ID = "id";

    public Context mContext;
    private ArrayList<Pair<Integer, String>> mCategoryList;
    private ArrayList<Integer> mSelectedIdList;

    public class ExerciseCategoryViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mTextHolder;
        public TextView mCategoryText;
        public int mCategoryId;
        public boolean selected;

        public ExerciseCategoryViewHolder(LinearLayout view) {
            super(view);
            mTextHolder = view;
            mCategoryText = new TextView(mContext);
            mTextHolder.addView(mCategoryText);
            selected = false;
        }

        public void toggleViewSelected() {
            if (selected) {
                mTextHolder.setBackgroundResource(R.color.card_back);
                selected = false;
                for (int i = 0; i < mSelectedIdList.size(); i++) {
                    if (mSelectedIdList.get(i) == mCategoryId) {
                        mSelectedIdList.remove(i);
                        break;
                    }
                }
            } else {
                mTextHolder.setBackgroundResource(R.color.colorPrimaryDark);
                selected = true;
                mSelectedIdList.add(mCategoryId);
            }
        }
    }

    public ExerciseCategoryAdapter(Context context, String jsonString) {
        mContext = context;
        mCategoryList = new ArrayList<>();
        mSelectedIdList = new ArrayList<>();
        extractExerciseCategories(jsonString);
    }

    @NonNull
    @Override
    public ExerciseCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout categoryLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams holderParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        holderParams.setMargins(15, 15, 15, 15);
        categoryLayout.setLayoutParams(holderParams);
        categoryLayout.setPadding(50, 200, 50, 200);
        categoryLayout.setBackgroundResource(R.color.card_back);
        categoryLayout.setElevation(8);
        ExerciseCategoryViewHolder categoryViewHolder =
                new ExerciseCategoryViewHolder(categoryLayout);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseCategoryViewHolder holder, int position) {
        Pair<Integer, String> currentPair = mCategoryList.get(position);
        holder.mCategoryId = currentPair.first;
        LinearLayout.LayoutParams textParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        textParams.gravity = Gravity.CENTER;
        holder.mCategoryText.setLayoutParams(textParams);
        holder.mCategoryText.setText(currentPair.second);
        holder.mCategoryText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        holder.mCategoryText.setTextColor(mContext.getResources().getColor(R.color.card_text));
        holder.mCategoryText.setTextSize(20);

        final ExerciseCategoryViewHolder holderIn = holder;
        holder.mTextHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holderIn.toggleViewSelected();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public void extractExerciseCategories(String jsonString) {
        try {
            JSONArray categoriesJson = new JSONObject(jsonString).getJSONArray(JSON_RESULTS);
            for (int i = 0; i < categoriesJson.length(); i++) {
                String categoryName = categoriesJson.getJSONObject(i)
                        .getString(JSON_NAME);
                int categoryId = categoriesJson.getJSONObject(i)
                        .getInt(JSON_ID);
                Pair<Integer, String > categoryPair = new Pair<>(categoryId, categoryName);
                mCategoryList.add(categoryPair);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pair<Integer, String>> getmCategoryList() {
        return mCategoryList;
    }

    public void setmCategoryList(ArrayList<Pair<Integer, String>> mCategoryList) {
        this.mCategoryList = mCategoryList;
    }

    public ArrayList<Integer> getmSelectedIdList() {
        return mSelectedIdList;
    }

    public void setmSelectedIdList(ArrayList<Integer> mSelectedIdList) {
        this.mSelectedIdList = mSelectedIdList;
    }
}
