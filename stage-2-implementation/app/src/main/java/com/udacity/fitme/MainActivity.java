package com.udacity.fitme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.udacity.fitme.views.GenerateFragment;
import com.udacity.fitme.views.SavedWorkoutsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container) ConstraintLayout mFragmentHolder;
    @BindView(R.id.navigation) BottomNavigationView mNavigation;

    public static final String GENERATE_FRAGMENT_TAG = "generate_fragment";
    public static final String SAVED_FRAGMENT_TAG = "saved_fragment";

    private GenerateFragment mGenerateFragment;
    private SavedWorkoutsFragment mSavedWorkoutsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            mGenerateFragment = new GenerateFragment();
            mSavedWorkoutsFragment = new SavedWorkoutsFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mGenerateFragment, GENERATE_FRAGMENT_TAG)
                    .commit();
            mNavigation.setSelectedItemId(R.id.navigation_generate);
        } else {
            mGenerateFragment = (GenerateFragment) getSupportFragmentManager()
                    .findFragmentByTag(GENERATE_FRAGMENT_TAG);
            mSavedWorkoutsFragment = (SavedWorkoutsFragment) getSupportFragmentManager()
                    .findFragmentByTag(SAVED_FRAGMENT_TAG);
            if (mGenerateFragment == null) {
                mGenerateFragment = new GenerateFragment();
            }
            if (mSavedWorkoutsFragment == null) {
                mSavedWorkoutsFragment = new SavedWorkoutsFragment();
            }
        }
        setNavigationListener();
    }

    public void setNavigationListener() {
        mNavigation.setOnNavigationItemSelectedListener
            (new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_saved:
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container,
                                            mSavedWorkoutsFragment, SAVED_FRAGMENT_TAG)
                                    .commit();
                            return true;
                        case R.id.navigation_generate:
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container,
                                            mGenerateFragment, GENERATE_FRAGMENT_TAG)
                                    .commit();
                            return true;
                        case R.id.navigation_gym:
                            Intent mapIntent = new Intent(MainActivity.this,
                                    FindGymActivity.class);
                            startActivity(mapIntent);
                            return false;
                    }
                    return false;
                }
            });
    }
}
