package com.gandsoft.phonetest.ActivityClass;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import com.google.android.gms.location.*;
import android.Manifest;
/**
 * Created by Duke on 9/7/2015.
 */
public class GpsTracker extends Service implements LocationListener {
    private final Context mContext;

    boolean isGPSEnabled = false;

    boolean isNetworkEnabled = false;

    boolean canGetLocation = false;

    Location location;
    double latitude;
    double longitude;


    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;


    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;


    protected LocationManager locationManager;

    public GpsTracker(GpsActivity context) {
        this.mContext = context;
        getLocation();
    }

    @SuppressLint("MissingPermission")
    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);


            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);


            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {

            } else {
                this.canGetLocation = true;

                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    //    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                Log.d("GPS Enabled", "GDisabled");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }


    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(GpsTracker.this);
        }
    }


    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }

        return latitude;
    }


    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }


        return longitude;
    }


    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);


        alertDialog.setTitle("Warning");


        alertDialog.setMessage("Please enabled GPS from settings");


        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });


        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        alertDialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {

        double lat = location.getLatitude();
        double longi = location.getLongitude();
        getLocation();

        Toast.makeText(getApplicationContext(), "My Location is \n" + lat + "\n" + longi, Toast.LENGTH_SHORT);
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
