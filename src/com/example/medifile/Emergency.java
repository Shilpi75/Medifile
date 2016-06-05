package com.example.medifile;

import java.util.ArrayList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iangclifton.android.floatlabel.FloatLabel;

public class Emergency extends AppCompatActivity {
	Toolbar toolbar;
	SwitchCompat enable;
	SharedPreferences pref;
	SharedPreferences.Editor editor;
	LinearLayout l1;
	boolean triger;

FloatLabel e1;
	Button save, edit, view;
	AlertDialog.Builder builder;
	
	ArrayList<NavModel> navList;
	Custom_nav adapter;
	DrawerLayout drawer;
	ActionBarDrawerToggle drawerListener;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emergency);

		toolbar = (Toolbar) findViewById(R.id.actionbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav);

		enable = (SwitchCompat) findViewById(R.id.switchCompat1);

		navList = new ArrayList<NavModel>();
		
		l1 = (LinearLayout) findViewById(R.id.linear1);
		e1 = (FloatLabel) findViewById(R.id.editText1);
		save = (Button) findViewById(R.id.button1);
		edit = (Button) findViewById(R.id.button2);
		view = (Button) findViewById(R.id.button3);
		lv=(ListView)findViewById(R.id.listView1);
		drawer=(DrawerLayout)findViewById(R.id.drawerLayout);

		Typeface tf = Typeface.createFromAsset(this.getAssets(),
				"Sansation-Regular.ttf");
		enable.setTypeface(tf);

		pref = getSharedPreferences("emergency", Context.MODE_PRIVATE);

		edit.setVisibility(View.GONE);
		view.setVisibility(View.GONE);

		builder = new AlertDialog.Builder(Emergency.this);

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String current = pref.getString("contact", "");

				builder.setTitle("Emergency number").setMessage(current)
						.setPositiveButton("OK", null)
						.setIcon(R.drawable.ic_launcher);
				builder.show();

			}
		});
		edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Emergency.this, Emergency_Edit.class));
			}
		});

		editor = pref.edit();
		if (pref.getBoolean("enable", false) == true) {
			enable.setChecked(true);
			check();
		}

		enable.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					builder.setTitle("Emergency")
							.setMessage(
									"Shake your phone three times to triger emergency when you need help.Our location will send to the emergency number saved.")
							.setCancelable(false)
							.setPositiveButton("ok",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											check();

										}
									}).show();
					
					editor.putBoolean("triger", true);
					editor.commit();

				} else {
					l1.setVisibility(View.VISIBLE);
					
					
					
					edit.setVisibility(View.GONE);
					view.setVisibility(View.GONE);
					editor.putBoolean("triger", false);
					editor.commit();
					Toast.makeText(Emergency.this, "emergency disabled", 0)
							.show();

				}
			}

		});
	
		triger=pref.getBoolean("triger", false);
		Log.e("triger",""+triger);
		if(triger==true)
		{
			
		}
		
		
		navList.add(new NavModel("My History",R.drawable.historylist));
		navList.add(new NavModel("My Allergies",R.drawable.allergies_food));
		navList.add(new NavModel("My Appointments",R.drawable.app));
		navList.add(new NavModel("BMI",R.drawable.bmiicon));
		navList.add(new NavModel("Emergency",R.drawable.navemergency));
		navList.add(new NavModel("Settings",R.drawable.navsettings));
		navList.add(new NavModel("Share",R.drawable.navshare));
		navList.add(new NavModel("Feedback",R.drawable.navfeedback));
		
		adapter = new Custom_nav(this, navList);
		lv.setAdapter(adapter);

		drawerListener = new ActionBarDrawerToggle(this, drawer,
				R.drawable.ic_nav, R.string.opennav, R.string.closenav);
		drawer.setDrawerListener(drawerListener);
		
		lv.setOnItemClickListener(new  OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch(position)
				{
				case 0:
					startActivity(new Intent(Emergency.this,History.class));
					break;
				case 1:
					startActivity(new Intent(Emergency.this,Allergies.class));
					break;
				case 2:
					startActivity(new Intent(Emergency.this,Appointments.class));
					break;
				case 3:
					startActivity(new Intent(Emergency.this,BMI_Calculator.class));
					break;
				case 4:
					startActivity(new Intent(Emergency.this,Emergency.class));
					break;
				case 5:
					startActivity(new Intent(Emergency.this,Setting.class));
					break;
				case 6:
					startActivity(new Intent(Emergency.this,Share.class));
					break;
				case 7:
					startActivity(new Intent(Emergency.this,Feedback.class));
					break;
					
					
				}
				drawer.closeDrawer(lv);
			}
		});
	}

	public void check() {
		final String choice = pref.getString("contact", null);
		editor.putBoolean("enable", true);
		editor.commit();
		if (choice != null) {
			l1.setVisibility(View.VISIBLE);

			edit.setVisibility(View.VISIBLE);
			view.setVisibility(View.VISIBLE);
		}

		else {
			l1.setVisibility(View.GONE);
			save.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String contact = e1.getEditText().getText().toString();
					if (contact.equals(""))
						CustomToast.printToast(Emergency.this,
								"Enter valid number", 0, R.drawable.error);
					else {
						editor.putString("contact", contact);

						editor.commit();
						CustomToast.printToast(Emergency.this, "Emergency set",
								0, R.drawable.anydo);
						
						l1.setVisibility(View.VISIBLE);

						edit.setVisibility(View.VISIBLE);
						view.setVisibility(View.VISIBLE);

					}
				}
			});
			
			
		}
		
		
		
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.emergency, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (drawerListener.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	
	
}
