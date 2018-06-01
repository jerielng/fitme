package com.udacity.fitme.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.fitme.R;

public class FindGymFragment extends Fragment {

    private Context mContext;

    public FindGymFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = container.getContext();
        ((AppCompatActivity) mContext)
                .getSupportActionBar()
                .setTitle(getString(R.string.title_gym_full));
        return inflater.inflate(R.layout.fragment_find_gym, container, false);
    }
}
