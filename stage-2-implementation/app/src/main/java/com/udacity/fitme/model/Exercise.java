package com.udacity.fitme.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Serializable {

    private int mId;
    private String mName;
    private String mDescription;
    private int mExerciseCategory;
    private ArrayList<Integer> mMuscleList;
    private ArrayList<Integer> mEquipmentList;
    private ArrayList<String> mImageUrlList;

    public Exercise(int id, String name, String description, int exerciseCategory,
                    ArrayList<Integer> muscleList, ArrayList<Integer> equipmentList) {
        mId = id;
        mName = name;
        mDescription = description;
        mExerciseCategory = exerciseCategory;
        mMuscleList = muscleList;
        mEquipmentList = equipmentList;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmExerciseCategory() {
        return mExerciseCategory;
    }

    public void setmExerciseCategory(int mExerciseCategory) {
        this.mExerciseCategory = mExerciseCategory;
    }

    public ArrayList<Integer> getmMuscleList() {
        return mMuscleList;
    }

    public void setmMuscleList(ArrayList<Integer> mMuscleList) {
        this.mMuscleList = mMuscleList;
    }

    public ArrayList<Integer> getmEquipmentList() {
        return mEquipmentList;
    }

    public void setmEquipmentList(ArrayList<Integer> mEquipmentList) {
        this.mEquipmentList = mEquipmentList;
    }

    public ArrayList<String> getmImageUrlList() {
        return mImageUrlList;
    }

    public void setmImageUrlList(ArrayList<String> mImageUrlList) {
        this.mImageUrlList = mImageUrlList;
    }
}
