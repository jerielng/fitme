package com.udacity.fitme;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.udacity.fitme.utils.MapNetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class FindGymActivity extends AppCompatActivity implements OnMapReadyCallback,
        OnConnectionFailedListener {

    private static final int REQUEST_LOCATION = 0;

    private GeoDataClient mGeoDataClient;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap mMap;
    private float mZoomLevel;
    private LatLng mCameraTarget;

    public static final String JSON_RESULTS = "results";
    public static final String JSON_GEOMETRY = "geometry";
    public static final String JSON_LOCATION = "location";
    public static final String JSON_LAT = "lat";
    public static final String JSON_LNG = "lng";
    public static final String JSON_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_gym);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        updateMapLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateMapLocation();
                }
                return;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putFloat(getString(R.string.zoom_level_extra), mMap.getCameraPosition().zoom);
        outState.putParcelable
                (getString(R.string.camera_target_extra), mMap.getCameraPosition().target);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mZoomLevel = savedInstanceState.getFloat(getString(R.string.zoom_level_extra));
        mCameraTarget = savedInstanceState.getParcelable(getString(R.string.camera_target_extra));
    }

    public void updateMapLocation() {
        if (ContextCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                LatLng currentLocation =
                                        new LatLng(location.getLatitude(),
                                                location.getLongitude());
                                if (mZoomLevel == 0.0f) {
                                    mZoomLevel = 14.0f;
                                }
                                if (mCameraTarget == null) {
                                    mCameraTarget = currentLocation;
                                }
                                mMap.addMarker
                                        (new MarkerOptions()
                                                .position(currentLocation)
                                                .title(getString(R.string.current_location)));
                                mMap.moveCamera(CameraUpdateFactory
                                        .newLatLngZoom(mCameraTarget, mZoomLevel));
                                String currentString = currentLocation.toString();
                                currentString = currentString
                                        .substring(currentString.indexOf("(") + 1,
                                                currentString.indexOf(")"));
                                new FetchGymsTask().execute(currentString);
                            }
                        }
                    });
        }
    }

    class FetchGymsTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL nearbyRequestUrl = MapNetworkUtils.buildNearbySearchUrl(strings[0]);
                return MapNetworkUtils.getResponseFromHttpUrl(nearbyRequestUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            extractGymData(s);
        }
    }

    public void extractGymData(String jsonGymData) {
        try {
            JSONArray gymJsonArray = new JSONObject(jsonGymData).getJSONArray(JSON_RESULTS);
            for (int i = 0; i < gymJsonArray.length(); i++) {
                double latitude = gymJsonArray.getJSONObject(i)
                        .getJSONObject(JSON_GEOMETRY)
                        .getJSONObject(JSON_LOCATION)
                        .getDouble(JSON_LAT);
                double longitude = gymJsonArray.getJSONObject(i)
                        .getJSONObject(JSON_GEOMETRY)
                        .getJSONObject(JSON_LOCATION)
                        .getDouble(JSON_LNG);
                String gymName = gymJsonArray.getJSONObject(i)
                        .getString(JSON_NAME);
                LatLng gymCoordinates = new LatLng(latitude, longitude);
                mMap.addMarker
                        (new MarkerOptions()
                                .position(gymCoordinates)
                                .title(gymName)
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
