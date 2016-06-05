package com.example.medifile;

import java.util.ArrayList;
import java.util.List;

import com.android.accelerometerlibrary.AccelerometerListener;
import com.android.accelerometerlibrary.AccelerometerManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class History extends AppCompatActivity implements OnItemClickListener,AccelerometerListener {
	int counter=0;
	List<HistoryModel> list;
	Databasehandler handler;
	Custom_History adapter;
	CheckBox c1;
	ListView lv,lv2;

	Toolbar toolbar;
	ArrayList<NavModel> navList;
	Custom_nav navAdapter;
	DrawerLayout drawer;
	ActionBarDrawerToggle drawerListener;
	
	AlertDialog.Builder dialog;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		toolbar = (Toolbar) findViewById(R.id.actionbar);
		toolbar.setTitle("My History");
		setSupportActionBar(toolbar);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav);
		
		navList = new ArrayList<NavModel>();

		lv = (ListView) findViewById(R.id.listView1);
		lv2=(ListView)findViewById(R.id.navList);
		
		drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
		handler = new Databasehandler(this);
		list = handler.getAllHistory();

		dialog=new AlertDialog.Builder(this);
		
		lv.setDescendantFocusability(ListView.FOCUS_BLOCK_DESCENDANTS);
		adapter = new Custom_History(this, list);
		// lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent,
					final View view, final int position, long id) {
				// TODO Auto-generated method stub
				Log.e("shilpi", "in long check");
				HistoryViewHolder holder = (HistoryViewHolder) view.getTag();
				HistoryModel obj = list.get(position);
				c1 = holder.getC1();
				c1.setVisibility(View.VISIBLE);
				if (!c1.isChecked()) {
					holder.getC1().setChecked(c1.isChecked());
					c1.setChecked(true);

					obj.setChecked(c1.isChecked());
				} else {
					c1.setChecked(false);
					holder.getC1().setChecked(c1.isChecked());
					obj.setChecked(c1.isChecked());
					c1.setVisibility(View.GONE);

				}

				return true;
			}
		});

		// lv.setOnLongClickListener(new OnLongClickListener() {
		//
		// @Override
		// public boolean onLongClick(View v) {
		// // TODO Auto-generated method stub
		// Log.e("shilpi","in long check");
		// c1=(CheckBox)v.findViewById(R.id.checkBox1);
		// if(!c1.isChecked())
		// c1.setChecked(true);
		// return true;
		// }
		// });

		
		navList.add(new NavModel("My History",R.drawable.historylist));
		navList.add(new NavModel("My Allergies",R.drawable.allergies_food));
		navList.add(new NavModel("My Appointments",R.drawable.app));
		navList.add(new NavModel("BMI",R.drawable.bmiicon));
		navList.add(new NavModel("Emergency",R.drawable.navemergency));
		navList.add(new NavModel("Settings",R.drawable.navsettings));
		navList.add(new NavModel("Share",R.drawable.navshare));
		navList.add(new NavModel("Feedback",R.drawable.navfeedback));
		navAdapter = new Custom_nav(History.this, navList);
		lv2.setAdapter(navAdapter);

		drawerListener = new ActionBarDrawerToggle(this, drawer,
				R.drawable.ic_nav, R.string.opennav, R.string.closenav);
		drawer.setDrawerListener(drawerListener);
		
		lv2.setOnItemClickListener(new  OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch(position)
				{
				case 0:
					
					startActivity(new Intent(History.this,History.class));
					break;
				case 1:
					
					startActivity(new Intent(History.this,Allergies.class));
					break;
				case 2:
					startActivity(new Intent(History.this,Appointments.class));
					break;
				case 3:
					startActivity(new Intent(History.this,BMI_Calculator.class));
					break;
				case 4:
					startActivity(new Intent(History.this,Emergency.class));
					break;
				case 5:
					startActivity(new Intent(History.this,Setting.class));
					break;
				case 6:
					startActivity(new Intent(History.this,Share.class));
					break;
				case 7:
					startActivity(new Intent(History.this,Feedback.class));
					break;
					
					
				}
				drawer.closeDrawer(lv2);
			}
		});
	}

	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerListener.onConfigurationChanged(newConfig);
	}

	
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}

	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Log.e("shilpi", "clicked");
		HistoryModel object = list.get(position);
		Intent in = new Intent(History.this, ViewHistory.class);
		in.putExtra("id", object.getId());
		// in.putExtra("position", position);
		startActivity(in);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		if (drawerListener.onOptionsItemSelected(item)) {
			return true;
		}
		
		
		switch (item.getItemId()) {
		case R.id.addNew:
			startActivity(new Intent(History.this, HistoryAdd.class));
			break;
		case R.id.delete:
			 
			
			dialog.setTitle("Delete History")
			.setMessage("Are you sure to delete selected items?")
			.setCancelable(true)
			.setNegativeButton("Cancel", null)
			.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int flag = 0;
					for (int i = 0; i < list.size(); i++) {
						HistoryModel obj = list.get(i);
						if (obj.isChecked()) {
							Log.e("shilpi", "checked " + obj.getTitle());
							flag = 1;
							handler.deleteHistory(obj.getId());
						}
					}
					if (flag == 0)
						Toast.makeText(History.this, "Sorry!No item selected", 0).show();
					else

						CustomToast.printToast(History.this, "successfully deleted", 0,
								R.drawable.anydo);
					startActivity(new Intent(History.this, History.class));
					
				}
			})
			.show();
			
			
			break;
		case R.id.search:
			startActivity(new Intent(History.this, SearchHistory.class));
			break;
		case R.id.share:
			Intent in = new Intent(Intent.ACTION_SEND);
			in.setType("text/plain");
			String message;
			int flag2=0;
			for (int i = 0; i < list.size(); i++) {
				HistoryModel obj = list.get(i);
				if (obj.isChecked()) {
					flag2=1;
					message = "Doctor name: " + obj.getDoctorName()
							+ "\nLocation: " + obj.getLocation() + "\n"
							+ "Prescription: " + obj.getPrescription()
							+ "\n Contcact no. " + obj.getContactNo1() + " "
							+ obj.getContactNo2();
					in.putExtra(Intent.EXTRA_TEXT, message);
					startActivity(in);
				}
				if(flag2==0)
					Toast.makeText(this,"no item selected",0).show();
			}
			break;
		
		}

		return super.onOptionsItemSelected(item);

	}
	
	@Override
	public void onAccelerationChanged(float arg0, float arg1, float arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onShake(float arg0) {
		Log.e("shilpi"," in on shake");
		SharedPreferences pref;
		pref = getSharedPreferences("emergency", Context.MODE_PRIVATE);
		
		if (pref.getBoolean("triger", false) == true) {
			counter++;
		CustomToast.printToast(this, "Emergency Triger Shake: "+counter,0, R.drawable.triger);
			if(counter==3){
			final int SENT = 0;
			final int DELIVERED = 1;

			// GPSTracker class
			GPSTracker gps;

			gps = new GPSTracker(History.this);

			// check if GPS enabled
			if (gps.canGetLocation()) {

				double latitude = gps.getLatitude();
				double longitude = gps.getLongitude();

				// \n is for new line
//				Toast.makeText(
//						getApplicationContext(),
//						"Your Location is - \nLat: " + latitude + "\nLong: "
//								+ longitude, Toast.LENGTH_LONG).show();
				String message = "MEDICAL EMERGENCY. PLEASE HELP ME.\nMY LOCATION IS LATITUDE: "
						+ latitude
						+ " LONGITUDE: "
						+ longitude
						+ "\n http://maps.google.com/?q=<"
						+ latitude
						+ ">,<"
						+ longitude + ">";
				// String phoneno = pref.getString("contact", " ");

				String number = pref.getString("contact", null);

				// sms
				try {

					// Get the default instance of the SmsManager

					SmsManager smsManager = SmsManager.getDefault();

					smsManager.sendTextMessage(number,

					null,

					message,

					null,

					null);

				CustomToast.printToast(this,"Emergency Message Sent.", 0, R.drawable.anydo);

				} catch (Exception ex) {
					CustomToast.printToast(this,"Emergency Message Failed.", 0, R.drawable.ic_error);
					ex.printStackTrace();

				}

				// calling function
				try {
					// set the data

					String uri = "tel:" + number;

					Intent callIntent = new Intent(Intent.ACTION_CALL,
							Uri.parse(uri));

					startActivity(callIntent);

				} catch (Exception e) {

					CustomToast.printToast(this,"Emergency Calling Failed.", 0, R.drawable.ic_error);

					e.printStackTrace();

				}

			} else {
				// can't get location
				// GPS or Network is not enabled
				// Ask user to enable GPS/network in settings
				gps.showSettingsAlert();
			}
			counter=0;
		}

		}

	}

	@Override
	public void onResume() {
		super.onResume();
		if (AccelerometerManager.isSupported(this)) {
			AccelerometerManager.startListening(this);

		}
	
	}
	
	

	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (AccelerometerManager.isSupported(this)) {
			AccelerometerManager.stopListening();

		}
	}


}

// delete edit and share

