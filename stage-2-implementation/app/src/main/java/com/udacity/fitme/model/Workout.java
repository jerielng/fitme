package com.udacity.fitme.model;

import java.util.ArrayList;

public class Workout {
    private String mName;
    private ArrayList<Exercise> mExerciseList;

    public Workout(String name, ArrayList<Exercise> exerciseList) {
        mName = name;
        mExerciseList = exerciseList;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public ArrayList<Exercise> getmExerciseList() {
        return mExerciseList;
    }

    public void setmExerciseList(ArrayList<Exercise> mExerciseList) {
        this.mExerciseList = mExerciseList;
    }
}
