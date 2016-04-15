package com.artivisi.android.maps.helper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by endymuhardin on 4/14/16.
 */
public class GpsHelper extends Service implements LocationListener {

    private static final String TAG = "GPS";
    private Context context;

    public GpsHelper(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Location getLocation() {
        LocationManager locationManager = (LocationManager)
                context.getSystemService(LOCATION_SERVICE);

        if(locationManager == null){
            Log.w(TAG, "Location Manager tidak aktif");
        }

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 3 * 60 * 1000, 10, this
            );
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else {
            Log.w(TAG, "GPS tidak dinyalakan");
            return null;
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
