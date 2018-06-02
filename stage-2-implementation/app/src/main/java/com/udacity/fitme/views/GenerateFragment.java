package com.udacity.fitme.views;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.fitme.R;
import com.udacity.fitme.utils.NetworkUtils;

import java.net.URL;

public class GenerateFragment extends Fragment {

    private Context mContext;

    class FetchCategoriesTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL categoriesRequestUrl = NetworkUtils.buildCategoriesUrl();
                return NetworkUtils.getResponseFromHttpUrl(categoriesRequestUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    public class FetchEquipmentTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL equipmentRequestUrl = NetworkUtils.buildCategoriesUrl();
                return NetworkUtils.getResponseFromHttpUrl(equipmentRequestUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    public class FetchExercisesTask {
    }

    public GenerateFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = container.getContext();

        new FetchCategoriesTask().execute();

        ((AppCompatActivity) mContext)
                .getSupportActionBar()
                .setTitle(getString(R.string.title_generate_full));
        return inflater.inflate(R.layout.fragment_generate, container, false);
    }
}