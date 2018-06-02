package com.udacity.fitme.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String BASE_URL = "https://wger.de/api/v2/";
    private static final String EQUIPMENT = "equipment";
    private static final String EXERCISE_CATEGORY = "exercisecategory";

    public static URL buildCategoriesUrl() {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(EXERCISE_CATEGORY)
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildEquipmentUrl() {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(EQUIPMENT)
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildExerciseUrl() {
        URL url = null;
        return url;
    }

    /* This code was referenced from the Udacity Sunshine exercise on Networking */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}