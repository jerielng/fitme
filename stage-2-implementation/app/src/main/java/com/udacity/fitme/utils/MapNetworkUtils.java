package com.udacity.fitme.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MapNetworkUtils {
    private static final String BASE_URL =
            "https://maps.googleapis.com/maps/api/place/nearbysearch";
    private static final String OUTPUT = "output";
    private static final String OUTPUT_JSON = "json";
    private static final String LOCATION = "location";
    private static final String RADIUS = "radius";
    private static final String TYPE = "type";
    private static final String TYPE_GYM = "gym";
    private static final String API_KEY = "key";

    public static URL buildNearbySearchUrl(String currentLocation) {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(OUTPUT, OUTPUT_JSON)
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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