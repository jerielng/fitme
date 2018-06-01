package com.udacity.fitme;

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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container) ConstraintLayout mFragmentHolder;

    private GenerateFragment mGenerateFragment;
    private SavedWorkoutsFragment mSavedWorkoutsFragment;
    private FindGymFragment mFindGymFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGenerateFragment = new GenerateFragment();
        mSavedWorkoutsFragment = new SavedWorkoutsFragment();
        mFindGymFragment = new FindGymFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mGenerateFragment)
                .commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_generate);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_saved:
                    //check current visible fragment
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
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, mFindGymFragment)
                            .commit();
                    return true;
            }
            return false;
        }
    };
}
