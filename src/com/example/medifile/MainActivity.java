package com.example.medifile;

import com.android.accelerometerlibrary.AccelerometerListener;
import com.android.accelerometerlibrary.AccelerometerManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity 
{
	TextView medifile;
	Button feedback, allergies, share, login, password, bmi, emergency, triger,
			history, appointments;
	int counter=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		medifile = (TextView) findViewById(R.id.textView1);
		// Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/.ttf");
		// medifile.setTypeface(typeFace);
		feedback = (Button) findViewById(R.id.button2);
		allergies = (Button) findViewById(R.id.button3);
		share = (Button) findViewById(R.id.button4);
		login = (Button) findViewById(R.id.button5);
		password = (Button) findViewById(R.id.button6);
		bmi = (Button) findViewById(R.id.button7);
		emergency = (Button) findViewById(R.id.button8);
		triger = (Button) findViewById(R.id.button9);
		history = (Button) findViewById(R.id.button10);
		appointments = (Button) findViewById(R.id.button11);
		appointments.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, Appointments.class));
			}
		});
		history.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, History.class));
			}
		});
		triger.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivity(new Intent(MainActivity.this,
						AndroidGPSTrackingActivity.class));

			}
		});
		emergency.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, Emergency.class));
			}
		});
		bmi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,
						BMI_Calculator.class));
			}
		});
		password.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, Password.class));
			}
		});
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivity(new Intent(MainActivity.this, Login.class));
			}
		});

		share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, Share.class));
			}
		});
		allergies.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, Allergies.class));

			}
		});
		feedback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(MainActivity.this, Feedback.class);
				startActivity(in);

			}
		});

	}


//	@Override
//	public void onAccelerationChanged(float arg0, float arg1, float arg2) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onShake(float arg0) {
//		// TODO Auto-generated method stub
////counter++;
////Toast.makeText(this, "Emergency triger shake: "+counter,0).show();
////if(counter==3){
//		SharedPreferences pref;
//		pref = getSharedPreferences("emergency", Context.MODE_PRIVATE);
//		if (pref.getBoolean("triger", false) == true) {
//			counter++;
//		CustomToast.printToast(this, "Emergency Triger Shake: "+counter,0, R.drawable.triger);
//			if(counter==3){
//			final int SENT = 0;
//			final int DELIVERED = 1;
//
//			// GPSTracker class
//			GPSTracker gps;
//
//			gps = new GPSTracker(MainActivity.this);
//
//			// check if GPS enabled
//			if (gps.canGetLocation()) {
//
//				double latitude = gps.getLatitude();
//				double longitude = gps.getLongitude();
//
//				// \n is for new line
////				Toast.makeText(
////						getApplicationContext(),
////						"Your Location is - \nLat: " + latitude + "\nLong: "
////								+ longitude, Toast.LENGTH_LONG).show();
//				String message = "MEDICAL EMERGENCY. PLEASE HELP ME.\nMY LOCATION IS LATITUDE: "
//						+ latitude
//						+ " LONGITUDE: "
//						+ longitude
//						+ "\n http://maps.google.com/?q=<"
//						+ latitude
//						+ ">,<"
//						+ longitude + ">";
//				// String phoneno = pref.getString("contact", " ");
//
//				String number = pref.getString("contact", null);
//
//				// sms
//				try {
//
//					// Get the default instance of the SmsManager
//
//					SmsManager smsManager = SmsManager.getDefault();
//
//					smsManager.sendTextMessage(number,
//
//					null,
//
//					message,
//
//					null,
//
//					null);
//
//				CustomToast.printToast(this,"Emergency Message Sent.", 0, R.drawable.anydo);
//
//				} catch (Exception ex) {
//					CustomToast.printToast(this,"Emergency Message Failed.", 0, R.drawable.ic_error);
//					ex.printStackTrace();
//
//				}
//
//				// calling function
//				try {
//					// set the data
//
//					String uri = "tel:" + number;
//
//					Intent callIntent = new Intent(Intent.ACTION_CALL,
//							Uri.parse(uri));
//
//					startActivity(callIntent);
//
//				} catch (Exception e) {
//
//					CustomToast.printToast(this,"Emergency Calling Failed.", 0, R.drawable.ic_error);
//
//					e.printStackTrace();
//
//				}
//
//			} else {
//				// can't get location
//				// GPS or Network is not enabled
//				// Ask user to enable GPS/network in settings
//				gps.showSettingsAlert();
//			}
//			counter=0;
//		}
//
//		}
//
//	}


//	@Override
//	public void onResume() {
//		super.onResume();
//		if (AccelerometerManager.isSupported(this)) {
//			AccelerometerManager.startListening(this);
//
//		}
//	}
//	
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		if (AccelerometerManager.isSupported(this)) {
//			AccelerometerManager.stopListening();
//
//		}
//	}
}
