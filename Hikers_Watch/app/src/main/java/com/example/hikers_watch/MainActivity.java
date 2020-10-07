package com.example.hikers_watch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
           startListening();

        }
    }

    public void startListening()
    {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        }

    }

    public void updateLocationInfo(Location location)
    {
        Log.i("Location info",location.toString());

        TextView lattextView = (TextView)findViewById(R.id.LattextView);

        TextView longtextView = (TextView)findViewById(R.id.LongtextView);

        TextView alttextView = (TextView)findViewById(R.id.alttextView);

        TextView acctextView = (TextView)findViewById(R.id.acctextView);

        lattextView.setText("Latitute: "+location.getLatitude());

        longtextView.setText("Longitude: "+location.getLongitude());

        acctextView.setText("Accuracy: "+location.getAccuracy());

        alttextView.setText("Altitude: "+location.getAltitude());

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
                String address="Could not find address !!";
            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            if (addressList != null && addressList.size()>0)
            {
                address = "Address:\n";
            Log.i("Addresses",addressList.get(0).toString());

                if (addressList.get(0).getSubThoroughfare() != null)
                {
                    address += addressList.get(0).getSubThoroughfare() + "\n";
                }
                if (addressList.get(0).getThoroughfare() != null)
                {
                    address += addressList.get(0).getThoroughfare() + "\n";
                }
                if (addressList.get(0).getLocality() != null)
                {
                    address += addressList.get(0).getLocality() + "\n";
                }

                if (addressList.get(0).getPostalCode() != null)
                {
                    address += addressList.get(0).getPostalCode() + "\n";
                }
                if (addressList.get(0).getCountryName() != null)
                {
                    address += addressList.get(0).getCountryName() + "\n";
                }

            }
            TextView addressTextView = (TextView)findViewById(R.id.addresstextView);
            addressTextView.setText(address);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener(){
            @Override
            public void onLocationChanged(Location location) {
                updateLocationInfo(location);
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
        };
        if (Build.VERSION.SDK_INT < 23)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }
        else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,new  String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }else {
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,locationListener);
                Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
                if (location != null)
                updateLocationInfo(location);
            }
        }
    }
}
