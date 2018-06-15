package com.udacity.fitme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Workout implements Parcelable {
    private String mName;
    private ArrayList<Exercise> mExerciseList;

    public Workout(String name, ArrayList<Exercise> exerciseList) {
        mName = name;
        mExerciseList = exerciseList;
    }

    private Workout(Parcel source) {
        mName = source.readString();
        if (source.readByte() == 0x01) {
            mExerciseList = new ArrayList<Exercise>();
            source.readList(mExerciseList, Exercise.class.getClassLoader());
        } else {
            mExerciseList = null;
        }
    }

    public static final Parcelable.Creator<Workout> CREATOR = new Parcelable.Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        if (mExerciseList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mExerciseList);
        }
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