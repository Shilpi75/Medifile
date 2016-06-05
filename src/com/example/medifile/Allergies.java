package com.example.medifile;

import java.util.ArrayList;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Allergies extends ActionBarActivity {
	Toolbar toolbar;
	TextView t1, t2, t3, t4;
	ListView lv;
	Databasehandler handler;
	String food, medicines, skin, animals;
	AlertDialog.Builder dialog;
	ArrayList<NavModel> navList;
	Custom_nav adapter;
	DrawerLayout drawer;
	ActionBarDrawerToggle drawerListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allergies);

		toolbar = (Toolbar) findViewById(R.id.actionbar);
		toolbar.setTitle("My Allergies");
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav);

		navList = new ArrayList<NavModel>();

		handler = new Databasehandler(this);
		dialog = new AlertDialog.Builder(this);

		t1 = (TextView) findViewById(R.id.textView1);
		t2 = (TextView) findViewById(R.id.textView2);
		t3 = (TextView) findViewById(R.id.textView3);
		t4 = (TextView) findViewById(R.id.textView4);
		lv = (ListView) findViewById(R.id.listView1);
		drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

		food = handler.getAllergies("food");
		medicines = handler.getAllergies("medicines");
		skin = handler.getAllergies("skin");
		animals = handler.getAllergies("animals");

		t1.setText(food);
		t2.setText(medicines);
		t3.setText(skin);
		t4.setText(animals);

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
					startActivity(new Intent(Allergies.this,History.class));
					break;
				case 1:
					startActivity(new Intent(Allergies.this,Allergies.class));
					break;
				case 2:
					startActivity(new Intent(Allergies.this,Appointments.class));
					break;
				case 3:
					startActivity(new Intent(Allergies.this,BMI_Calculator.class));
					break;
				case 4:
					startActivity(new Intent(Allergies.this,Emergency.class));
					break;
				case 5:
					startActivity(new Intent(Allergies.this,Setting.class));
					break;
				case 6:
					startActivity(new Intent(Allergies.this,Share.class));
					break;
				case 7:
					startActivity(new Intent(Allergies.this,Feedback.class));
					break;
					
					
				}
				drawer.closeDrawer(lv);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.allergies, menu);
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
		
		
		int id = item.getItemId();
		if (id == R.id.edit) {
			Intent in = new Intent(Allergies.this, EditAllergies.class);
			startActivity(in);
			
			return true;
		} else if (id == R.id.delete)

		{
			dialog.setTitle("Delete Allergies")
					.setMessage("Are you sure to delete your allergies?")
					.setCancelable(true)
					.setNegativeButton("cancel", null)
					.setPositiveButton("Delete",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									handler.deleteAllergies();
									startActivity(new Intent(Allergies.this,
											Allergies.class));
									finish();

								}
							}).show();

		}

		return super.onOptionsItemSelected(item);
	}
}
