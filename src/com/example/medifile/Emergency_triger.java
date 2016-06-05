package com.example.medifile;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class Emergency_triger extends AppCompatActivity implements LocationListener {
	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	protected LocationManager locationManager;
	LocationListener listener;
	protected Context context;
	protected boolean gps_enabled, network_enabled;
	TextView txtLat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emergency_triger);
		txtLat = (TextView) findViewById(R.id.textview1);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		
		// getting GPS status
		gps_enabled = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		// getting network status
		network_enabled = locationManager
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		
		
		

		
		
        // Showing Alert Message
      
		if (gps_enabled) {
			Log.e("shipi","in if");
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER,0,
					0, this);
		} else if (network_enabled) {
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
					MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
		}
		;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		Log.d("shilpi","in loctipn changed");
		txtLat = (TextView) findViewById(R.id.textview1);
		double latitude=location.getLatitude();
		txtLat.setText("Latitude: " + location.getLatitude() + ",Longitude:"+ location.getLongitude());
		Toast.makeText(this," "+latitude, 0).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}

	