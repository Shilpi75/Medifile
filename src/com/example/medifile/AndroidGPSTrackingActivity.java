package com.example.medifile;

import com.android.accelerometerlibrary.AccelerometerListener;
import com.android.accelerometerlibrary.AccelerometerManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidGPSTrackingActivity  extends Activity implements AccelerometerListener{
	public static final int SENT = 0;
	public static final int DELIVERED = 1;

	Button btnShowLocation;
	TextView t1;
	SharedPreferences pref;

	// GPSTracker class
	GPSTracker gps;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_gpstracking);

		btnShowLocation = (Button) findViewById(R.id.button1);
		t1 = (TextView) findViewById(R.id.textView1);
		// show location button click event
		btnShowLocation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// create class object

				SharedPreferences pref;
				pref = getSharedPreferences("emergency", Context.MODE_PRIVATE);

				gps = new GPSTracker(AndroidGPSTrackingActivity.this);

				// check if GPS enabled
				if (gps.canGetLocation()) {

					double latitude = gps.getLatitude();
					double longitude = gps.getLongitude();

					// \n is for new line
					Toast.makeText(
							getApplicationContext(),
							"Your Location is - \nLat: " + latitude
									+ "\nLong: " + longitude, Toast.LENGTH_LONG)
							.show();
					String message = "MEDICAL EMERGENCY. PLEASE HELP ME.\nMY LOCATION IS LATITUDE: "
							+ latitude
							+ " LONGITUDE: "
							+ longitude
							+ "\n http://maps.google.com/?q=<"
							+ latitude
							+ ">,<" + longitude + ">" ;
					// String phoneno = pref.getString("contact", " ");
					t1.setText(message);
					String number = pref.getString("contact", null);
					t1.append(number);


					// sms
					try {

						// Get the default instance of the SmsManager

						SmsManager smsManager = SmsManager.getDefault();

						smsManager.sendTextMessage(number,

						null,

						message,

						null,

						null);

						Toast.makeText(getApplicationContext(),
								"Your sms has successfully sent!",

								Toast.LENGTH_LONG).show();

					} catch (Exception ex) {
						Toast.makeText(getApplicationContext(),
								"Your sms has failed...",

								Toast.LENGTH_LONG).show();

						ex.printStackTrace();

					}
					
					
					
					

					

					// calling function
					 try {
					 // set the data
					
					 String uri = "tel:" + number;
					
					 Intent callIntent = new Intent(Intent.ACTION_CALL, Uri
					 .parse(uri));
					
					 startActivity(callIntent);
					
					 } catch (Exception e) {
					
					 Toast.makeText(getApplicationContext(),
					 "Your call has failed...",
					
					 Toast.LENGTH_LONG).show();
					
					 e.printStackTrace();
					
					 }

				} else {
					// can't get location
					// GPS or Network is not enabled
					// Ask user to enable GPS/network in settings
					gps.showSettingsAlert();
				}

			}
		});
	}

	@Override
	public void onAccelerationChanged(float arg0, float arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShake(float arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(AndroidGPSTrackingActivity.this, "shake sensor", 0).show();
		
	}

	
//	@Override
//	public void onResume()
//	{
//		super.onResume();
//		if(AccelerometerManager.isSupported(this))
//		{
//			AccelerometerManager.startListening(this);
//			
//		}
//	}
}