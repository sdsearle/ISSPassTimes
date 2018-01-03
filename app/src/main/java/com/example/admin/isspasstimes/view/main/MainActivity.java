package com.example.admin.isspasstimes.view.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.isspasstimes.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener, MyItemRecyclerViewAdapter.OnListInteractionListener, MainActivityContract.View{

    private static final int MY_PERMISSIONS_REQUEST_GEOLOCATION = 101;
    private static final String TAG = "MainTag";


    private RecyclerView rvPassTimes;
    private MyItemRecyclerViewAdapter issRVadapter;
    private MainActivityPresenter presenter;
    private List<com.example.admin.isspasstimes.model.Response> issResponses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter();
        presenter.attachView(this);

        rvPassTimes = findViewById(R.id.rvPassTimes);
        rvPassTimes.setLayoutManager(new LinearLayoutManager(this));
        issRVadapter = new MyItemRecyclerViewAdapter(issResponses, this);
        rvPassTimes.setAdapter(issRVadapter);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_GEOLOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            getlocation();
        }


    }

    @SuppressLint("MissingPermission")
    private void getlocation() {
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 100, this);
    }

    private void makeUseOfNewLocation(Location location) {
        presenter.updateCall(location);
    }


    @Override
    public void onLocationChanged(Location location) {
        // Called when a new location is found by the network location provider.
        makeUseOfNewLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onListFragmentInteraction(com.example.admin.isspasstimes.model.Response item) {

    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void updateView(String s) {

    }

    @Override
    public void updateRV(List<com.example.admin.isspasstimes.model.Response> children) {
        issRVadapter.updateList(children);
    }
}
