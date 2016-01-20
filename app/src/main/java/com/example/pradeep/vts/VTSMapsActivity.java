package com.example.pradeep.vts;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.pradeep.vts.Adapters.SpinnerAdapter;
import com.example.pradeep.vts.Adapters.VehicleTrackAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class VTSMapsActivity extends AppCompatActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    double Dlongitude;
    double Dlatitude;
    String mVehicleId;
    String mCustomerId;
    String longitude;
    String latitude;
    Spinner vehicleSpinner;
    ArrayList<HashMap<String,String>> listResponse;
    SpinnerAdapter adapter;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vtsmaps);
        setUpMapIfNeeded();

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        //TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        getSupportActionBar().show();



        // vehicleSpinner.setPopupBackgroundResource(R.drawable.spinner);

        vehicleSpinner= (Spinner) findViewById(R.id.vehicle_number_spinner);
        longitude=getIntent().getStringExtra("longitude");
        latitude=getIntent().getStringExtra("latitude");
        mVehicleId=getIntent().getStringExtra("lVehicleId");
       // mCustomerId=getIntent().getStringExtra("lCustomerId");
        listResponse= (ArrayList<HashMap<String,String>>) getIntent().getSerializableExtra("lSerlizableObject");

         Log.d("log lat", longitude+" : "+latitude+" : "+mVehicleId+" : "+mCustomerId);
         adapter= new SpinnerAdapter(VTSMapsActivity.this,listResponse);
         vehicleSpinner.setAdapter(adapter);
         Dlongitude=Double.valueOf(longitude);
         Dlatitude=Double.valueOf(latitude);
        Log.d("latin", Dlongitude+" : "+Dlatitude+"  ");

        //AIzaSyDq6_aJM2Fu4bmu80uagC1csHMZmzyE2DI


        vehicleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String,String> rowObject=listResponse.get(position);
                String latitude=rowObject.get("lLatiTude");
                String longitude=rowObject.get("lLongiTude");
                String lVehicleId=rowObject.get("lVehicleId");
               // String lCustomerId=rowObject.get("lCustomerId");

                Log.d("log lat spinner", latitude+" : "+longitude+" : "+lVehicleId+" : ");

                // Dlongitude=Double.valueOf(longitude);
                // Dlatitude=Double.valueOf(latitude);
                setUpMapIfNeeded();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));


        mMap.setMyLocationEnabled(true);
                                                              //Dlatitude,Dlongitude
        mMap.addMarker(new MarkerOptions().position(new LatLng(Dlatitude,Dlongitude)).title(mCustomerId).snippet(mVehicleId));
        LatLng latLng = new LatLng(Dlatitude, Dlongitude);
       mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

       /* mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!").snippet("Consider yourself located"));*/

    }
}
