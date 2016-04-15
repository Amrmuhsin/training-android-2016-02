package com.artivisi.android.maps;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.artivisi.android.maps.dto.GenericHttpResponse;
import com.artivisi.android.maps.dto.Location;
import com.artivisi.android.maps.helper.GpsHelper;
import com.artivisi.android.maps.restservice.LocationService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getSimpleName();
    private LocationService locationService = new LocationService();
    private GoogleMap mMap;
    private Button nearMe, allLocation;
    android.location.Location posisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        allLocation = (Button) findViewById(R.id.btnAllLoc);
        nearMe = (Button) findViewById(R.id.btnNearMe);

        allLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetAllLocation().execute();
            }
        });

        nearMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                posisi = new GpsHelper(MapsActivity.this).getLocation();

                String latitude = String.valueOf(posisi.getLatitude());
                String longitude = String.valueOf(posisi.getLongitude());

                new GetNearMe().execute(latitude, longitude);
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-7.5754887, 110.8243272);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in SOLO"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(19), 10000, null);
    }

    private class GetAllLocation extends AsyncTask<Void, Void, GenericHttpResponse> {

        @Override
        protected GenericHttpResponse doInBackground(Void... voids) {
            return locationService.ambilAllLocation();
        }

        @Override
        protected void onPostExecute(GenericHttpResponse genericHttpResponse) {
            Log.i(TAG, genericHttpResponse.getRc());
            List<Location> data = genericHttpResponse.getData();
            mMap.clear();



            for (Location loc :
                    data) {

                LatLng newLoc = new LatLng(new Double(loc.getLat()), new Double(loc.getLng()));
                mMap.addMarker(new MarkerOptions().position(newLoc).title(loc.getName()));

            }
        }
    }

    private class GetNearMe extends AsyncTask<String, Void, GenericHttpResponse> {

        @Override
        protected GenericHttpResponse doInBackground(String... param) {
            return locationService.getNearMe(param[0], param[1]);
        }

        @Override
        protected void onPostExecute(GenericHttpResponse genericHttpResponse) {
            Log.i(TAG, genericHttpResponse.getRc());
            Log.i(TAG, genericHttpResponse.getMessage());

            posisi = new GpsHelper(MapsActivity.this).getLocation();

            if(genericHttpResponse.getRc().equals("00")) {
                List<Location> data = genericHttpResponse.getData();
                mMap.clear();

                LatLng myLoc = new LatLng(posisi.getLatitude(), posisi.getLatitude());
                mMap.addMarker(new MarkerOptions()
                        .position(myLoc)
                        .title("You're here.")
                );

                //create marker for myself
                Log.i(TAG, "" + posisi.getLatitude() + " " + posisi.getLongitude());
                for (Location loc : data) {

                    LatLng newLoc = new LatLng(new Double(loc.getLat()), new Double(loc.getLng()));
                    mMap.addMarker(new MarkerOptions()
                            .position(newLoc)
                            .title(loc.getName())
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                }


            }


        }
    }
}
