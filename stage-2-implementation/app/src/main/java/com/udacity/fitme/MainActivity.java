package com.udacity.fitme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.udacity.fitme.views.FindGymFragment;
import com.udacity.fitme.views.GenerateFragment;
import com.udacity.fitme.views.SavedWorkoutsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container) ConstraintLayout mFragmentHolder;
    @BindView(R.id.navigation) BottomNavigationView mNavigation;

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
                    .add(R.id.fragment_container, mGenerateFragment)
                    .commit();

            mNavigation.setSelectedItemId(R.id.navigation_generate);
        } else {
            mGenerateFragment =
                    (GenerateFragment) getSupportFragmentManager()
                            .getFragment(savedInstanceState, "generate_fragment");
            mSavedWorkoutsFragment =
                    (SavedWorkoutsFragment) getSupportFragmentManager()
                            .getFragment(savedInstanceState, "saved_fragment");
        }

        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mGenerateFragment != null && mGenerateFragment.isAdded()) {
            getSupportFragmentManager()
                    .putFragment(outState, "generate_fragment", mGenerateFragment);
        }
        if (mSavedWorkoutsFragment != null && mSavedWorkoutsFragment.isAdded()) {
            getSupportFragmentManager()
                    .putFragment(outState, "saved_fragment", mSavedWorkoutsFragment);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_saved:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, mSavedWorkoutsFragment)
                            .commit();
                    return true;
                case R.id.navigation_generate:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, mGenerateFragment)
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
    };
}
